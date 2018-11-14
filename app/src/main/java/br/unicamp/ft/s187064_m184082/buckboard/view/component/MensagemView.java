package br.unicamp.ft.s187064_m184082.buckboard.view.component;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;
import br.unicamp.ft.s187064_m184082.buckboard.model.Mensagem;

public class MensagemView extends LinearLayout {

    private Mensagem mensagem;

    public MensagemView(Context context, Mensagem mensagem) {
        super(context);
        this.mensagem = mensagem;

        this.setOrientation(HORIZONTAL);
        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

        TextView mensagemTextView = new TextView(context);
        mensagemTextView.setText(mensagem.getMensagem());

        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        if (mensagem.getRemetenteIdFirebase().equals(Autenticador.getIdUsuarioLogado())) {
            // Minha mensagem
            params.setMargins(70, 15, 5, 15);
            mensagemTextView.setBackgroundColor(Color.parseColor("#55ccff"));
        } else {
            params.setMargins(5, 15, 70, 15);
            mensagemTextView.setBackgroundColor(Color.parseColor("#dddddd"));
        }
        mensagemTextView.setLayoutParams(params);
        mensagemTextView.setTextSize(20);

        this.addView(mensagemTextView);
    }

}
