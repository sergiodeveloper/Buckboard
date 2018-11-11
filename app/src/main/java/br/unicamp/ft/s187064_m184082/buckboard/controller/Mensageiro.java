package br.unicamp.ft.s187064_m184082.buckboard.controller;

import android.annotation.SuppressLint;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.unicamp.ft.s187064_m184082.buckboard.model.Mensagem;

public class Mensageiro {

    public static void enviarMensagem(String conversaId, String remetenteId, String mensagem) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat(Mensagem.dataHoraFormat);

        final Mensagem novaMensagem = new Mensagem(remetenteId, mensagem, df.format(new Date()));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("conversa").child(conversaId).child("mensagens").push().setValue(novaMensagem);
    }

}
