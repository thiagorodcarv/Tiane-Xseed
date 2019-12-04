package com.example.tiane;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProdutoActivity extends AppCompatActivity {
    private Integer codigo;
    private String descricao;
    private double preco;
    private FloatingActionButton fabSalvar;
    private FloatingActionButton fabConsultar;
    SQLiteDatabase banco;
    Conexao conexao;
    private Funcionario funcionario = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.produto_activity);
	

//        nome = ((EditText) findViewById(R.id.nomeCadastro)).getText().toString();
//        matricula = (Integer.parseInt(((EditText) findViewById(R.id.matriculaCadastro)).toString()));
//        salario = (Double.parseDouble(((EditText) findViewById(R.id.salarioCadastro)).toString()));

        //Intent intent = getIntent();

        //funcionario = (Funcionario) intent.getSerializableExtra("ObjetoFuncionario");

        //if (funcionario != null){
        //    javaToLayout(funcionario);
        //}



        fabSalvar = findViewById(R.id.saveButton);
        fabConsultar = findViewById(R.id.searchButton);

        conexao = new Conexao(this);
        banco = conexao.getWritableDatabase();

        fabSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutToJava();
                salvar();
                javaToLayout();
            }
        });

        fabConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutToJava();
                consultar();
                javaToLayout();
            }
        });



    }

    public void salvar(){
//        String abc = matricula.getText().toString();
//        String xyz = nome;
//        String xpto = salario.getText().toString();



        banco.execSQL("insert into produtos (codigo, descricao, preco) values ('"+codigo+"','"+descricao+"','"+preco+"')");


//                abc=abc.toUpperCase();
//        xyz=xyz.toUpperCase();
//        xpto=xpto.toUpperCase();

//        matricula.setText(abc);
//        nome.setText(xyz);
//        salario.setText(xpto);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("DEU ONDESTROY");
    }

    public void consultar(){
//        Cursor cursor = banco.query("funcionarios", new String[]{"nome","salario"},
//                "matricula=?",new String[]{matricula.getText().toString()},null,null,null);
//        cursor.moveToFirst();
//        nome.setText(cursor.getString(0));
//        salario.setText(cursor.getString(1));


        Cursor cursor = banco.rawQuery("SELECT descricao,preco,codigo FROM produtos WHERE codigo = ?", new String[] { Integer.toString(codigo) });
        cursor.moveToFirst();
        Toast.makeText(this, "codigo igual a:"+ cursor.getInt(2) , Toast.LENGTH_SHORT).show();
        descricao = (cursor.getString(0));
        preco= (cursor.getDouble(1));


        //OS DOIS METODOS FUNCIONAM DA MESMA FORMA, O MÉTODO ABAIXO É MAIS INTUITIVO
        //VISTO QUE NÃO HÁ A NECESSIDADE DE DECLARAR NULL EM PARAMETROS PARA ORDER BY, HAVING, GROUP BY E OUTROS
        /**
         * Query the given URL, returning a {@link Cursor} over the result set.
         *
         * @param distinct true if you want each row to be unique, false otherwise.
         * @param table The table name to compile the query against.
         * @param columns A list of which columns to return. Passing null will
         *            return all columns, which is discouraged to prevent reading
         *            data from storage that isn't going to be used.
         * @param selection A filter declaring which rows to return, formatted as an
         *            SQL WHERE clause (excluding the WHERE itself). Passing null
         *            will return all rows for the given table.
         * @param selectionArgs You may include ?s in selection, which will be
         *         replaced by the values from selectionArgs, in order that they
         *         appear in the selection. The values will be bound as Strings.
         * @param groupBy A filter declaring how to group rows, formatted as an SQL
         *            GROUP BY clause (excluding the GROUP BY itself). Passing null
         *            will cause the rows to not be grouped.
         * @param having A filter declare which row groups to include in the cursor,
         *            if row grouping is being used, formatted as an SQL HAVING
         *            clause (excluding the HAVING itself). Passing null will cause
         *            all row groups to be included, and is required when row
         *            grouping is not being used.
         * @param orderBy How to order the rows, formatted as an SQL ORDER BY clause
         *            (excluding the ORDER BY itself). Passing null will use the
         *            default sort order, which may be unordered.
         * @param limit Limits the number of rows returned by the query,
         *            formatted as LIMIT clause. Passing null denotes no LIMIT clause.
         * @param cancellationSignal A signal to cancel the operation in progress, or null if none.
         * If the operation is canceled, then {@link OperationCanceledException} will be thrown
         * when the query is executed.
         * @return A {@link Cursor} object, which is positioned before the first entry. Note that
         * {@link Cursor}s are not synchronized, see the documentation for more details.
         * @see Cursor
         */

        /**
         * Runs the provided SQL and returns a {@link Cursor} over the result set.
         *
         * @param sql the SQL query. The SQL string must not be ; terminated
         * @param selectionArgs You may include ?s in where clause in the query,
         *     which will be replaced by the values from selectionArgs. The
         *     values will be bound as Strings.
         * @param cancellationSignal A signal to cancel the operation in progress, or null if none.
         * If the operation is canceled, then {@link OperationCanceledException} will be thrown
         * when the query is executed.
         * @return A {@link Cursor} object, which is positioned before the first entry. Note that
         * {@link Cursor}s are not synchronized, see the documentation for more details.
         */

    }

    public void javaToLayout(){
        ((EditText) findViewById(R.id.descricao)).setText(descricao);
        ((EditText) findViewById(R.id.codigo)).setText(""+codigo);
        ((EditText) findViewById(R.id.preco)).setText(""+preco);
    }

/*
    public void javaToLayout(Funcionario funcionario){
        ((EditText) findViewById(R.id.nomeCadastro)).setText(funcionario.getNome());
        ((EditText) findViewById(R.id.matriculaCadastro)).setText(""+funcionario.getMatricula());
        ((EditText) findViewById(R.id.salarioCadastro)).setText(""+funcionario.getSalario());
//        ((EditText) findViewById(R.id.cidade)).setText(cidade);
    }
*/

    public void layoutToJava(){
        if (((EditText) findViewById(R.id.descricao)).getText()==null){
            descricao = "";
        }
        else {
            descricao = ((EditText) findViewById(R.id.descricao)).getText().toString();
        }
        if (((EditText) findViewById(R.id.codigo)).getText()==null){
            codigo = -1;
        }
        else {
            codigo= Integer.parseInt(((EditText) findViewById(R.id.codigo)).getText().toString());
        }
        if (((EditText) findViewById(R.id.preco)).getText().toString().equals("")){
            preco= 0.0;
        }
        else {
            preco= (Double.parseDouble(((EditText) findViewById(R.id.preco)).getText().toString()));
        }
    }
	


}
