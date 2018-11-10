package br.unicamp.ft.s187064_m184082.buckboard.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.unicamp.ft.s187064_m184082.buckboard.R;

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
    }

    public void openCreateNewUserView(View view) {
        Intent homeIntent = new Intent(ActivityEntrar.this, CreateUserActivity.class);
        startActivity(homeIntent);
//        finish();
    }
}
