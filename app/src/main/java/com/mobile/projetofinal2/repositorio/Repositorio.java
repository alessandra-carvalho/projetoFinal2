package com.mobile.projetofinal2.repositorio;

import com.mobile.projetofinal2.model.Produto;

import java.util.ArrayList;

public class Repositorio {

    public ArrayList<Produto> buscarProdutos(){

        ArrayList<Produto> produtos = new ArrayList<>();

        //criando oo produtos que vai aparecer na lista
        Produto p1 = new Produto("Leite",2);
        Produto p2 = new Produto("Café",1);
        Produto p3 = new Produto("Pão",1);

        produtos.add(p1);
        produtos.add(p2);
        produtos.add(p3);

        return produtos;
    }
}
