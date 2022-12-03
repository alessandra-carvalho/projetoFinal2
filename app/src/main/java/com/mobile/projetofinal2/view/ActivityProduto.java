package com.mobile.projetofinal2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.mobile.projetofinal2.R;
import com.mobile.projetofinal2.model.Produto;

public class ActivityProduto extends AppCompatActivity {

    Produto p;
    private TextView textview_nomeProdSelecionado;
    private TextView textview_quantProdSelecionado;

    //onCreate ActivityProduto
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_selecionado);

        //esconde a barra no cabeçalho
        getSupportActionBar().hide();

        //recuperando as referencias
        this.textview_nomeProdSelecionado = findViewById(R.id.textview_nomeProdSelecionado);
        this.textview_quantProdSelecionado = findViewById(R.id.textview_quantProdSelecionado);

        //verifica se há parametros passados na intent
        if(getIntent().getExtras() != null){
            //recupera o objeto
            p = (Produto) getIntent().getSerializableExtra("produto");

            // #################################################################
            // ************* verificar passar para int **************************
            // #################################################################
            //seta os produtos
            this.textview_nomeProdSelecionado.setText(p.getNomeProduto());
            this.textview_quantProdSelecionado.setText(" " + String.format("%.2f",p.getQuantProduto()));

        }
    }
}