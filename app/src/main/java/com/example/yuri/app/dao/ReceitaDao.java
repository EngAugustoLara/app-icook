package com.example.yuri.app.dao;

import android.content.ContentValues;
import android.content.Context;

import com.example.yuri.app.database.AtributosDatabase;
import com.example.yuri.app.database.Database;
import com.example.yuri.app.model.Receita;

/**
 * Created by yuri on 29/08/17.
 */

public class ReceitaDao extends PadraoDAO {

    public ReceitaDao(Context mContext) {
        super(mContext, AtributosDatabase.Receita.NOME_TABELA);
    }

    public boolean inserir(Receita pReceita){
        return insert(preencherContentValues(pReceita), new Database(mContext).getWritableDatabase());
    }

    private ContentValues preencherContentValues(Receita pReceita) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(AtributosDatabase.Receita.COLUNA_ID, pReceita.getId());
        contentValues.put(AtributosDatabase.Receita.COLUNA_NOME, pReceita.getNome());

        return contentValues;
    }
}
