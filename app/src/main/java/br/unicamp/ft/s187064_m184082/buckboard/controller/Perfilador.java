package br.unicamp.ft.s187064_m184082.buckboard.controller;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.unicamp.ft.s187064_m184082.buckboard.model.PerfilPublico;

public class Perfilador {

    public interface ListenerPerfil {
        void sucesso(PerfilPublico perfilPublico);

        void erro();
    }

    public static void obterPerfilPublico(String idUsuario, ListenerPerfil listener) {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("users").child(idUsuario).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nome = dataSnapshot.child("nome").getValue(String.class);
                String sobrenome = dataSnapshot.child("sobrenome").getValue(String.class);
                String sexo = dataSnapshot.child("sexo").getValue(String.class);
                String foto = dataSnapshot.child("foto").getValue(String.class);

                PerfilPublico perfilPublico = new PerfilPublico(nome, sobrenome, foto, sexo);
                listener.sucesso(perfilPublico);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.erro();
            }
        });

    }
}
