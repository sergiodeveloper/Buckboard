package br.unicamp.ft.s187064_m184082.buckboard.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;
import br.unicamp.ft.s187064_m184082.buckboard.model.Postagem;
import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.view.adapter.AdapterListaPosts;
import br.unicamp.ft.s187064_m184082.buckboard.view.fragment.MensagensFragment;
import br.unicamp.ft.s187064_m184082.buckboard.view.fragment.PublicacoesFragment;
import br.unicamp.ft.s187064_m184082.buckboard.view.fragment.SharePostFragment;
import br.unicamp.ft.s187064_m184082.buckboard.view.fragment.UserFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        PublicacoesFragment publicacoes = new PublicacoesFragment();
        ft.replace(R.id.fragmentContainer, publicacoes);
        publicacoes.setOnPostShareListener(new AdapterListaPosts.PostShareListener() {
            @Override
            public void compartilhar(Postagem postagem) {
                FragmentTransaction ft = fm.beginTransaction();
                SharePostFragment fragment = new SharePostFragment();
                fragment.setPost(postagem);
                ft.replace(R.id.fragmentContainer, fragment);
                ft.commit();
            }
        });
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_publicacoes) {
            FragmentTransaction ft = fm.beginTransaction();
            PublicacoesFragment publicacoes = new PublicacoesFragment();
            publicacoes.setOnPostShareListener(new AdapterListaPosts.PostShareListener() {
                @Override
                public void compartilhar(Postagem postagem) {
                    FragmentTransaction ft = fm.beginTransaction();
                    SharePostFragment fragment = new SharePostFragment();
                    fragment.setPost(postagem);
                    ft.replace(R.id.fragmentContainer, fragment);
                    ft.commit();
                }
            });

            ft.replace(R.id.fragmentContainer, publicacoes);
            ft.commit();
        } else if (id == R.id.nav_mensagens) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, new MensagensFragment());
            ft.commit();
        } else if (id == R.id.nav_perfil) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer, new UserFragment());
            ft.commit();
        } else if (id == R.id.nav_sair) {
            Autenticador.logout();

            Intent intent = new Intent(MainActivity.this, ActivityEntrar.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
