package br.unicamp.ft.s187064_m184082.buckboard.controller;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.unicamp.ft.s187064_m184082.buckboard.model.Conversa;
import br.unicamp.ft.s187064_m184082.buckboard.model.Mensagem;

public class Mensageiro {

    public static interface ListenerConversas {
        void conversaAdicionada(Conversa conversa);

        void conversaRemovida(Conversa conversa);
    }

    public static interface ListenerMensagem {
        void mensagemAdicionada(Mensagem mensagem);
    }

    public static void enviarMensagem(String conversaId, String remetenteId, String mensagem) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat(Mensagem.dataHoraFormat);

        final Mensagem novaMensagem = new Mensagem(remetenteId, mensagem, df.format(new Date()));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("conversa").child(conversaId).child("mensagens").push().setValue(novaMensagem);
    }

    private static ValueEventListener listenerConversaAtual = null;

    public static void cadastrarListenerConversas(ListenerConversas listenerConversas) {
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        if(listenerConversaAtual != null) {
            mFirebaseDatabaseReference.child("conversa").removeEventListener(listenerConversaAtual);
        }

        listenerConversaAtual = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot conversaSnapshot : dataSnapshot.getChildren()) {
                    Conversa conversa = conversaSnapshot.getValue(Conversa.class);
                    conversa.setId(conversaSnapshot.getKey());

                    listenerConversas.conversaAdicionada(conversa);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };

        mFirebaseDatabaseReference.child("conversa").addValueEventListener(listenerConversaAtual);
    }

    private static Map<String, ValueEventListener> listenersMensagem = new HashMap<>();

    public static void cadastrarListenerMensagem(String idConversa, ListenerMensagem listenerMensagem) {
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

        if(listenersMensagem.containsKey(idConversa)) {
            mFirebaseDatabaseReference.child("conversa").child(idConversa).removeEventListener(listenersMensagem.get(idConversa));
            listenersMensagem.remove(idConversa);
        }

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot conversaSnapshot : dataSnapshot.getChildren()) {
                    Mensagem mensagem = conversaSnapshot.getValue(Mensagem.class);
                    mensagem.setId(conversaSnapshot.getKey());

                    listenerMensagem.mensagemAdicionada(mensagem);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        };
        listenersMensagem.put(idConversa, valueEventListener);

        mFirebaseDatabaseReference.child("conversa").child(idConversa).addValueEventListener(valueEventListener);
    }

    public static void removerListenerMensagem(String idConversa) {
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mFirebaseDatabaseReference.child("conversa").child(idConversa).removeEventListener(listenersMensagem.get(idConversa));
    }

}
