package com.mobile.projetofinal2.database;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.ContentValues;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.projetofinal2.model.Msg;
import com.mobile.projetofinal2.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class DBProduto {
    // criando o database
    static SQLiteDatabase db=null;
    static Cursor cursor;

    // abrir DB bancoListaCompras
    public static void abrirBanco(Activity act){
        try{
            ContextWrapper cw= new ContextWrapper(act);
            db=cw.openOrCreateDatabase("bancoListaCompras",MODE_PRIVATE, null);
        }catch (Exception ex){
            Msg.mostrar("Erro ao abrir banco de dados bancoListaCompras",act);
        }
    }

    // fechar DB bancoListaCompras
    public static void fecharDB(){
        db.close();
    }

    // abrir ou criar tabela lista_produtos
    public static void abrirOuCriarTabela(Activity act){

        //caso a tabela não exista, vai criar
        try{
            db.execSQL("CREATE TABLE if not exists lista_produtos (id integer PRIMARY KEY, nome TEXT, quantidade integer)");
        }
        //caso apresente erro
        catch (Exception ex){
            Msg.mostrar("Erro na criação da tabela lista_produtos!", act);
        }
    }

    // insert produto
    public void inserirRegistro(Produto produto, Activity act){
        abrirBanco(act);
        //não estando vazio irá continuar inserindo os dados no BD conforme os campos abaixo
        try{
            db.execSQL("INSERT INTO lista_produtos (nome,quantidade) VALUES ('"+produto.getNomeProduto() +"', '"+produto.getQuantProduto() +"')");

        }catch (Exception ex){
            Msg.mostrar("Erro ao inserir produto!",act);
        }
        finally {
            Msg.mostrar("Produto inserido com sucesso!",act);
        }
        fecharDB();
    }

    // delete produto
    public void apagarRegistro(Produto produto, Activity act){
        abrirBanco(act);
        //não estando vazio irá continuar inserindo os dados no BD conforme os campos abaixo
        try{

        }catch (Exception ex){
            Msg.mostrar("Erro ao apgar produto!",act);
        }
        finally {
            Msg.mostrar("Produto apagado com sucesso!",act);
        }
        fecharDB();
    }

    // update produto
    public int atualizarRegistro(Produto produto, Activity act){
        abrirBanco(act);

        int statusUpdate =0;
        //não estando vazio irá continuar inserindo os dados no BD conforme os campos abaixo
        try{

            ContentValues values = new ContentValues();
            values.put("nome", produto.getNomeProduto());
            values.put("quantidade", produto.getQuantProduto());

            statusUpdate = db.update("lista_produtos", values, "id = ?", new String[] { String.valueOf(produto.getID()) });

        }catch (Exception ex){
            Msg.mostrar("Erro ao atualizar produto!",act);
        }
        finally {
            Msg.mostrar("Produto atualizado com sucesso!",act);
        }
        fecharDB();

        return statusUpdate;

    }

    //retorna lista de produtos sem fltro
    public ArrayList<Produto> buscarDadosProdutoTodos(Activity act) {
        ArrayList<Produto> listProduto = new ArrayList<>();

        try{
            abrirBanco(act);
            Cursor cursor = db.rawQuery("SELECT * FROM lista_produtos ", null);

            if (cursor.moveToFirst()) {
                do {
                    Produto objProduto = new Produto();
                    //objProduto.setID(Integer.parseInt(cursor.getString(0)));
                    objProduto.setNomeProduto(cursor.getString(1));
                    objProduto.setQuantProduto(Double.parseDouble(cursor.getString(2)));

                    listProduto.add(objProduto);

                } while (cursor.moveToNext());
            }

        }catch (Exception ex){
            Msg.mostrar("Erro ao buscarDadosProduto!",act);
        }
        fecharDB();

        return listProduto;

    }

    //retorna lista de produtos filtrando IDProduto
    public List<Produto> buscarDadosProduto(int intID, Activity act) {
        List<Produto> listProduto = new ArrayList<>();

        try{
            abrirBanco(act);
            Cursor cursor = db.rawQuery("SELECT * FROM lista_produtos WHERE id = '" + intID + "' ", null);

            if (cursor.moveToFirst()) {
                do {
                    Produto objProduto = new Produto();
                    objProduto.setID(Integer.parseInt(cursor.getString(0)));
                    objProduto.setNomeProduto(cursor.getString(1));
                    objProduto.setQuantProduto(Double.parseDouble(cursor.getString(2)));

                    listProduto.add(objProduto);

                } while (cursor.moveToNext());
            }

        }catch (Exception ex){
            Msg.mostrar("Erro ao buscarDadosProduto!",act);
        }
        fecharDB();

        return listProduto;

    }

}