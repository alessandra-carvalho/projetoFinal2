package com.mobile.projetofinal2.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.projetofinal2.R;

public class ActivityLoginUsuario extends AppCompatActivity {

    //onCreate ActivityLoginUsuario
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        //esconde o cabeçalho do projeto***
        getSupportActionBar().hide();
    }
}