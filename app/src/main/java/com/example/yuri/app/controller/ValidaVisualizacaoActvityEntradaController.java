package com.example.yuri.app.controller;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yuri.app.R;
import com.example.yuri.app.activity.EntradaAppActivity;
import com.example.yuri.app.activity.MainActivity;
import com.example.yuri.app.activity.MenuPrincipalActivity;
import com.example.yuri.app.dao.ConfiguracoesTelaDAO;
import com.example.yuri.app.database.Database;
import com.example.yuri.app.model.TelaConfiguracoes;

/**
 * Created by yuri on 14/09/17.
 */

public class ValidaVisualizacaoActvityEntradaController {

    private Context mContext;
    private ConfiguracoesTelaDAO configuracoesTelaDAO = null;
    private TelaConfiguracoes telaConfiguracoes = null;
    private SQLiteDatabase db;


    public ValidaVisualizacaoActvityEntradaController(Context mContext){

        db = new Database(mContext).getWritableDatabase();
        configuracoesTelaDAO = new ConfiguracoesTelaDAO(mContext);

        mContext.startActivity(new Intent(mContext, MenuPrincipalActivity.class));
        try{

            if (configuracoesTelaDAO.get(R.id.telaApresentacao) == null){
                abrindoTelaApresentacao();
                mContext.startActivity(new Intent(mContext, EntradaAppActivity.class));
                Log.d("Status da tela: (Entrada) ", ""+configuracoesTelaDAO.get(R.id.telaApresentacao).isStatus_tela_acessada());

            } else{
                telaConfiguracoes = configuracoesTelaDAO.get(R.id.telaApresentacao);
                Log.d("Objeto telaConfigurações: ", ""+telaConfiguracoes.getId()+", "+telaConfiguracoes.getId_tela()+", "+telaConfiguracoes.getNome_tela()+", "+telaConfiguracoes.isStatus_tela_acessada());
                mContext.startActivity(new Intent(mContext, MenuPrincipalActivity.class));
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.d("Status", " "+e.getMessage());
        }
    }

    private void abrindoTelaApresentacao(){
        telaConfiguracoes = new TelaConfiguracoes(
                Long.parseLong(String.valueOf(R.id.telaApresentacao)),
                "TelaApresentacao",
                true
        );
        configuracoesTelaDAO.inserir(telaConfiguracoes);
    }
}
