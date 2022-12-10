package com.mobile.projetofinal2.repositorio;

import android.app.Activity;
import android.content.Context;

import com.mobile.projetofinal2.database.DBProduto;
import com.mobile.projetofinal2.model.Produto;
import com.mobile.projetofinal2.view.ActivityProdutoMain;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // grava produto no DB
    public void salvarProduto(Produto p, Activity activity){

        // conexão e gestão do banco de dados
        DBProduto.abrirBanco(activity);
        DBProduto.abrirOuCriarTabela(activity);

        DBProduto objDBProduto = new DBProduto();

        // verifica se é insert ou update
        if (p.getID() > 0){
            objDBProduto.atualizarRegistro(p, activity);
        }else{objDBProduto.inserirRegistro(p, activity);}

        //fecha o banco de dados
        DBProduto.fecharDB();
    }

    // apagar produto
    public void apagarProduto(Produto p, Activity activity){

        // conexão e gestão do banco de dados
        DBProduto.abrirBanco(activity);
        DBProduto.abrirOuCriarTabela(activity);

        DBProduto objDBProduto = new DBProduto();
        objDBProduto.apagarRegistro(p, activity);

        //fecha o banco de dados
        DBProduto.fecharDB();
    }

    // retorna todos produtos cadastrados
    public ArrayList<Produto> buscarProdutosTodos(Activity activity){
        ArrayList<Produto> listProduto = new ArrayList<>();

        // conexão e gestão do banco de dados
        DBProduto.abrirBanco(activity);
        DBProduto.abrirOuCriarTabela(activity);
        DBProduto objDBProduto = new DBProduto();

        listProduto = objDBProduto.buscarDadosProdutoTodos(activity);

        DBProduto.fecharDB();

        return listProduto;

    }
}
