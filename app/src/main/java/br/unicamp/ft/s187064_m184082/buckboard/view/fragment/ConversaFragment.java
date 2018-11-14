package br.unicamp.ft.s187064_m184082.buckboard.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Mensageiro;
import br.unicamp.ft.s187064_m184082.buckboard.model.Mensagem;
import br.unicamp.ft.s187064_m184082.buckboard.view.component.MensagemView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversaFragment extends Fragment {

    private String conversaId;
    private String conversaNome;

    private List<Mensagem> mensagens = new ArrayList<>();
    private View view;
    private LinearLayout listaMensagens;
    private ScrollView scrollView;
    private TextView textViewNome;

    public ConversaFragment() {
        // Required empty public constructor
    }

    public void setArguments(String conversaId, String conversaNome) {
        this.conversaId = conversaId;
        this.conversaNome = conversaNome;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_conversa, container, false);
            textViewNome = view.findViewById(R.id.nome_conversa);
            textViewNome.setText(conversaNome);

            Mensageiro.cadastrarListenerMensagem(conversaId, new Mensageiro.ListenerMensagem() {
                @Override
                public void mensagemAdicionada(Mensagem mensagem) {
                    mensagens.add(mensagem);
                    ordernarMensagens(mensagens);
                    atualizarView();
                }
            });
        }

        if (!Autenticador.isLogado()) {
            return view;
        }

        listaMensagens = view.findViewById(R.id.mensagens_conversa);
        scrollView = view.findViewById(R.id.scroll_mensagens);

        Button enviarBtn = view.findViewById(R.id.btn_enviar_msg);
        enviarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mensagem = view.findViewById(R.id.mensagem_digitada);

                Mensageiro.enviarMensagem(conversaId, Autenticador.getIdUsuarioLogado(), mensagem.getText().toString());
                mensagem.setText(null);
            }
        });

        return view;
    }

    private void atualizarView(){
        listaMensagens.removeAllViews();

        for (Mensagem mensagem : mensagens) {
            listaMensagens.addView(new MensagemView(getContext(), mensagem));
        }


        scrollView.fullScroll(View.FOCUS_DOWN);
    }


    private void ordernarMensagens(List<Mensagem> mensagens) {
        Collections.sort(mensagens, new Comparator<Mensagem>() {
            public int compare(Mensagem s1, Mensagem s2) {
                return ((Long) s1.getTimestamp()).compareTo((Long) s2.getTimestamp());
            }
        });
    }

}
