package com.example.yuri.app.model;

/**
 * Created by Usuario on 11/09/2017.
 */

public class ResultadoBusca  {
    private int idResultado;
    private int idReceita;
    private String nomeReceita;

    public ResultadoBusca(int idReceita, String nomeReceita) {
        this.idReceita = idReceita;
        this.nomeReceita = nomeReceita;
    }

    public int getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(int idReceita) {
        this.idReceita = idReceita;
    }

    public String getNomeReceita() {
        return nomeReceita;
    }

    public void setNomeReceita(String nomeReceita) {
        this.nomeReceita = nomeReceita;
    }
}
