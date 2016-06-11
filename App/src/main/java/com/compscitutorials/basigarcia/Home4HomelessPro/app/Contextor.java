package com.compscitutorials.basigarcia.Home4HomelessPro.app;

import android.content.Context;

public class Contextor {

    private static Contextor contextor;

    public static Contextor getInstance() {
        if (contextor == null)
            contextor = new Contextor();

        return contextor;
    }

    private Context context;

    public void init(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return this.context;
    }
}
