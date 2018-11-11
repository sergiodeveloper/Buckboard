package br.unicamp.ft.s187064_m184082.buckboard.view.fragment;


import android.annotation.SuppressLint;
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
import java.util.LinkedList;
import java.util.List;

import br.unicamp.ft.s187064_m184082.buckboard.model.Conversa;
import br.unicamp.ft.s187064_m184082.buckboard.model.Mensagem;
import br.unicamp.ft.s187064_m184082.buckboard.model.PreviewMensagem;
import br.unicamp.ft.s187064_m184082.buckboard.R;


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
            conversas = new ArrayList<>();
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        final ListView listv = view.findViewById(R.id.lista_mensagens);
        final List<PreviewMensagem> mensagensList = new LinkedList<>();


        //mensagensList.add(new PreviewMensagem("Mateus", "A entrega é até segunda", R.drawable.mateus_tanaka));
//        mensagensList.add(new PreviewMensagem("Sérgio", "Blz, estou fazendo o vídeo", R.drawable.sergio_filho));
//        mensagensList.add(new PreviewMensagem("Laís", "Pra quando é o trabalho de android", R.drawable.lais_arcaro));
//        mensagensList.add(new PreviewMensagem("Amadeu", "Semana que vem eu vou", R.drawable.amadeu_carvalho));
//        mensagensList.add(new PreviewMensagem("Guilherme", "Falow valeu", R.drawable.gilherme_santos));
//        mensagensList.add(new PreviewMensagem("Alex", "Que sala é a palestra?", R.drawable.alex_rafael));
//        mensagensList.add(new PreviewMensagem("Felipe", "Precisa colocar ListView", R.drawable.felipe_tosta));
//        mensagensList.add(new PreviewMensagem("Kelvin", "Amanhã tem a palestra na PA07", R.drawable.kelwin_falico));

        ArrayAdapter<PreviewMensagem> adapter = new ArrayAdapter<PreviewMensagem>(view.getContext(), R.layout.layout_list_view_mensagem, R.id.nome_contato, mensagensList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                PreviewMensagem entry = mensagensList.get(position);
                TextView textViewNome = view.findViewById(R.id.nome_contato);
                TextView textViewMensagem = view.findViewById(R.id.ultima_mensagem);
                ImageView imageView = view.findViewById(R.id.foto);
                textViewNome.setText(entry.getNome());
                textViewMensagem.setText(entry.getMensagem());
                imageView.setImageResource(entry.getFoto());

                return view;
            }
        };
//
//        listv.setAdapter(adapter);

        listv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ConversaFragment conversaFragment = new ConversaFragment();
                conversaFragment.setConversaId("conversaId");
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.layout.fragment_conversa, conversaFragment ,"findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        if (user != null) {
            DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

            mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot conversaSnapshot : dataSnapshot.child("conversa").getChildren()) {
                        Conversa conversa = conversaSnapshot.getValue(Conversa.class);

                        for (Mensagem mensagem : conversa.getMensagens()) {
                            mensagensList.add(new PreviewMensagem(mensagem.getRemetenteIdFirebase(), mensagem.getMensagem(), R.drawable.mateus_tanaka));
                        }

                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        return view;
    }

}
