package com.example.yuri.app.model;

/**
 * Created by yuri on 14/09/17.
 */

public class TelaConfiguracoes {
    private long id;
    private long id_tela;
    private String nome_tela;
    private boolean status_tela_acessada;

    public TelaConfiguracoes(long id_tela, String nome_tela, boolean status_tela_acessada) {
        this.id = id;
        this.id_tela = id_tela;
        this.nome_tela = nome_tela;
        this.status_tela_acessada = status_tela_acessada;
    }

    public TelaConfiguracoes() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_tela() {
        return id_tela;
    }

    public void setId_tela(long id_tela) {
        this.id_tela = id_tela;
    }

    public String getNome_tela() {
        return nome_tela;
    }

    public void setNome_tela(String nome_tela) {
        this.nome_tela = nome_tela;
    }

    public boolean isStatus_tela_acessada() {
        return status_tela_acessada;
    }

    public void setStatus_tela_acessada(boolean status_tela_acessada) {
        this.status_tela_acessada = status_tela_acessada;
    }
}
