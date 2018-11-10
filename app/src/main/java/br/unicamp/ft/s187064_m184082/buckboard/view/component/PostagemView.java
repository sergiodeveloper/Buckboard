package br.unicamp.ft.s187064_m184082.buckboard.view.component;

import android.content.Context;
import android.widget.LinearLayout;

import br.unicamp.ft.s187064_m184082.buckboard.model.Postagem;

public class PostagemView extends LinearLayout {

    private Postagem postagem;

    public PostagemView(Context context, Postagem postagem) {
        super(context);
        this.postagem = postagem;
    }


}
