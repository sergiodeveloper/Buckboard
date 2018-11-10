package br.unicamp.ft.s187064_m184082.buckboard.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.unicamp.ft.s187064_m184082.buckboard.R;

public class CreateUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
    }

    public void cadastrar(View view) {
        finish();
    }
}
