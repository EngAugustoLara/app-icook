package com.example.yuri.app.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuri.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeRefreshLayout;


    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipelayout);

        swipeRefreshLayout.setColorSchemeResources(
                R.color.refresh,
                R.color.refresh1,
                R.color.refresh2
        );

        return view;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
                atualizarFragmentOne();
            }
        }, 2000);
    }

    private void atualizarFragmentOne() {
    }

}
