package com.mobile.projetofinal2.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.projetofinal2.R;
import com.mobile.projetofinal2.database.DBUser;
import com.mobile.projetofinal2.model.User;

import java.util.ArrayList;
import java.util.List;


//classe responsável por manipulação da activity_tela_cadastro
public class ActivityLoginCadastroUsuario extends AppCompatActivity {

    private TextView text_cadastro;
    //criando as variaveis
    EditText edit_nome;
    EditText edit_email;
    EditText edit_senha;
    Button bt_cadastrar;

    // onCreate ActivityLoginCadastroUsuario
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user_cadastro);

        getSupportActionBar().hide();

        //faz a ligação com o id de cada objeto da activity
        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);

        //conexão e gestão do banco de dados
        DBUser.abrirBanco(this);
        DBUser.abrirOuCriarTabela(this);
        DBUser.fecharDB();

    }

    //metodo inserir registro
    public void inserirRegistro(View v){
        String st_nome, st_email, st_senha;
        st_nome = edit_nome.getText().toString();
        st_email = edit_email.getText().toString();
        st_senha = edit_senha.getText().toString();

        // valida campos obrigatórios
        if (!edit_nome.getText().toString().equals("")
                && !edit_email.getText().toString().equals("")
                && !edit_senha.getText().toString().equals("")) {

            //verifica se usuario existe no BD
            DBUser objDBUser = new DBUser();
            List<User> listUser = new ArrayList<>(objDBUser.buscarDadosLogin(edit_email.getText().toString(), this));
            int n = listUser.size();

            // if dataset retornar algum registro usuario existente
            if(n > 0){

                //percorre a lista comparando se os dados estão corretos
                for (int i=0; i<n; i++){

                    //verifica se os dados de e-mail e senha estão corretos
                    if (edit_email.getText().toString().equals(listUser.get(i).getEmail().toString())){
                        mensagem("Usuário/Email já cadastrado!");
                    }
                }

            }else{

                // popular a classe user construtor com os objetos da tela de cadastro
                User objUser = new User(st_nome, st_email, st_senha);

                // chamar a classe DBUser metodo inserir registros passando parametro User e main
                DBUser.inserirRegistro(objUser, this);

                //após fechar e limpar os campos da tela
                edit_nome.setText(null);
                edit_email.setText(null);
                edit_senha.setText(null);

                //após as verificações vai para uma nova activity
                Intent intent = new Intent(getApplicationContext(), ActivityLogin.class);
                startActivity(intent);
            }

        }else{
            mensagem("Por favor, preencha todos os campos!");
        }



    }

    //método para passar a msg
    private void mensagem(String msg){
        Toast.makeText(ActivityLoginCadastroUsuario.this, msg, Toast.LENGTH_SHORT).show();
        edit_email.requestFocus();
    }

}