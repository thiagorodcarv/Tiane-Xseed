package com.example.tiane;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class FuncionarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CheckBox.OnCheckedChangeListener {

    TextView nome;
    TextView matricula;
    TextView salario;
    ConstraintLayout layout;
    ActivityHolder activityHolder;
    CheckBox checkBox;

    public FuncionarioViewHolder(@NonNull View itemView, ActivityHolder activityHolder) {
        super(itemView);
        this.activityHolder = activityHolder;
        nome = itemView.findViewById(R.id.nome);
        layout = itemView.findViewById(R.id.layout_adapter);
        layout.setOnClickListener(this);
        matricula = itemView.findViewById(R.id.matricula);
        checkBox = itemView.findViewById(R.id.checkBoxDelete);
        checkBox.setOnCheckedChangeListener(this);
        salario = itemView.findViewById(R.id.salario);
    }

    @Override
    public void onClick(View v) {
        activityHolder.chamarUpdate(getAdapterPosition());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        activityHolder.checkedDelete(getAdapterPosition(),isChecked);
    }
}
