package com.mobile.projetofinal2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobile.projetofinal2.R;
import com.mobile.projetofinal2.database.DBProduto;
import com.mobile.projetofinal2.model.Produto;
import com.mobile.projetofinal2.repositorio.ProdutoDAO;

import java.util.ArrayList;

public class ActivityProdutoCadastro extends AppCompatActivity implements View.OnClickListener{

    private EditText editText_nome_produto;
    private EditText editText_quant_produto;
    private Button bt_add_produto;

    //referencia ao ArrayList Produto
    private ArrayList<Produto> produtos;

    //onCreate CadastroProdActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_cadastro);

        this.editText_nome_produto = findViewById(R.id.editText_nome_produto);
        this.editText_quant_produto = findViewById(R.id.editText_quant_produto);
        this.bt_add_produto = findViewById(R.id.bt_add_produto);

        //cria envento de click
        this.bt_add_produto.setOnClickListener(this);

        //verificação se intenet foi passada
        if(getIntent().getExtras() != null){
            this.produtos = (ArrayList<Produto>) getIntent().getSerializableExtra("listaProdutos");
        }

    }

    //onClick CadastroProdActivity
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_add_produto:

                // valida campos obrigatóris
                if (!this.editText_nome_produto.getText().toString().equals("")
                        && !this.editText_quant_produto.getText().toString().equals("")) {

                    String nome = this.editText_nome_produto.getText().toString();
                    double qtd_produto = Double.parseDouble(this.editText_quant_produto.getText().toString());

                    // valida quantidade menor/igual zero
                    if (qtd_produto <= 0){
                        Toast.makeText(this,"Quantidade precisa ser maior que zero!",Toast.LENGTH_SHORT).show();
                    }else{
                        // invoca método para add produto
                        this.adicionarProduto(nome, qtd_produto);
                    }

                    // valida se campos vazios/nulos
                }else{
                    Toast.makeText(this,"Por favor, preencha os campos solicitados!",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //insere produtos no lista
    private void adicionarProduto(String nomProduto, Double qtdProdutos){

        Produto produto = new Produto(nomProduto, qtdProdutos);
        this.produtos.add(produto);
        Toast.makeText(this,"Produto cadastrado com sucesso!",Toast.LENGTH_SHORT).show();

        //grava produto usando ProdutoDAO
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.salvarProduto(produto, this);

        // criar intent de retorno para ser tratada pelo ActivityResult da Main
        Intent returnIntent = new Intent(this, ActivityProdutoMain.class);
        returnIntent.putExtra("listaProdutosAtualizada",  this.produtos);
        setResult(RESULT_OK, returnIntent);
        finish();

    }
}