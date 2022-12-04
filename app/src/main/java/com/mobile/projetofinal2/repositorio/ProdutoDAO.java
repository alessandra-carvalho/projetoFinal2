package com.mobile.projetofinal2.repositorio;

import android.app.Activity;
import com.mobile.projetofinal2.database.DBProduto;
import com.mobile.projetofinal2.model.Produto;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    // grava produto no DB
    public void salvarProduto(Produto p, Activity act){

        // conexão e gestão do banco de dados
        DBProduto.abrirBanco(act);
        DBProduto.abrirOuCriarTabela(act);

        DBProduto objDBProduto = new DBProduto();

        // verifica se é insert ou update
        if (p.getID() > 0){
            objDBProduto.atualizarRegistro(p, act);
        }else{objDBProduto.inserirRegistro(p, act);}

        DBProduto.fecharDB();

    }

    // retorna todos produtos cadastrados
    public ArrayList<Produto> buscarProdutosTodos(Activity act){
        ArrayList<Produto> listProduto = new ArrayList<>();

        // conexão e gestão do banco de dados
        DBProduto.abrirBanco(act);
        DBProduto.abrirOuCriarTabela(act);
        DBProduto objDBProduto = new DBProduto();

        listProduto = objDBProduto.buscarDadosProdutoTodos(act);

        DBProduto.fecharDB();

        return listProduto;

    }
}
