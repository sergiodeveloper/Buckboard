package br.unicamp.ft.s187064_m184082.buckboard.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Publicador;
import br.unicamp.ft.s187064_m184082.buckboard.model.Postagem;
import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.view.adapter.AdapterListaPosts;


/**
 * A simple {@link Fragment} subclass.
 */
public class PublicacoesFragment extends Fragment {

    private View view;

    private AppCompatButton botaoPublicar;

    private EditText entradaPublicacao;

    private ListView listViewPublicacoes;

    private List<Postagem> listPostagem = new ArrayList<>();
    private AdapterListaPosts adapterListaPosts;
    private AdapterListaPosts.PostShareListener onPostShareListener;


    public PublicacoesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if(view == null) {
            view = inflater.inflate(R.layout.fragment_publicacoes, container, false);

            adapterListaPosts = new AdapterListaPosts(listPostagem, getContext());
            adapterListaPosts.setOnPostShareListener(new AdapterListaPosts.PostShareListener() {
                @Override
                public void compartilhar(Postagem postagem) {
                    if(onPostShareListener != null) {
                        onPostShareListener.compartilhar(postagem);
                    }
                }
            });

            listViewPublicacoes = view.findViewById(R.id.container_publicacoes);
            listViewPublicacoes.setAdapter(adapterListaPosts);

            Publicador.cadastrarListenerPostagem(new Publicador.ListenerPostagens() {
                @Override
                public void postagemAdicionada(Postagem postagem) {
                    listPostagem.add(postagem);
                    adapterListaPosts.notifyDataSetChanged();
                }

                @Override
                public void postagemRemovida(Postagem postagem) {
                    listPostagem.remove(postagem);
                    adapterListaPosts.notifyDataSetChanged();
                }
            });

            botaoPublicar = view.findViewById(R.id.btn_publicar);
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
                    entradaPublicacao.setText("");
                }
            });

        }

        return view;
    }

    public void setOnPostShareListener(AdapterListaPosts.PostShareListener onPostShareListener) {
        this.onPostShareListener = onPostShareListener;
    }

    public AdapterListaPosts.PostShareListener getOnPostShareListener() {
        return onPostShareListener;
    }
}
