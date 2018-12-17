package com.exemplo.aula.exemploaula.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDHelper extends SQLiteOpenHelper {

    static int VERSAO = 1;
    static String DATABASE = "ExemploAula.db";

    public BDHelper(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CriarTblPessoas());

    }

    private String CriarTblPessoas() {
        return  " CREATE TABLE pessoas(codigo integer primary key autoincrement, " +
                "nome varchar(50)," +
                "sobrenome varchar(50)," +
                "idade int); ";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}
