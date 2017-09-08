package com.example.yuri.app.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.example.yuri.app.R;
import com.example.yuri.app.expandableRecycler.Constant;
import com.example.yuri.app.expandableRecycler.RecyclerAdapter;
import com.example.yuri.app.expandableRecycler.SubTitle;
import com.example.yuri.app.expandableRecycler.Title;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {

    private String imageUrl[] = Constant.image;
    private String names[] = Constant.name;
    private String subNames[] = Constant.subName;
    private RatingBar avaliacao;


    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_two, container, false);

        avaliacao = (RatingBar) view.findViewById(R.id.avaliacaoStar);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewId);
        ButterKnife.bind((Activity) view.getContext());

        // Configurando o gerenciador de layout para ser uma lista.
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // Está sendo criado uma lista que será preenchida automaticamente na função @getList com o conteudo da classe @Constant.
        List<Title> list = getList();

        // Cria o adapter que irá receber os objetos da lista.
        RecyclerAdapter adapter = new RecyclerAdapter(view.getContext(), list);

        // Configurando um dividr entre linhas, para uma melhor visualização.
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        view.getContext(),
                        LinearLayoutManager.VERTICAL)
        );


        // Adiciona no adapter com o conteúdo da lista
        recyclerView.setAdapter(adapter);


        return view;
    }

    private List<Title> getList() {

        List<Title> list = new ArrayList<>();

        for (int i = 0; i < imageUrl.length; i++) {

            List<SubTitle> subTitles = new ArrayList<>();

            for (int j = 0; j < subNames.length; j++) {
                SubTitle subTitle = new SubTitle(subNames[j]);
                subTitles.add(subTitle);
            }

            Title model = new Title(names[i], subTitles, imageUrl[i]);
            list.add(model);
        }
        return list;
    }
}