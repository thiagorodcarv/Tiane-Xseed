package com.example.tiane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ListagemFuncionarios extends AppCompatActivity {

    private List<Funcionario> funcionarios = new ArrayList<>();
    private RecyclerView recyclerView;
    private FuncionarioAdapter adapter;
    SQLiteDatabase banco;
    Conexao conexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listagem_funcionarios);
        recyclerView = findViewById(R.id.recyclerview);

        conexao = new Conexao(this);
        banco = conexao.getWritableDatabase();


        Cursor cursor = banco.rawQuery("SELECT matricula,nome,salario FROM funcionarios",null);
        while(cursor.moveToNext()){
            Funcionario f = new Funcionario();
            f.setMatricula(cursor.getString(0));
            f.setNome(cursor.getString(1));
            f.setSalario(cursor.getString(2));
            funcionarios.add(f);
        }
        adapter = new FuncionarioAdapter(funcionarios,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}
