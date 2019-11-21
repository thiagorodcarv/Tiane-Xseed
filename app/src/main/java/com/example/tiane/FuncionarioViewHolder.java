package com.example.tiane;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FuncionarioViewHolder extends RecyclerView.ViewHolder {

    TextView nome;
    TextView matricula;
    TextView salario;

    public FuncionarioViewHolder(@NonNull View itemView) {
        super(itemView);
        nome = itemView.findViewById(R.id.nome);
        matricula = itemView.findViewById(R.id.matricula);
        salario = itemView.findViewById(R.id.salario);
    }
}
