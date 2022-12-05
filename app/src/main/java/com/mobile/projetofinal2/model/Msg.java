package com.mobile.projetofinal2.model;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

public class Msg {

    //metodo de msg/alertas para as situações
    public static void mostrar (String txt, Context context){
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setMessage(txt);
        adb.setNeutralButton("ok!", null);
        adb.show();
    }
}