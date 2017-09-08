package com.example.yuri.app.model;

/**
 * Created by yuri on 08/09/17.
 */

public class TwoFragmentItem {

    private int id;
    private String nome;
    private String descricao;

    public TwoFragmentItem(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
