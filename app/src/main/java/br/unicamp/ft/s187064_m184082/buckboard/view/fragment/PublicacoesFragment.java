package br.unicamp.ft.s187064_m184082.buckboard.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.unicamp.ft.s187064_m184082.buckboard.model.Postagem;
import br.unicamp.ft.s187064_m184082.buckboard.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublicacoesFragment extends Fragment {

    private View view;

    private PostShareListener onPostShareListener;

    public PublicacoesFragment() {
        // Required empty public constructor
    }

    public static interface PostShareListener {
        public void compartilhar(Postagem postagem);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_publicacoes, container, false);

        view.findViewById(R.id.compartilhar1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onPostShareListener != null) {
                    Postagem postagem = new Postagem("Olha o que eu construí!", R.drawable.cerca);

                    onPostShareListener.compartilhar(postagem);
                }
            }
        });

        return view;
    }

    public void setOnPostShareListener(PostShareListener onPostShareListener) {
        this.onPostShareListener = onPostShareListener;
    }
}
