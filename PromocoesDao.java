package model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.adrianomerodack.promofind.InserirPromocao;

import java.util.ArrayList;
import java.util.List;

import model.bean.Promocoes;
import model.connection.ConnectionFactory;

/**
 * Created by Adriano Merodack on 22/10/2015.
 */
public class PromocoesDao {
    private SQLiteDatabase db;
    private ConnectionFactory connectionFactory;
    private Promocoes promocoes;
    private InserirPromocao inserirPromocao;
    private static String tabela="promocoes";
    private static String[] colunas={"codigo","promocao","descricao","data","foto"};
    private ConnectionFactory bancoDados;

    public PromocoesDao (Context context){
        bancoDados=new ConnectionFactory(context);
    }

    public String SalvarPromocoes(Promocoes promocoes){
        ContentValues values= new ContentValues();
        long result;
        db = bancoDados.getWritableDatabase();
        values.put("promocao",promocoes.getPromocao());
        values.put("descricao",promocoes.getDescricao());
        values.put("data",promocoes.getData());
        values.put("foto",promocoes.getFoto());
        result=db.insert("promocoes",null,values);

        db.close();
        if (result ==-1) {
            return "Erro ao inserir";
        }else{
            return "Promocao inserida com sucesso";
        }

    }
    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos =  {"promocao","descricao","data","foto"};
        db = bancoDados.getReadableDatabase();
        cursor = db.query("promocoes", campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
        }
    public List<Promocoes> listarObj(String nome){
        List<Promocoes> promocoes= new ArrayList<>();
        String sql="select *from promocoes where descricao like '%"+nome+"%' order by data desc ";
        Cursor cursor=bancoDados.getWritableDatabase().rawQuery(sql,null);
        while(cursor.moveToNext()){
            Promocoes promo=new Promocoes();
            promo.setFoto(cursor.getBlob(4));
            promo.setPromocao(cursor.getString(1));
            promo.setDescricao(cursor.getString(2));
            promo.setData(cursor.getString(3));

            promocoes.add(promo);
        }
        cursor.close();
        return promocoes;

    }



}

