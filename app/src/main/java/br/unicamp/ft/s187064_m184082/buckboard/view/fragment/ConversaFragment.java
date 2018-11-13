package br.unicamp.ft.s187064_m184082.buckboard.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
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

    private List<Mensagem> mensagens = new ArrayList<>();

    private View view;

    private LinearLayout listaMensagens;

    public ConversaFragment() {
        // Required empty public constructor
    }

    public void setArguments(String conversaId) {
        this.conversaId = conversaId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_conversa, container, false);
        }

        if (!Autenticador.isLogado()) {
            return view;
        }

        listaMensagens = view.findViewById(R.id.mensagens_conversa);

        Mensageiro.cadastrarListenerMensagem(conversaId, new Mensageiro.ListenerMensagem() {

            @Override
            public void mensagemAdicionada(Mensagem mensagem) {
                mensagens.add(mensagem);
                ordernarMensagens(mensagens);
                atualizarView();
            }
        });

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
    }


    private void ordernarMensagens(List<Mensagem> mensagens) {
        Collections.sort(mensagens, new Comparator<Mensagem>() {
            public int compare(Mensagem s1, Mensagem s2) {
                return s1.getDataHora().compareTo(s2.getDataHora());
            }
        });
    }

}
