package com.example.yuri.app.activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.yuri.app.R;
import com.example.yuri.app.controller.ValidaVisualizacaoActvityEntradaController;
import com.example.yuri.app.dao.ConfiguracoesTelaDAO;
import com.example.yuri.app.database.Database;
import com.example.yuri.app.model.TelaConfiguracoes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            ValidaVisualizacaoActvityEntradaController validaVisualizacaoActvityEntradaController =
                    new ValidaVisualizacaoActvityEntradaController(MainActivity.this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
