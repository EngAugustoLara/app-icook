package com.example.yuri.app.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yuri.app.database.AtributosDatabase;
import com.example.yuri.app.database.Database;
import com.example.yuri.app.model.TelaConfiguracoes;

/**
 * Created by yuri on 14/09/17.
 */

public class ConfiguracoesTelaDAO extends PadraoDAO{


    public ConfiguracoesTelaDAO(Context mContext) {
        super(mContext, AtributosDatabase.Configuracoes_tela.NOME_TABELA);
    }

    public Boolean inserir(TelaConfiguracoes telaConfiguracoes) {
        return super.insert(preencherContentValues(telaConfiguracoes), new Database(mContext).getWritableDatabase());
    }

    public boolean inserir(TelaConfiguracoes telaConfiguracoes, SQLiteDatabase pDataBase) {
        return insert(preencherContentValues(telaConfiguracoes), pDataBase, false);
    }

    @Override
    public Boolean insertOrUpdate(ContentValues pContentValues, String pClausulasWhere, String[] pArgumentosWhere, SQLiteDatabase pDatabase, Boolean pForcaFechamentoBanco) {
        return super.insertOrUpdate(pContentValues, pClausulasWhere, pArgumentosWhere, pDatabase, pForcaFechamentoBanco);
    }

    @Override
    public Boolean insert(ContentValues pContentValues, SQLiteDatabase pDataBase, boolean pForcaFechamentoBanco) {
        return super.insert(pContentValues, pDataBase, pForcaFechamentoBanco);
    }

    @Override
    public Boolean update(ContentValues pContentValues, String pClausulasWhere, String[] pArgumentosWhere, SQLiteDatabase pDataBase) {
        return super.update(pContentValues, pClausulasWhere, pArgumentosWhere, pDataBase);
    }

    @Override
    public Integer updateInt(ContentValues pContentValues, String pClausulasWhere, String[] pArgumentosWhere, SQLiteDatabase pDataBase, boolean pForcaFechamentoBanco) {
        return super.updateInt(pContentValues, pClausulasWhere, pArgumentosWhere, pDataBase, pForcaFechamentoBanco);
    }

    @Override
    public Boolean update(ContentValues pContentValues, String pClausulasWhere, String[] pArgumentosWhere, SQLiteDatabase pDataBase, boolean pForcaFechamentoBanco) {
        return super.update(pContentValues, pClausulasWhere, pArgumentosWhere, pDataBase, pForcaFechamentoBanco);
    }

    @Override
    public boolean exists(String[] pWhereClauses, String[] pSelectionArgs, SQLiteDatabase pDataBase) {
        return super.exists(pWhereClauses, pSelectionArgs, pDataBase);
    }

    @Override
    public boolean exists(String[] pWhereClauses, String[] pSelectionArgs, SQLiteDatabase pDataBase, boolean pForcaFechamentoBanco) {
        return super.exists(pWhereClauses, pSelectionArgs, pDataBase, pForcaFechamentoBanco);
    }


    private ContentValues preencherContentValues(TelaConfiguracoes telaConfiguracoes) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(AtributosDatabase.Configuracoes_tela.COLUNA_ID, telaConfiguracoes.getId());
        contentValues.put(AtributosDatabase.Configuracoes_tela.COLUNA_ID_TELA, telaConfiguracoes.getId_tela());
        contentValues.put(AtributosDatabase.Configuracoes_tela.COLUNA_NOME_TELA, telaConfiguracoes.getNome_tela());
        contentValues.put(AtributosDatabase.Configuracoes_tela.COLUNA_STATUS_TELA_ACESSADA, telaConfiguracoes.isStatus_tela_acessada());

        return contentValues;
    }

    public TelaConfiguracoes preencherObjeto(Cursor c) {
        return preencherObjeto(c, false);
    }

    public TelaConfiguracoes preencherObjeto(Cursor cursor, boolean pRetornarClienteCompleto) {
        TelaConfiguracoes telaConfiguracoes = new TelaConfiguracoes();

        try{
            telaConfiguracoes.setId(cursor.getLong(cursor.getColumnIndex(AtributosDatabase.Configuracoes_tela.COLUNA_ID)));
            telaConfiguracoes.setId_tela(cursor.getLong(cursor.getColumnIndex(AtributosDatabase.Configuracoes_tela.COLUNA_ID_TELA)));
            telaConfiguracoes.setNome_tela(cursor.getString(cursor.getColumnIndex(AtributosDatabase.Configuracoes_tela.COLUNA_NOME_TELA)));
            telaConfiguracoes.setStatus_tela_acessada(cursor.getInt(cursor.getColumnIndex(AtributosDatabase.Configuracoes_tela.COLUNA_STATUS_TELA_ACESSADA)) > 0);
        }catch (Exception e) {
            Log.e("Erro Tela Configurações - PreencherObjeto", " | Erro: "+e.getMessage());
            e.printStackTrace();
        }
        return telaConfiguracoes;
    }

    /*public long geraIdCliente(SQLiteDatabase db) {
        long maxValue = 0;
        SQLiteDatabase dbLocal = null ;

        if(db == null)
            dbLocal = new Database(mContext).getWritableDatabase();
        else
            dbLocal = db ;

        Cursor c = dbLocal.rawQuery("SELECT MAX("+AtributosDatabase.Configuracoes_tela.COLUNA_ID+") FROM "+AtributosDatabase.Configuracoes_tela.NOME_TABELA, null);

        if (c.moveToFirst()) {
            maxValue = c.getLong(0);

            if (maxValue >= 1000000) {
                maxValue += 1;

            } else {
                maxValue = 1000000;
            }
        }
        c.close();
        if(db == null)
            dbLocal.close();

        return maxValue;
    }
    */

    private SQLiteDatabase getDataBase(SQLiteDatabase pDataBase) {
        if (pDataBase != null) {
            return pDataBase;
        } else {
            return new Database(mContext).getWritableDatabase();
        }
    }

    public TelaConfiguracoes get(long pIdTela) {
        return get(pIdTela, null);
    }

    public TelaConfiguracoes get(long pIdTela, SQLiteDatabase pDataBase) {
        SQLiteDatabase dataBase = getDataBase(pDataBase);
        TelaConfiguracoes telaConfiguracoes = null;
        Cursor cursor = null;

        try {
            cursor = dataBase.query(mTable, null, String.format("%s = ?", AtributosDatabase.Configuracoes_tela.COLUNA_ID_TELA), new String[] { String.valueOf(pIdTela) },
                    null, null, null);

            if (cursor.moveToFirst()) {
                telaConfiguracoes = preencherObjeto(cursor);
            }
        } catch (Exception e) {
            Log.e("PedidoDAO.get.", e.getMessage());
        } finally {
            cursor.close();
            if (pDataBase == null) {
                dataBase.close();
            }
        }
        return telaConfiguracoes;
    }


}
