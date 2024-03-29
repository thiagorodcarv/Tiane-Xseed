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
    private ActivityHolder activityHolder;
    private boolean b;

    public FuncionarioAdapter(List<Funcionario> funcionarios, Context context,ActivityHolder activityHolder) {
        this.funcionarios = funcionarios;
        this.context = context;
        this.activityHolder = activityHolder;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.funcionario_adapter,parent,false);
        FuncionarioViewHolder holder = new FuncionarioViewHolder(view,activityHolder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final FuncionarioViewHolder holder = (FuncionarioViewHolder) viewHolder;
        Funcionario funcionario = funcionarios.get(position);

        holder.matricula.setText(funcionario.getMatricula().toString());
        holder.nome.setText(funcionario.getNome());
        holder.salario.setText(funcionario.getSalario().toString());
        holder.checkBox.setChecked(b);

    }

    @Override
    public int getItemCount() {
        return funcionarios.size();
    }

    public void setCheckBoxAll(boolean b){
        this.b = b;
    }
}

