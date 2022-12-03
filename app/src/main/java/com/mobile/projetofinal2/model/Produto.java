package com.mobile.projetofinal2.model;

import java.io.Serializable;

public class Produto implements Serializable {

    private String nomeProduto;
    private double quantProduto;

    public Produto() {

    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getQuantProduto() {
        return quantProduto;
    }

    public void setQuantProduto(int quantProduto) {
        this.quantProduto = quantProduto;
    }

    public Produto(String nomeProduto, int quantProduto) {
        this.nomeProduto = nomeProduto;
        this.quantProduto = quantProduto;
    }
}
