package br.unicamp.ft.s187064_m184082.buckboard.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;

public class ActivityCadastrarUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);
    }

    public void cadastrar(View view) {

        String nome = ((EditText) findViewById(R.id.nome)).getText().toString();
        String sobrenome = ((EditText) findViewById(R.id.sobrenome)).getText().toString();
        String sexo = ((RadioGroup) findViewById(R.id.sexo)).getCheckedRadioButtonId() == R.id.feminino? "feminino" : "masculino";
        String estadoCivil = ((Spinner) findViewById(R.id.estadoCivil)).getSelectedItem().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String senha = ((EditText) findViewById(R.id.senha)).getText().toString();
        String senhaRepita = ((EditText) findViewById(R.id.senha_repita)).getText().toString();

        if(nome.isEmpty()) {
            Toast.makeText(this, "Digite seu nome", Toast.LENGTH_SHORT).show();
            return;
        }
        if(sobrenome.isEmpty()) {
            Toast.makeText(this, "Digite seu sobrenome", Toast.LENGTH_SHORT).show();
            return;
        }
        if(sexo.isEmpty()) {
            Toast.makeText(this, "Informe seu sexo", Toast.LENGTH_SHORT).show();
            return;
        }
        if(estadoCivil.isEmpty()) {
            Toast.makeText(this, "Informe seu estado civil", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.isEmpty()) {
            Toast.makeText(this, "Digite seu email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!senha.equals(senhaRepita)) {
            Toast.makeText(this, "As senhas não são iguais", Toast.LENGTH_SHORT).show();
            return;
        }

        if(senha.length() < 6) {
            Toast.makeText(this, "A senha é muito fraca", Toast.LENGTH_SHORT).show();
            return;
        }


        Autenticador.CallbackLogin callbackLogin = new Autenticador.CallbackLogin() {
            @Override
            public void sucesso(String nome, String email, Uri foto) {
                // Abrir main activity
                Intent intent = new Intent(ActivityCadastrarUsuario.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void erro() {
                Toast.makeText(ActivityCadastrarUsuario.this, "Falha ao cadastrar", Toast.LENGTH_SHORT).show();
            }
        };

        Autenticador.cadastrarUsuarioEmailSenha(callbackLogin, nome, sobrenome, sexo, estadoCivil, email, senha);

        Toast.makeText(this, "Por favor, aguarde...", Toast.LENGTH_SHORT).show();
    }
}
