package br.unicamp.ft.s187064_m184082.buckboard.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Publicador;
import br.unicamp.ft.s187064_m184082.buckboard.model.Postagem;
import br.unicamp.ft.s187064_m184082.buckboard.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublicacoesFragment extends Fragment {

    private View view;

    private PostShareListener onPostShareListener;

    private AppCompatButton botaoPublicar;

    private EditText entradaPublicacao;

    private List<Postagem> listPostagem = new ArrayList<>();

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

        if(view == null) {
            view = inflater.inflate(R.layout.fragment_publicacoes, container, false);

            Publicador.cadastrarListenerPostagem(new Publicador.ListenerPostagens() {
                @Override
                public void postagemAdicionada(Postagem postagem) {
                    listPostagem.add(postagem);
                    atualizarView();
                }

                @Override
                public void postagemRemovida(Postagem postagem) {

                }
            });

            view.findViewById(R.id.compartilhar1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onPostShareListener != null) {
                        //Postagem postagem = new Postagem("Olha o que eu construí!", "");

                        //onPostShareListener.compartilhar(postagem);
                    }
                }
            });

            botaoPublicar = view.findViewById(R.id.botao_compartilhar);
            entradaPublicacao = view.findViewById(R.id.entrada_publicacao);
            botaoPublicar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String publicacaoNova = entradaPublicacao.getText().toString().trim();
                    if(publicacaoNova.isEmpty()) {
                        return;
                    }

                    Postagem postagem = new Postagem(publicacaoNova, null, Autenticador.getIdUsuarioLogado(), "normal", null);

                    Publicador.publicarPostagem(postagem);
                }
            });

        }

        return view;
    }

    private void atualizarView() {

    }

    public void setOnPostShareListener(PostShareListener onPostShareListener) {
        this.onPostShareListener = onPostShareListener;
    }
}
