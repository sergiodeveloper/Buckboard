package br.unicamp.ft.s187064_m184082.buckboard.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Mensageiro;
import br.unicamp.ft.s187064_m184082.buckboard.model.Conversa;
import br.unicamp.ft.s187064_m184082.buckboard.model.Mensagem;
import br.unicamp.ft.s187064_m184082.buckboard.model.PreviewMensagem;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConversaFragment extends Fragment {

    private String conversaId;

    private View view;

    public ConversaFragment() {
        // Required empty public constructor
    }

    public void setArguments(String conversaId){
        this.conversaId = conversaId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_conversa, container, false);
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();

            mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Conversa conversa = dataSnapshot.child("conversa").child(conversaId).getValue(Conversa.class);

                        EditText lista = view.findViewById(R.id.mensagens_conversa);
                        for (Mensagem mensagem : conversa.getMensagens().values()) {
                            lista.append("\n");
                            lista.append(mensagem.getRemetenteIdFirebase() + ": " + mensagem.getMensagem());
                        }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        Button enviarBtn = view.findViewById(R.id.btn_enviar_msg);
        enviarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mensagem = view.findViewById(R.id.mensagem_digitada);
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();

                Mensageiro.enviarMensagem(conversaId, user.getUid(), mensagem.getText().toString());
            }
        });
        
        return view;
    }

}
