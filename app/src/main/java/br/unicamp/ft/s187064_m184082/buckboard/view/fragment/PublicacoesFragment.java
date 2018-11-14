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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Publicador;
import br.unicamp.ft.s187064_m184082.buckboard.model.Mensagem;
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

                @Override
                public void postar(Postagem postagem) {
                    if(onPostShareListener != null) {
                        onPostShareListener.postar(postagem);
                    }
                }
            });

            listViewPublicacoes = view.findViewById(R.id.container_publicacoes);
            listViewPublicacoes.setAdapter(adapterListaPosts);

            Publicador.cadastrarListenerPostagem(new Publicador.ListenerPostagens() {
                @Override
                public void postagemAdicionada(Postagem postagem) {
                    Collections.reverse(listPostagem);
                    listPostagem.add(postagem);
                    Collections.reverse(listPostagem);
                    adapterListaPosts.notifyDataSetChanged();
                }

                @Override
                public void postagemRemovida(Postagem postagem) {
                    listPostagem.remove(postagem);
                    adapterListaPosts.notifyDataSetChanged();
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



    private void ordernarPostagens(List<Postagem> postagens) {
        Collections.sort(postagens, new Comparator<Postagem>() {
            public int compare(Postagem s1, Postagem s2) {
                return ((Long) s1.getTimestamp()).compareTo((Long) s2.getTimestamp());
            }
        });
    }
}
