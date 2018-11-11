package br.unicamp.ft.s187064_m184082.buckboard.controller;

import android.annotation.SuppressLint;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

import br.unicamp.ft.s187064_m184082.buckboard.model.Conversa;
import br.unicamp.ft.s187064_m184082.buckboard.model.Mensagem;

public class Mensageiro {

    public void enviarMensagem(String remetenteId, String destinatarioId, String mensagem) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat(Mensagem.dataHoraFormat);

        final Mensagem ms = new Mensagem(remetenteId, mensagem, df.format(new Date()));
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //TODO pegar conversar by remetente e destinatario, adicionar nova mensagem no array de mensagens
        databaseReference.child("conversa").push().setValue(new Conversa(remetenteId, destinatarioId, Collections.singletonList(ms)));
    }

//    public List<Conversa> recuperarConversas (String usuarioId) {
//
//    }
}
