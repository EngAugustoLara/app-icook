package com.example.yuri.app.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

public class Mensagem {

    public static void toastShort(Context context, String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }

    public static void toastLong(Context context, String mensagem) {
        Toast.makeText(context, mensagem, Toast.LENGTH_LONG).show();
    }

    public static void snackShort(View view, String mensagem){
        Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT).setAction("Action", null).show();
    }

    public static void snackLong(View view, String mensagem){
        Snackbar.make(view, mensagem, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

}