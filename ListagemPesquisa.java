package com.example.adrianomerodack.promofind;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import model.bean.Promocoes;
import model.dao.PromocoesDao;

public class ListagemPesquisa extends AppCompatActivity {
    private PromocoesAdapter adaptador;
    ListView listViewPesquisa;
    EditText txtpesquisar;

    public void listar(){
        PromocoesDao promocoesDao=new PromocoesDao(this);
        txtpesquisar=(EditText) findViewById(R.id.editTextPesq);
        List<Promocoes> lista=promocoesDao.listarObj(txtpesquisar.getText().toString());
        adaptador = new PromocoesAdapter(getApplication(),R.layout.activity_itens_list_view, lista);
        listViewPesquisa=(ListView)findViewById(R.id.listViewPesquisa);
        listViewPesquisa.setAdapter(adaptador);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_pesquisa);
        List<Promocoes> lista;
        PromocoesDao dao = new PromocoesDao(getBaseContext());
        Cursor cursor = dao.carregaDados();
        lista = cursor2List(cursor);
        adaptador = new PromocoesAdapter(getApplication(),R.layout.activity_itens_list_view, lista);
        ListView controle = (ListView)findViewById(R.id.listViewPesquisa);
        controle.setAdapter(adaptador);

        ListView lv= (ListView) findViewById(R.id.listViewPesquisa);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:-19.8516098,-43.9509601?z=15"));
                startActivity(intent);
            }
        });


        Button btnPesquisar=(Button)findViewById(R.id.btnPesquisar);
        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listar();
            }
        });

    }


    protected List<Promocoes> cursor2List(Cursor cursor){
        List<Promocoes> promo = new ArrayList<Promocoes>();
        cursor.moveToFirst();
        do{
            Promocoes promocoes = new Promocoes();

            promocoes.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            promocoes.setPromocao(cursor.getString(cursor.getColumnIndex("promocao")));
            promocoes.setFoto(cursor.getBlob(cursor.getColumnIndex("foto")));
            promocoes.setData(cursor.getString(cursor.getColumnIndex("data")));

            promo.add(promocoes);
        }while(cursor.moveToNext());
        return promo;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listagem_pesquisa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            startActivity(new Intent(ListagemPesquisa.this, TelaPrincipal.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
