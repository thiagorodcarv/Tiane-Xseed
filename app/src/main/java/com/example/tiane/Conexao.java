package com.example.tiane;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexao extends SQLiteOpenHelper {

    public static final String name = "tiane.db";
    public static final int version = 3;

    public Conexao(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table funcionarios(matricula integer primary key," +
                " nome varchar(50),salario varchar(10))");
	db.execSQL("alter table funcionarios add cidade varchar(40)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("create table produtos(codigo integer primary key," +
                " descricao varchar(50),preco decimal(8,2))");
    }
}
