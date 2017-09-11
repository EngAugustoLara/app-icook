package com.example.yuri.app.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.yuri.app.R;
import com.example.yuri.app.adapter.AdapterTwoFragmentPersonalizadoAdapter;
import com.example.yuri.app.model.TwoFragmentItem;
import com.example.yuri.app.util.Mensagem;

import java.util.List;

public class ReceitaIndividualActivity extends AppCompatActivity {

    AdapterTwoFragmentPersonalizadoAdapter adapterTwoFragmentPersonalizadoAdapter;
    private List<TwoFragmentItem> twoFragmentItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita_individual);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        adapterTwoFragmentPersonalizadoAdapter = new AdapterTwoFragmentPersonalizadoAdapter(ReceitaIndividualActivity.this, twoFragmentItemList);

        TextView txtReceitaItem = (TextView) findViewById(R.id.txtReceitaItem);
        setSupportActionBar(toolbar);

        adapterTwoFragmentPersonalizadoAdapter.getIdSelecionado();

        txtReceitaItem.setText("Teste"+adapterTwoFragmentPersonalizadoAdapter.getIdSelecionado());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mensagem.snackShort(view, "Receita adicionada em seu livro");
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
