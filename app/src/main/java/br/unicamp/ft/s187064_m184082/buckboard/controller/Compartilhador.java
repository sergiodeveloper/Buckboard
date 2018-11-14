package br.unicamp.ft.s187064_m184082.buckboard.controller;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.unicamp.ft.s187064_m184082.buckboard.model.Mensagem;
import br.unicamp.ft.s187064_m184082.buckboard.model.Postagem;

public class Compartilhador {

    public interface ListenerObterPostagem {
        void sucesso(Postagem postagem);

        void erro();
    }

    public static void compartilhar(Postagem postagemCompartilhada) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("postagem").push().setValue(postagemCompartilhada);
    }

    public static void obterPostagemCompartilhada(String idPostagem, ListenerObterPostagem listener) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("postagem").child(idPostagem).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Postagem postagem = dataSnapshot.getValue(Postagem.class);
                postagem.setId(dataSnapshot.getKey());
                listener.sucesso(postagem);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.erro();
            }
        });
    }

}
