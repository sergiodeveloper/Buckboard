package br.unicamp.ft.s187064_m184082.buckboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void openCreateNewUserView(View view) {
        Intent homeIntent = new Intent(HomeActivity.this, CreateUserActivity.class);
        startActivity(homeIntent);
        finish();
    }
}
