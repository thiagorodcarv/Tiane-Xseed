package com.example.tiane;

import java.io.Serializable;

public class Funcionario implements Serializable {
    private String matricula;
    private String nome;
    private String salario;

    public Funcionario(String matricula, String nome, String salario) {
        this.matricula = matricula;
        this.nome = nome;
        this.salario = salario;
    }

    public Funcionario() {
    }
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }
}
