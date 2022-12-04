package com.mobile.projetofinal2.model;

import java.io.Serializable;

public class Produto implements Serializable {

    private int IdProduto;
    private String nomeProduto;
    private double quantProduto;

    public Produto() {

    }

    public Produto(String nomeProduto, double quantProduto) {
        this.nomeProduto = nomeProduto;
        this.quantProduto = quantProduto;
    }

    public int getID() {
        return IdProduto;
    }
    public void setID(int IdProduto) {
        this.IdProduto= IdProduto;
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
    public void setQuantProduto(Double quantProduto) {
        this.quantProduto = quantProduto;
    }


}
