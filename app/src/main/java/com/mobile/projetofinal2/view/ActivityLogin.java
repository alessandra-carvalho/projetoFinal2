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

public class ActivityLogin extends AppCompatActivity {

    private TextView text_cadastro;

    // criando as variaveis
    EditText edit_email;
    EditText edit_senha;
    Button bt_acessar;

    // onCreate ActivityLogin
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        //faz a ligação com o id de cada objeto da activity
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_acessar = findViewById(R.id.bt_acessar);

        //faz com que o foco seja o e-mail na tela principal
        edit_email.requestFocus();

        //esconde a barra azul da tela
        getSupportActionBar().hide();

        // conexão e gestão do banco de dados
        DBUser.abrirBanco(this);
        DBUser.abrirOuCriarTabela(this);
        DBUser.fecharDB();

        //redireciona p/ a tela de cadastro
        IniciarTelaCadastro();

        //validação do evento do botão login
        bt_acessar.setOnClickListener(new View.OnClickListener() {
            private View view;

            // onClick para ActivityLogin
            @Override
            public void onClick(View view) {
                this.view = view;

                // valida campos obrigatórios
                if (!edit_email.getText().toString().equals("")
                        && !edit_senha.getText().toString().equals("")) {

                    // conexão e busca do usuario no banco
                    DBUser objDBUser = new DBUser();
                    List<User> listUser = new ArrayList<>(objDBUser.buscarDadosLogin(edit_email.getText().toString(),
                            edit_senha.getText().toString(), ActivityLogin.this));

                    //valida se o login e senha estão na lista de usuários cadastrados
                    int n = listUser.size();
                    boolean boolUserValido = false;

                    //verifica se usuario não existe no BD
                    if(n == 0){
                        mensagem("Usuário ou senha inválido!");
                    }

                    //percorre a lista comparando se os dados estão corretos
                    for (int i=0; i<n; i++){

                        //verifica se os dados de e-mail e senha estão corretos
                        if (edit_email.getText().toString().equals(listUser.get(i).getEmail().toString())
                                &&  edit_senha.getText().toString().equals(listUser.get(i).getSenha().toString())){
                            boolUserValido = true;
                        }
                    }

                    if (boolUserValido){
                        //após as verificações vai para uma nova activity
                        Intent intent = new Intent(getApplicationContext(), ActivityProdutoMain.class);
                        startActivity(intent);
                    }

                }else{
                    mensagem("Por favor, Insira seus dados!");
                }
            }


        });

        //chamar a tela de cadastro
        text_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //cria a intenção de que ao clicar no text de criar cadastro seja direcionado para a tela de cadastro
                Intent intent = new Intent(ActivityLogin.this, com.mobile.projetofinal2.view.ActivityLoginCadastroUsuario.class);
                startActivity(intent);
            }
        });
    }

    // faz referencia ao id do botão text_cadastro que leva para a próxima tela
    private void IniciarTelaCadastro(){
        text_cadastro = findViewById(R.id.text_cadastro);
    }

    // método para passar a msg
    private void mensagem(String msg){
        Toast.makeText(ActivityLogin.this, msg, Toast.LENGTH_SHORT).show();
        edit_email.requestFocus();
    }
}