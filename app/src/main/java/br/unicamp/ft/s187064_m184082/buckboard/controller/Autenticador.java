package br.unicamp.ft.s187064_m184082.buckboard.controller;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class Autenticador {

    public static interface CallbackLogin {
        void sucesso(String nome, String email, Uri foto);

        void erro();
    }

    public static void entrarEmailSenha(CallbackLogin callbackLogin, String email, String senha) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            callbackLogin.sucesso(currentUser.getDisplayName(), currentUser.getEmail(), currentUser .getPhotoUrl());
            return;
        }

        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();

                    callbackLogin.sucesso(user.getDisplayName(), user.getEmail(), user.getPhotoUrl());
                } else {
                    callbackLogin.erro();
                }
            }
        });
    }

    public static void logout() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.signOut();
    }

    public static void cadastrarUsuarioEmailSenha(CallbackLogin callbackLogin, String nome, String sobrenome,
                                                  String sexo, String estadoCivil, String email, String senha) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) return;

        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();

                    callbackLogin.sucesso(user.getDisplayName(), user.getEmail(), user.getPhotoUrl());
                } else {
                    callbackLogin.erro();
                }
            }
        });
    }
}

