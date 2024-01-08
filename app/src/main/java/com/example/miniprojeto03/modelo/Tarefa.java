package com.example.miniprojeto03.modelo;

import java.io.Serializable;

public class Tarefa implements Serializable {

    private Long id;
    private String titulo;
    private String descricao;
    private String data_criacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData_criacao() { return data_criacao; }

    public void setData_criacao(String data_criacao) { this.data_criacao = data_criacao; }
}