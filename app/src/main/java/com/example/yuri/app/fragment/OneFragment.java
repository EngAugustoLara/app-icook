package com.example.yuri.app.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yuri.app.R;
import com.example.yuri.app.activity.EntradaAppActivity;
import com.example.yuri.app.activity.MainActivity;
import com.example.yuri.app.controller.ValidaVisualizacaoActvityEntradaController;
import com.example.yuri.app.dao.ConfiguracoesTelaDAO;
import com.example.yuri.app.model.TelaConfiguracoes;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment{

    private ValidaVisualizacaoActvityEntradaController validaVisualizacaoActvityEntradaController = null;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        Button btn = (Button) view.findViewById(R.id.btnClicadoOne);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfiguracoesTelaDAO configuracoesTelaDAO = new ConfiguracoesTelaDAO(view.getContext());
                TelaConfiguracoes telaConfiguracoes = configuracoesTelaDAO.get(R.id.telaApresentacao);

                Log.d("Objeto telaConfigurações: ", ""
                        +telaConfiguracoes.getId()+", "
                        +telaConfiguracoes.getId_tela()+", "
                        +telaConfiguracoes.getNome_tela()+", "
                        +telaConfiguracoes.isStatus_tela_acessada()
                );

                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}
