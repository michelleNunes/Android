package model.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Adriano Merodack on 22/10/2015.
 */
public class ConnectionFactory extends SQLiteOpenHelper {
    private static int VERSAO=1;
    private static String BANCO="projeto.db";
    private static String TABELA="promocoes";

    public ConnectionFactory(Context context){
        super(context,BANCO,null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="create table "+TABELA+"(" +
                "codigo integer primary key autoincrement," +
                "promocao text not null," +
                "descricao text not null," +
                "data text not null,"+
                "foto blob not null);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql="drop table if exists"+TABELA;
        db.execSQL(sql);

    }
}

