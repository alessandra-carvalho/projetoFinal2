package com.mobile.projetofinal2.database;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
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
    public static void abrirBanco(Context context){
        try{
            ContextWrapper cw= new ContextWrapper(context);
            db=cw.openOrCreateDatabase("bancoListaCompras",MODE_PRIVATE, null);
        }catch (Exception ex){
            //Msg.mostrar("Erro ao abrir banco de dados bancoListaCompras",context);
        }
    }

    // fechar DB bancoListaCompras
    public static void fecharDB(){
        db.close();
    }

    // abrir ou criar tabela lista_produtos
    public static void abrirOuCriarTabela(Context context){

        //caso a tabela não exista, vai criar
        try{
            db.execSQL("CREATE TABLE if not exists lista_produtos (id integer PRIMARY KEY, nome TEXT, quantidade integer)");
        }
        //caso apresente erro
        catch (Exception ex){
            Msg.mostrar("Erro na criação da tabela lista_produtos!", context);
        }
    }

    // insert produto
    public void inserirRegistro(Produto produto, Context context){
        abrirBanco(context);
        //não estando vazio irá continuar inserindo os dados no BD conforme os campos abaixo
        try{
            db.execSQL("INSERT INTO lista_produtos (nome,quantidade) VALUES ('"+produto.getNomeProduto() +"', '"+produto.getQuantProduto() +"')");

        }catch (Exception ex){
            Msg.mostrar("Erro ao inserir produto!",context);
        }
        finally {
            Msg.mostrar("Produto inserido com sucesso!",context);
        }
        fecharDB();
    }

    // delete produto
    public void apagarRegistro(Produto produto, Context context){
        abrirBanco(context);
        //não estando vazio irá continuar inserindo os dados no BD conforme os campos abaixo
        try{

            db.delete("lista_produtos", "id = ?", new String[] { String.valueOf(produto.getID()) });
            db.close();

        }catch (Exception ex){
            Msg.mostrar("Erro ao apgar produto!",context);
        }
        finally {
            Msg.mostrar("Produto apagado com sucesso!",context);
        }
        fecharDB();
    }

    // update produto
    public int atualizarRegistro(Produto produto, Context context){
        abrirBanco(context);

        int statusUpdate =0;
        //não estando vazio irá continuar inserindo os dados no BD conforme os campos abaixo
        try{

            ContentValues values = new ContentValues();
            values.put("nome", produto.getNomeProduto());
            values.put("quantidade", produto.getQuantProduto());

            statusUpdate = db.update("lista_produtos", values, "id = ?", new String[] { String.valueOf(produto.getID()) });

        }catch (Exception ex){
            Msg.mostrar("Erro ao atualizar produto!",context);
        }
        finally {
            Msg.mostrar("Produto atualizado com sucesso!",context);
        }
        fecharDB();

        return statusUpdate;

    }

    //retorna lista de produtos sem fltro
    public ArrayList<Produto> buscarDadosProdutoTodos(Context context) {
        ArrayList<Produto> listProduto = new ArrayList<>();

        try{
            abrirBanco(context);
            Cursor cursor = db.rawQuery("SELECT * FROM lista_produtos ", null);

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
            Msg.mostrar("Erro ao buscarDadosProduto!",context);
        }
        fecharDB();

        return listProduto;

    }

    //retorna lista de produtos filtrando IDProduto
    public List<Produto> buscarDadosProduto(int intID, Context context) {
        List<Produto> listProduto = new ArrayList<>();

        try{
            abrirBanco(context);
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
            Msg.mostrar("Erro ao buscarDadosProduto!",context);
        }
        fecharDB();

        return listProduto;

    }

}