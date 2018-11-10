package br.unicamp.ft.s187064_m184082.buckboard.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;
import br.unicamp.ft.s187064_m184082.buckboard.model.Usuario;

public class ActivityEntrar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        Button testButton = findViewById(R.id.testButton);

        testButton.setOnClickListener(view -> {
            Intent homeIntent = new Intent(ActivityEntrar.this, MainActivity.class);
            startActivity(homeIntent);
            finish();
        });

        if(Autenticador.isLogado()) {
            abrirMainActivity();
        }
    }

    public void openCreateNewUserView(View view) {
        Intent homeIntent = new Intent(ActivityEntrar.this, ActivityCadastrarUsuario.class);
        startActivity(homeIntent);
//        finish();
    }

    public void entrar(View view) {
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String senha = ((EditText) findViewById(R.id.senha)).getText().toString();

        if(email.isEmpty()) {
            Toast.makeText(this, "Digite seu email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(senha.isEmpty()){
            Toast.makeText(this, "Digite sua senha", Toast.LENGTH_SHORT).show();
            return;
        }

        Autenticador.CallbackLogin callbackLogin = new Autenticador.CallbackLogin() {
            @Override
            public void sucesso(Usuario usuario) {
                abrirMainActivity();
            }

            @Override
            public void erro() {
                Toast.makeText(ActivityEntrar.this, "Falha ao fazer login", Toast.LENGTH_SHORT).show();
            }
        };

        Autenticador.entrarEmailSenha(callbackLogin, email, senha);

        Toast.makeText(this, "Por favor aguarde...", Toast.LENGTH_SHORT).show();
    }

    private void abrirMainActivity () {
        Intent intent = new Intent(ActivityEntrar.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
