package br.unicamp.ft.s187064_m184082.buckboard.view.component;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import br.unicamp.ft.s187064_m184082.buckboard.controller.Sessao;
import br.unicamp.ft.s187064_m184082.buckboard.model.Mensagem;

public class MensagemView extends LinearLayout {

    private Mensagem mensagem;

    public MensagemView(Context context, Mensagem mensagem) {
        super(context);
        this.mensagem = mensagem;

        this.setOrientation(HORIZONTAL);
        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));


        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        if(mensagem.getRemetente() == Sessao.getUsuarioLogado()) {
            // Minha mensagem
            params.setMargins(40, 0, 0, 0);
        }
        else {
            params.setMargins(0, 0, 40, 0);
        }
        TextView mensagemTextView = new TextView(context);
        mensagemTextView.setText(mensagem.getMensagem());
        mensagemTextView.setBackgroundColor(Color.parseColor("#0099ff"));
        mensagemTextView.setLayoutParams(params);

        this.addView(mensagemTextView);
    }

}
