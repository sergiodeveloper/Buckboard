package br.unicamp.ft.s187064_m184082.buckboard.view.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.model.Postagem;

public class PostagemView {

    private View view;

    private Postagem postagem;

    public PostagemView(Context context, Postagem postagem) {
        this.postagem = postagem;

        LayoutInflater inflater = (LayoutInflater)context.getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.fragment_publicacoes, null);

    }

    public View getView() {
        return view;
    }
}
