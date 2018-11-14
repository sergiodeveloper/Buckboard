package br.unicamp.ft.s187064_m184082.buckboard.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.unicamp.ft.s187064_m184082.buckboard.model.Postagem;

public class Publicador {

    public static interface ListenerPostagens {
        void postagemAdicionada(Postagem postagem);

        void postagemRemovida(Postagem postagem);
    }

    public static void publicarPostagem(Postagem postagem) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("postagem").push().setValue(postagem);
    }

    private static ChildEventListener listenerPostagem = null;

    public static void cadastrarListenerPostagem(ListenerPostagens listenerPostagens) {
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        if (listenerPostagem != null) {
            mFirebaseDatabaseReference.child("postagem").removeEventListener(listenerPostagem);
        }

        listenerPostagem = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot postagemSnapshot : dataSnapshot.getChildren()) {
                    Postagem postagem = postagemSnapshot.getValue(Postagem.class);
                    postagem.setId(postagemSnapshot.getKey());

                    listenerPostagens.postagemAdicionada(postagem);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };


        mFirebaseDatabaseReference.child("postagem").addChildEventListener(listenerPostagem);
    }
}
