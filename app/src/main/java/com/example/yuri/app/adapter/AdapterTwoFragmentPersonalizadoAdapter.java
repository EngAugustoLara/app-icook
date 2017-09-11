package com.example.yuri.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuri.app.R;
import com.example.yuri.app.activity.MenuPrincipalActivity;
import com.example.yuri.app.activity.ReceitaIndividualActivity;
import com.example.yuri.app.model.TwoFragmentItem;

import java.util.List;

/**
 * Created by yuri on 08/09/17.
 */

public class AdapterTwoFragmentPersonalizadoAdapter extends BaseAdapter {

    private final List<TwoFragmentItem> twoFragmentItemList;
    private Activity activity;
    private Context pContext;
    private long idSelecionado;


    public AdapterTwoFragmentPersonalizadoAdapter(Activity activity, List<TwoFragmentItem> twoFragmentItemList) {
        this.twoFragmentItemList = twoFragmentItemList;
        this.activity = activity;
    }

    public long getIdSelecionado() {
        return idSelecionado;
    }

    public void setIdSelecionado(long posicaoSelecionada) {
        this.idSelecionado = posicaoSelecionada;
    }

    @Override
    public int getCount() {
        return twoFragmentItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return twoFragmentItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = activity.getLayoutInflater().inflate(R.layout.item_lista_two_fragment_personalizado, parent, false);

        TwoFragmentItem twoFragmentItem = twoFragmentItemList.get(position);

        TextView nome = (TextView) view.findViewById(R.id.lista_curso_personalizada_nome);
        ImageView imagem = (ImageView) view.findViewById(R.id.lista_curso_personalizada_imagem);
        ImageView btInfo = (ImageView) view.findViewById(R.id.list_item_genre_arrow);

        nome.setText(twoFragmentItem.getNome());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getBaseContext(), ReceitaIndividualActivity.class);
                setIdSelecionado(getItemId(position));
                activity.startActivity(intent);
            }
        });



        if (nome.getText() == "Java" ){
            imagem.setImageResource(R.drawable.exemploimagem);
        } else if (nome.getText() == "HTML e CSS"){
            imagem.setImageResource(R.drawable.exemploimagem);
        } else if (nome.getText() == "Android"){
            imagem.setImageResource(R.drawable.exemploimagem);
        }

        return view;
    }


}
