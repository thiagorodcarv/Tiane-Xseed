package com.example.tiane;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FuncionarioAdapter extends RecyclerView.Adapter {

    private List<Funcionario> funcionarios;
    private Context context;

    public FuncionarioAdapter(List<Funcionario> funcionarios, Context context) {
        this.funcionarios = funcionarios;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.funcionario_adapter,parent,false);
        FuncionarioViewHolder holder = new FuncionarioViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final FuncionarioViewHolder holder = (FuncionarioViewHolder) viewHolder;
        Funcionario funcionario = funcionarios.get(position);

        holder.matricula.setText(funcionario.getMatricula().toString());
        holder.nome.setText(funcionario.getNome());
        holder.salario.setText(funcionario.getSalario().toString());

        holder.nome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "ITEM CLICADO" +holder.nome.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return funcionarios.size();
    }
}
