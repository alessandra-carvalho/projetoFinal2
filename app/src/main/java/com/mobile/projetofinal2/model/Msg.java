package com.mobile.projetofinal2.model;

import android.app.Activity;
import android.app.AlertDialog;

public class Msg {

    //metodo de msg/alertas para as situações
    public static void mostrar (String txt, Activity act){
        AlertDialog.Builder adb = new AlertDialog.Builder(act);
        adb.setMessage(txt);
        adb.setNeutralButton("ok!", null);
        adb.show();
    }
}