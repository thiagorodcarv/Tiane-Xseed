package com.example.tiane;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListagemFuncionarios extends AppCompatActivity implements ActivityHolder{

    private List<Funcionario> listaFuncionarios = new ArrayList<>();
    private List<Funcionario> listaFuncionariosView = new ArrayList<>();
    private RecyclerView recyclerView;
    private FuncionarioAdapter adapter;
    private List<Funcionario> listaDelete = new ArrayList<>();
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
            listaFuncionarios.add(f);
        }
        listaFuncionariosView.addAll(listaFuncionarios);
        adapter = new FuncionarioAdapter(listaFuncionariosView,this,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void chamarUpdate(int indexPosition) {
        Intent intent = new Intent(this,CadastroActivity.class);
        intent.putExtra("ObjetoFuncionario", listaFuncionariosView.get(indexPosition));
        startActivity(intent);
    }

    @Override
    public void checkedDelete(int indexPosition, boolean check) {
        if(check==true&&!(listaDelete.contains(listaFuncionariosView.get(indexPosition)))){
            listaDelete.add(listaFuncionariosView.get(indexPosition));
        }
        else{
            listaDelete.remove(listaFuncionariosView.get(indexPosition));
        }
    }

    public void newFunc(View view){
        Intent intent = new Intent(this,CadastroActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaFuncionarios.clear();
        listaFuncionariosView.clear();
        Cursor cursor = banco.rawQuery("SELECT matricula,nome,salario FROM funcionarios",null);
        while(cursor.moveToNext()){
            Funcionario f = new Funcionario();
            f.setMatricula(cursor.getString(0));
            f.setNome(cursor.getString(1));
            f.setSalario(cursor.getString(2));
            listaFuncionarios.add(f);
        }
        listaFuncionariosView.addAll(listaFuncionarios);
        adapter.notifyDataSetChanged();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_listagem,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                procuraFuncionario(newText);
                return false;
            }
        });
        return true;
    }
    public void procuraFuncionario(String nome){
        listaFuncionariosView.clear();
        for(Funcionario f : listaFuncionarios){
            if(f.getNome().toLowerCase().contains(nome)){
                listaFuncionariosView.add(f);
            }
        }
        adapter.notifyDataSetChanged();
    }

    public void delete(MenuItem menuItem){
        if (listaDelete.size()==0){
            Toast.makeText(this, "não há nada a ser deletado", Toast.LENGTH_SHORT).show();
        }

        else{
            for (Funcionario f: listaDelete){
                banco.execSQL("DELETE FROM funcionarios WHERE matricula = ?",new String[]{f.getMatricula()});
                System.out.println("\n funcionario: "+f.getNome()+" deletado");
            }
        }

    }
}
