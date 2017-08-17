package com.example.adrianomerodack.promofind;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.bean.Promocoes;
import model.dao.PromocoesDao;

public class InserirPromocao extends AppCompatActivity {

    public static final int IMAGEM_INTERNA = 12;

    private EditText txtpromo;
    private EditText txtdesc;
    private EditText txtdata;
    private Button btnFoto;
    private Button btnVoltar;
    private Button btnEnviar;
    private ImageView viewFoto;

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_promocao);
        MyLocation myLocation= new MyLocation(this);
        txtpromo=(EditText)findViewById(R.id.editPromo);
        txtdesc=(EditText)findViewById(R.id.editDesc);
        txtdata=(EditText)findViewById(R.id.editDate);
        viewFoto=(ImageView)findViewById(R.id.ViewFoto);
        btnFoto=(Button)findViewById(R.id.btnFoto);
        btnEnviar=(Button)findViewById(R.id.btnEnviar);
         btnVoltar=(Button)findViewById(R.id.btnVoltar);

        txtdata.setText(getDateTime());
        txtdata.setEnabled(false);



        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] options = {"TIRAR FOTO","ESCOLHER DA GALERIA","CANCELAR"};
                final AlertDialog.Builder builder= new AlertDialog.Builder(InserirPromocao.this);
                builder.setTitle("ESCOLHA UMA OPCAO");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int opselect) {
                        if (options[opselect] == "TIRAR FOTO") {

                            String action = MediaStore.ACTION_IMAGE_CAPTURE;
                            Intent itCamera = new Intent(action);
                            startActivityForResult(itCamera, 0);

                        } else if (options[opselect] == "ESCOLHER DA GALERIA") {

                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                            intent.setType("image/*");
                            startActivityForResult(intent, IMAGEM_INTERNA);

                        } else if (options[opselect] == "CANCELAR") {
                            dialog.dismiss();
                        }

                    }
                });
                builder.show();
            }
        });


        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PromocoesDao dao = new PromocoesDao(getBaseContext());
                Promocoes promocoes = new Promocoes();
                String promocaoString = txtpromo.getText().toString();
                String descricaoString = txtdesc.getText().toString();
                //String dataString= txtdata.getText().toString();

                String resultado;

                ImageView foto = (ImageView)findViewById(R.id.ViewFoto);
                Bitmap bitmap = ((BitmapDrawable)foto.getDrawable()).getBitmap();
                ByteArrayOutputStream saida = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, saida);
                byte[] img = saida.toByteArray();
                promocoes.setFoto(img);
                promocoes.setPromocao(promocaoString);
                promocoes.setDescricao(descricaoString);
                promocoes.setData(getDateTime());
                //promocoes.setData(dataString);

                resultado = dao.SalvarPromocoes(promocoes);

                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                //finish();
                //startActivity(new Intent(InserirPromocao.this, ListagemPesquisa.class));
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(InserirPromocao.this,TelaPrincipal.class));

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0){
            if (resultCode == RESULT_OK) {
                Uri contentUri = data.getData();
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(contentUri, proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String caminho = cursor.getString(column_index);
                Bitmap mBitmap = BitmapFactory.decodeFile(caminho);
                Bitmap imgred = Bitmap.createScaledBitmap(mBitmap, 350, 320, true);
                ImageView iv = (ImageView) findViewById(R.id.ViewFoto);
                iv.setImageBitmap(imgred);



            }
        }

        if(requestCode == IMAGEM_INTERNA){
            if(resultCode == RESULT_OK){

                Uri contentUri = data.getData();
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(contentUri, proj, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String caminho = cursor.getString(column_index);
                Bitmap mBitmap = BitmapFactory.decodeFile(caminho);
                Bitmap imgred = Bitmap.createScaledBitmap(mBitmap, 320, 320 , true);
                ImageView iv = (ImageView) findViewById(R.id.ViewFoto);
                iv.setImageBitmap(imgred);


            }
        }
    }

}
