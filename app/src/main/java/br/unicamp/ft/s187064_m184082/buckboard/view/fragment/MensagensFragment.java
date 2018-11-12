package br.unicamp.ft.s187064_m184082.buckboard.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Mensageiro;
import br.unicamp.ft.s187064_m184082.buckboard.model.Conversa;
import br.unicamp.ft.s187064_m184082.buckboard.model.Mensagem;
import br.unicamp.ft.s187064_m184082.buckboard.model.PreviewMensagem;


/**
 * A simple {@link Fragment} subclass.
 */
public class MensagensFragment extends Fragment {

    private View view;

    private List<Conversa> conversas;

    public MensagensFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mensagens, container, false);
        }

        conversas = new ArrayList<>();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        final ListView listv = view.findViewById(R.id.lista_mensagens);
//
//
//        mensagensList.add(new PreviewMensagem("1","Mateus", "A entrega é até segunda", R.drawable.mateus_tanaka));
//        mensagensList.add(new PreviewMensagem("2", "Sérgio", "Blz, estou fazendo o vídeo", R.drawable.sergio_filho));

        ArrayAdapter<Conversa> adapter = new ArrayAdapter<Conversa>(view.getContext(), R.layout.layout_list_view_mensagem, R.id.nome_contato, conversas) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView textViewNome = view.findViewById(R.id.nome_contato);
                TextView textViewMensagem = view.findViewById(R.id.ultima_mensagem);
                ImageView imageView = view.findViewById(R.id.foto);

                Conversa conversa = conversas.get(position);

                if(!conversa.getMensagens().containsKey(conversa.getIdUltimaMensagem())) {
                    textViewNome.setText(conversa.getPrimeiroUsuarioIdFirebase());
                    textViewMensagem.setText("Nenhuma mensagem");
                    imageView.setImageResource(R.drawable.mateus_tanaka);
                    return view;
                }

                Mensagem ultimaMensagem = conversa.getMensagens().get(conversa.getIdUltimaMensagem());


                PreviewMensagem entry = new PreviewMensagem(conversa.getId(), ultimaMensagem.getRemetenteIdFirebase(), ultimaMensagem.getMensagem(), R.drawable.mateus_tanaka);

                textViewNome.setText(entry.getNome());
                textViewMensagem.setText(entry.getMensagem());
                imageView.setImageResource(R.drawable.mateus_tanaka);

                return view;
            }
        };

        listv.setAdapter(adapter);

        atualizarConversas(adapter);

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Conversa conversa = conversas.get(position);
                ConversaFragment conversaFragment = new ConversaFragment();
                conversaFragment.setArguments(conversa.getId());
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, conversaFragment, "conversaFragment")
                        .addToBackStack(null).commit();
            }
        });

        return view;
    }

    private void atualizarConversas(ArrayAdapter<Conversa> adapter) {
        if (!Autenticador.isLogado()) return;

        Mensageiro.cadastrarListenerConversas(new Mensageiro.ListenerConversas() {
            @Override
            public void conversaAdicionada(Conversa conversa) {
                adapter.add(conversa);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void conversaRemovida(Conversa conversa) {
                Mensageiro.removerListenerMensagem(conversa.getId());
            }
        });
    }
}
