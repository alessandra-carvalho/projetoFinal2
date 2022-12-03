package com.mobile.projetofinal2.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mobile.projetofinal2.R;
import com.mobile.projetofinal2.adapter.AdapterProdutos;
import com.mobile.projetofinal2.model.Produto;
import com.mobile.projetofinal2.repositorio.Repositorio;

import java.util.ArrayList;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //referencia RecyclerView
    private RecyclerView recyclerview_produtos;
    private Button bt_inserirProduto;
    private ArrayList<Produto> produtos;
    private AdapterProdutos adapterProdutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //esconde a barra no cabeçalho
        getSupportActionBar().hide();

        this.bt_inserirProduto = findViewById(R.id.bt_inserirProduto);
        //configura o evento de click
        this.bt_inserirProduto.setOnClickListener(this);
        this.recyclerview_produtos=findViewById(R.id.recyclerview_produtos);

        //gerencia o layout
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,false);
        this.recyclerview_produtos.setLayoutManager(linearLayoutManager);

        //traz dados do repositorio
        Repositorio repositorio = new Repositorio();
        this.produtos = new ArrayList<>();
        this.produtos = repositorio.buscarProdutos();
        this.adapterProdutos = new AdapterProdutos(this,this.produtos);
        this.recyclerview_produtos.setAdapter(this.adapterProdutos);
    }

    //implementa o metodo OnClickListener
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_inserirProduto:
                //criar a intenção de ir p/ prox activity de cadastro
                Intent intent = new Intent (this,CadastroProdActivity.class);

                //parametros
                intent.putExtra("listaProdutos",this.produtos);

                //chama a activity e obtem uma resposta de quem foi chamado
                //startActivityForResult(intent,1);


                activityResultLaunch.launch(intent);

                break;
        }
    }

    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {


                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        //Bundle bundle = result.getData().getExtras();
                        produtos = (ArrayList<Produto>) result.getData().getSerializableExtra("listaProdutosAtualizada");

                        adapterProdutos.atualizaAdapter(produtos);

                    }

                }
            });


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        switch (requestCode){
//            this.produtos
//            case 1:
//        }
//    }
}