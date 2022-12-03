package com.mobile.projetofinal2.database;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mobile.projetofinal2.model.Msg;
import com.mobile.projetofinal2.model.User;

import java.util.ArrayList;
import java.util.List;

public class DBUser {
    //criando o database
    static SQLiteDatabase db=null;
    static Cursor cursor;

    public static void abrirBanco(Activity act){
        try{
            ContextWrapper cw= new ContextWrapper(act);
            db=cw.openOrCreateDatabase("bancoListaCompras",MODE_PRIVATE, null);
        }catch (Exception ex){
            Msg.mostrar("Erro ao abrir banco de dados bancoListaCompras",act);
        }
    }

    public static void fecharDB(){
        db.close();
    }

    public static void abrirOuCriarTabela(Activity act){
        //caso a tabela não exista, vai criar
        try{
            db.execSQL("CREATE TABLE if not exists usuarios (id integer PRIMARY KEY, nome TEXT, email TEXT, senha TEXT)");
        }
        //caso apresente erro
        catch (Exception ex){
            Msg.mostrar("Erro na criação da tabela usuário!", act);
        }
    }

    public static void inserirRegistro(User user, Activity act){
        abrirBanco(act);
        //não estando vazio irá continuar inserindo os dados no BD conforme os campos abaixo
        try{
            db.execSQL("INSERT INTO usuarios (nome,email,senha) VALUES ('"+user.getNome() +"', '"+user.getEmail() +"','"+user.getSenha() +"')");

        }catch (Exception ex){
            Msg.mostrar("Erro ao inserir usuário!",act);
        }
        finally {
            Msg.mostrar("Usuário inserido com sucesso",act);
        }
        fecharDB();
    }

    public List<User> buscarDadosLogin(String strEmail, String strSenha, Activity act) {
        List<User> listUser = new ArrayList<>();

        try{
            abrirBanco(act);
            Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = '" + strEmail.trim() + "' AND senha = '" + strSenha.trim() + "'", null);

            if (cursor.moveToFirst()) {
                do {
                    User objUser = new User();
                    objUser.setId(Integer.parseInt(cursor.getString(0)));
                    objUser.setNome(cursor.getString(1));
                    objUser.setEmail(cursor.getString(2));
                    objUser.setSenha(cursor.getString(3));

                    listUser.add(objUser);

                } while (cursor.moveToNext());
            }

        }catch (Exception ex){
            Msg.mostrar("Erro ao buscarDadosLogin!",act);
        }
        fecharDB();

        return listUser;

    }

    public List<User> buscarDadosLogin(String strEmail, Activity act) {
        List<User> listUser = new ArrayList<>();

        try{
            abrirBanco(act);
            Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = '" + strEmail.trim() + "'", null);

            if (cursor.moveToFirst()) {
                do {
                    User objUser = new User();
                    objUser.setId(Integer.parseInt(cursor.getString(0)));
                    objUser.setNome(cursor.getString(1));
                    objUser.setEmail(cursor.getString(2));
                    objUser.setSenha(cursor.getString(3));

                    listUser.add(objUser);

                } while (cursor.moveToNext());
            }

        }catch (Exception ex){
            Msg.mostrar("Erro ao buscarDadosLogin!",act);
        }
        fecharDB();

        return listUser;

    }

}