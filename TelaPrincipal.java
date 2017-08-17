package com.example.adrianomerodack.promofind;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaPrincipal extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);



        TextView inserir= (TextView)findViewById(R.id.TextViewInserir);
        inserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(TelaPrincipal.this, InserirPromocao.class));
            }
        });

        TextView listar=(TextView) findViewById(R.id.textViewLista);
        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(TelaPrincipal.this, ListagemPesquisa.class));
            }
        });



    }


}
