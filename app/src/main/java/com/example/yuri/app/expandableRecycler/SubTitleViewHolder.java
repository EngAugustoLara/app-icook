package com.example.yuri.app.expandableRecycler;

import android.view.View;
import android.widget.TextView;

import com.example.yuri.app.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

/**
 * Created by yuri on 22/08/17.
 */

public class SubTitleViewHolder extends ChildViewHolder {

    private TextView subTitleTextView;

    public SubTitleViewHolder(View itemView) {
        super(itemView);
        subTitleTextView = (TextView) itemView.findViewById(R.id.subtitle);
    }

    public void setSubTitletName(String name) {
        subTitleTextView.setText(name);
    }
}
