package com.mobile.projetofinal2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.projetofinal2.R;
import com.mobile.projetofinal2.model.Produto;

public class ActivityProduto extends AppCompatActivity implements View.OnClickListener{

    Produto p;
    //referencia o que tem no xml activity_produto_selecionado
    private TextView textview_nomeProdSelecionado;
    private TextView textview_quantProdSelecionado;
    private EditText edit_text_nomeProduto;
    private EditText edit_text_quantSelecionado;
    private Button btnAlterarProduto;

    //onCreate ActivityProduto
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_selecionado);

        //esconde a barra no cabeçalho
        getSupportActionBar().hide();

//        //recuperando as referencias do xml
//        this.textview_nomeProdSelecionado = findViewById(R.id.textview_nomeProdSelecionado);
//        this.textview_quantProdSelecionado = findViewById(R.id.textview_quantProdSelecionado);

        //verifica se há parametros passados na intent
        if(getIntent().getExtras() != null){
            //recupera o objeto
            this.p = (Produto) getIntent().getSerializableExtra("produto");

        //verificar se o user clicou no botão p/ editar o produto
        if(getIntent().getExtras().getString("editar")!=null){
            this.iniciarTelaProdEditavel();
        } else{
            this.iniciarTelaProdSelecionado();
        }
     }
    }
    //cria o evento de click do botão alterar produto
    @Override
    public void onClick(View v) {
        Toast.makeText(this, "clicou no botão de alterar", Toast.LENGTH_SHORT).show();
    }
    private void iniciarTelaProdSelecionado(){
        //link com as referencias dos componentes do xml
        this.textview_nomeProdSelecionado = findViewById(R.id.textview_nomeProdSelecionado);
        this.textview_quantProdSelecionado = findViewById(R.id.textview_quantProdSelecionado);
        //habilita a visualização
        this.textview_nomeProdSelecionado.setVisibility(View.VISIBLE);
        this.textview_quantProdSelecionado.setVisibility(View.VISIBLE);

        //seta os produtos
        this.textview_nomeProdSelecionado.setText(p.getNomeProduto());
        //this.textview_quantProdSelecionado.setText(p.getQuantProduto());
        this.textview_quantProdSelecionado.setText(" " + String.format("%.0f",p.getQuantProduto()));
    }
    private void iniciarTelaProdEditavel() {
        //link com as referencias dos componentes do xml
        this.edit_text_nomeProduto = findViewById(R.id.edit_text_nomeProduto);
        this.edit_text_quantSelecionado = findViewById(R.id.edit_text_quantSelecionado);
        this.btnAlterarProduto = findViewById(R.id.btnAlterarProduto);

        //habilita a visualização
        this.edit_text_nomeProduto.setVisibility(View.VISIBLE);
        this.edit_text_quantSelecionado.setVisibility(View.VISIBLE);
        this.btnAlterarProduto.setVisibility(View.VISIBLE);

        //cria o evento de click do botão alterar
        this.btnAlterarProduto.setOnClickListener(this);

        //traz os valores que vem do objeto produto
        this.edit_text_nomeProduto.setText(this.p.getNomeProduto());
        this.edit_text_quantSelecionado.setText(String.valueOf(this.p.getQuantProduto()));

    }
 }