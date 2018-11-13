package br.unicamp.ft.s187064_m184082.buckboard.controller;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.unicamp.ft.s187064_m184082.buckboard.model.Usuario;

public class Autenticador {

    public static interface CallbackLogin {
        void sucesso(Usuario usuario);

        void erro();
    }

    public interface CallbackUsuario {
        void receberUsuario(Usuario usuario);
        void erro();
    }

    private static Usuario usuarioLogado;

    public static void getUsuarioLogado(CallbackUsuario callbackUsuario) {
        if(!isLogado()) return;

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseDatabaseReference.child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Usuario usuario = dataSnapshot.getValue(Usuario.class);
                usuario.setEmail(user.getEmail());
                usuario.setIdFirebase(user.getUid());

                usuarioLogado = usuario;
                callbackUsuario.receberUsuario(usuario);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                usuarioLogado = null;
                callbackUsuario.erro();
            }
        });
    }

    public static String getIdUsuarioLogado() {
        if(!isLogado()) return null;

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser.getUid();
    }

    public static boolean isLogado() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        return currentUser != null;
    }

    public static void entrarEmailSenha(CallbackLogin callbackLogin, String email, String senha) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            FirebaseUser user = mAuth.getCurrentUser();

            DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
            mFirebaseDatabaseReference.child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    usuario.setEmail(user.getEmail());
                    usuario.setIdFirebase(user.getUid());

                    usuarioLogado = usuario;
                    callbackLogin.sucesso(usuario);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
            return;
        }

        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();

                    DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
                    mFirebaseDatabaseReference.child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Usuario usuario = dataSnapshot.getValue(Usuario.class);
                            usuario.setEmail(user.getEmail());
                            usuario.setIdFirebase(user.getUid());

                            usuarioLogado = usuario;
                            callbackLogin.sucesso(usuario);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                } else {
                    usuarioLogado = null;
                    callbackLogin.erro();
                }
            }
        });
    }

    public static void logout() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        usuarioLogado = null;
        mAuth.signOut();
    }

    public static void cadastrarUsuarioEmailSenha(CallbackLogin callbackLogin, String nome, String sobrenome,
                                                  String sexo, String estadoCivil, String email, String senha) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Usuario usuario = new Usuario();
            usuario.setEmail(currentUser.getEmail());
            if(currentUser.getPhotoUrl() != null) {
                usuario.setFoto(currentUser.getPhotoUrl().toString());
            }
            usuario.setIdFirebase(currentUser.getUid());
            usuario.setNome(nome);
            usuario.setSobrenome(sobrenome);
            usuario.setEstadoCivil(estadoCivil);
            usuario.setSexo(sexo);

            usuarioLogado = usuario;
            callbackLogin.sucesso(usuario);
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = mAuth.getCurrentUser();

                    Usuario usuario = new Usuario();
                    usuario.setEmail(user.getEmail());
                    if(user.getPhotoUrl() != null) {
                        usuario.setFoto(user.getPhotoUrl().toString());
                    }
                    usuario.setIdFirebase(user.getUid());
                    usuario.setNome(nome);
                    usuario.setSobrenome(sobrenome);
                    usuario.setEstadoCivil(estadoCivil);
                    usuario.setSexo(sexo);

                    DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
                    mFirebaseDatabaseReference.child("users").child(user.getUid()).setValue(usuario);

                    usuarioLogado = usuario;
                    callbackLogin.sucesso(usuario);
                } else {
                    usuarioLogado = null;
                    callbackLogin.erro();
                    task.getException().printStackTrace();
                }
            }
        });
    }
}

