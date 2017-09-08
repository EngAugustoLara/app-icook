package com.example.yuri.app.util;

import android.content.Context;

/**
 * Created by yuri on 29/08/17.
 */

public class ManipulacaoString {

    public static String getStringResource(Context pContext, int pId) {
        return pContext.getResources().getString(pId);
    }

}
