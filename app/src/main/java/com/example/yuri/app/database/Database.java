package com.example.yuri.app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.yuri.app.R;
import com.example.yuri.app.util.ManipulacaoString;

/**
 * Created by yuri on 28/08/17.
 */

public class Database extends SQLiteOpenHelper {

    private Context mContext;

    public Database(Context context) {
        super(context, AtributosDatabase.BancoDados.NOME_BANCO, null, AtributosDatabase.BancoDados.VERSAO_BANCO);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(ManipulacaoString.getStringResource(mContext, R.string.CREATE_TABLE_IF_NOT_EXISTS_TABELA_USUARIO));
            db.execSQL(ManipulacaoString.getStringResource(mContext, R.string.CREATE_TABLE_IF_NOT_EXISTS_TABELA_RECEITAS));
            db.execSQL(ManipulacaoString.getStringResource(mContext, R.string.CREATE_TABLE_IF_NOT_EXISTS_TABELA_INGREDIENTES));
        }catch (Exception e){
            Log.e("DBHelper.onCreate()", e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


}
