package com.example.yuri.app.expandableRecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuri.app.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by yuri on 22/08/17.
 */

public class RecyclerAdapter extends ExpandableRecyclerViewAdapter<TitleViewHolder, SubTitleViewHolder> {

    private Context context;
    public RecyclerAdapter(Context context, List<? extends ExpandableGroup> groups) {
        super(groups);
        this.context = context;
    }

    @Override
    public TitleViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandable_list_item_title, parent, false);
        return new TitleViewHolder(view);
    }

    @Override
    public SubTitleViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expandable_list_item_subtitle, parent, false);
        return new SubTitleViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(SubTitleViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {

        final SubTitle subTitle = ((Title) group).getItems().get(childIndex);
        holder.setSubTitletName(subTitle.getName());
    }

    @Override
    public void onBindGroupViewHolder(TitleViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGenreTitle(context, group);
    }
}
