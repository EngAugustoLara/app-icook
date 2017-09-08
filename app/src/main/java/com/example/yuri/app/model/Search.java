package com.example.yuri.app.model;

import ir.mirrajabi.searchdialog.core.Searchable;

/**
 * Created by yuri on 25/08/17.
 */

public class Search implements Searchable {

    private String mTitle;

    public Search(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }
}


/*
-> Banco
    -> Nome Receita
        -> CODIGO
        -> NOME RECEITA
        -> MODO PREPARO
    -> INGREDIENTES
        -> ID
        -> CODIGO
        -> NOME INGREDIENTE
        -> QUANTIDADE
*/
