package com.example.yuri.app.expandableRecycler;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by yuri on 22/08/17.
 */

public class Title extends ExpandableGroup<SubTitle> {

    private String imageUrl;

    public Title(String title, List<SubTitle> items, String imageUrl) {
        super(title, items);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

