package com.example.yuri.app.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;

import com.example.yuri.app.R;
import com.example.yuri.app.adapter.AdapterTwoFragmentPersonalizadoAdapter;
import com.example.yuri.app.expandableRecycler.Constant;
import com.example.yuri.app.expandableRecycler.SubTitle;
import com.example.yuri.app.expandableRecycler.Title;
import com.example.yuri.app.model.TwoFragmentItem;
import com.example.yuri.app.util.Mensagem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {

    private String imageUrl[] = Constant.image;
    private String names[] = Constant.name;
    private String subNames[] = Constant.subName;
    private RatingBar avaliacao;
    private Context context;


    public TwoFragment(Context context) {
        this.context = context;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_two, container, false);

        avaliacao = (RatingBar) view.findViewById(R.id.avaliacaoStar);
//
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewId);
//        ButterKnife.bind((Activity) view.getContext());
//
//        // Configurando o gerenciador de layout para ser uma lista.
//        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
//        recyclerView.setLayoutManager(layoutManager);
//
//        // Está sendo criado uma lista que será preenchida automaticamente na função @getList com o conteudo da classe @Constant.
//        List<Title> list = getList();
//
//        // Cria o adapter que irá receber os objetos da lista.
//        RecyclerAdapter adapter = new RecyclerAdapter(view.getContext(), list);
//
//        // Configurando um dividr entre linhas, para uma melhor visualização.
//        recyclerView.addItemDecoration(
//                new DividerItemDecoration(
//                        view.getContext(),
//                        LinearLayoutManager.VERTICAL)
//        );
//
//
//        // Adiciona no adapter com o conteúdo da lista
//        recyclerView.setAdapter(adapter);

        ListView lista = (ListView) view.findViewById(R.id.lista);
        List<TwoFragmentItem> twoFragmentItems = preencherList();

        AdapterTwoFragmentPersonalizadoAdapter adapter = new AdapterTwoFragmentPersonalizadoAdapter(
                (Activity) view.getContext(),
                twoFragmentItems
        );

        lista.setAdapter(adapter);


        return view;
    }

    private List<TwoFragmentItem> preencherList() {

        TwoFragmentItem twoFragmentItem = new TwoFragmentItem("Java");
        TwoFragmentItem twoFragmentItem1 = new TwoFragmentItem("HTML e CSS");
        TwoFragmentItem twoFragmentItem2 = new TwoFragmentItem("Android");
        TwoFragmentItem twoFragmentItem3 = new TwoFragmentItem("Java");
        TwoFragmentItem twoFragmentItem4 = new TwoFragmentItem("HTML e CSS");
        TwoFragmentItem twoFragmentItem5 = new TwoFragmentItem("Android");
        TwoFragmentItem twoFragmentItem6= new TwoFragmentItem("Java");
        TwoFragmentItem twoFragmentItem7 = new TwoFragmentItem("HTML e CSS");
        TwoFragmentItem twoFragmentItem8 = new TwoFragmentItem("Android");
        TwoFragmentItem twoFragmentItem9= new TwoFragmentItem("Java");
        TwoFragmentItem twoFragmentItem10 = new TwoFragmentItem("HTML e CSS");
        TwoFragmentItem twoFragmentItem11 = new TwoFragmentItem("Android");
        TwoFragmentItem twoFragmentItem12 = new TwoFragmentItem("Java");
        TwoFragmentItem twoFragmentItem13 = new TwoFragmentItem("HTML e CSS");
        TwoFragmentItem twoFragmentItem14 = new TwoFragmentItem("Android");

        ArrayList<TwoFragmentItem> itens = new ArrayList<>();
        itens.add(twoFragmentItem);
        itens.add(twoFragmentItem1);
        itens.add(twoFragmentItem2);
        itens.add(twoFragmentItem3);
        itens.add(twoFragmentItem4);
        itens.add(twoFragmentItem5);
        itens.add(twoFragmentItem6);
        itens.add(twoFragmentItem7);
        itens.add(twoFragmentItem8);
        itens.add(twoFragmentItem9);
        itens.add(twoFragmentItem10);
        itens.add(twoFragmentItem11);
        itens.add(twoFragmentItem12);
        itens.add(twoFragmentItem13);
        itens.add(twoFragmentItem14);


        return itens;
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