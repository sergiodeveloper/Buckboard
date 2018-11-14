package br.unicamp.ft.s187064_m184082.buckboard.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;
import br.unicamp.ft.s187064_m184082.buckboard.model.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserFragment extends Fragment {

    private View view;
    private EditText nomeEditText;
    private EditText sobrenomeEditText;
    private Spinner estadoCivilSpinner;
    private AppCompatRadioButton sexoMasculino;
    private AppCompatRadioButton sexoFeminino;
    private RadioGroup sexoRadioGroup;

    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_user, container, false);
        }

        nomeEditText = view.findViewById(R.id.nome_perfil);
        sobrenomeEditText = view.findViewById(R.id.sobrenome_perfil);
        sexoMasculino = view.findViewById(R.id.masculino_perfil);
        sexoFeminino = view.findViewById(R.id.feminino_perfil);
        estadoCivilSpinner = view.findViewById(R.id.estado_civil_perfil);
        sexoRadioGroup = view.findViewById(R.id.sexo_perfil);

        if(!Autenticador.isLogado()) {
            return view;
        }


            DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
            mFirebaseDatabaseReference.child("users").child(Autenticador.getIdUsuarioLogado()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                    if(usuario != null) {
                        setEstadoCivil(usuario.getEstadoCivil());
                        setSexo(usuario.getSexo());
                        nomeEditText.setText(usuario.getNome());
                        sobrenomeEditText.setText(usuario.getSobrenome());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

        AppCompatButton botaoSalvar = view.findViewById(R.id.botao_salvar_perfil);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = nomeEditText.getText().toString();
                String sobrenome = sobrenomeEditText.getText().toString();
                String sexo = sexoRadioGroup.getCheckedRadioButtonId() == R.id.feminino_perfil ? "feminino" : "masculino";
                String estadoCivil = estadoCivilSpinner.getSelectedItem().toString();

                DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
                mFirebaseDatabaseReference.child("users").child(Autenticador.getIdUsuarioLogado()).child("nome").setValue(nome);
                mFirebaseDatabaseReference.child("users").child(Autenticador.getIdUsuarioLogado()).child("sobrenome").setValue(sobrenome);
                mFirebaseDatabaseReference.child("users").child(Autenticador.getIdUsuarioLogado()).child("sexo").setValue(sexo);
                mFirebaseDatabaseReference.child("users").child(Autenticador.getIdUsuarioLogado()).child("estadoCivil").setValue(estadoCivil);

                Toast.makeText(getContext(), "Perfil atualizado", Toast.LENGTH_SHORT).show();
            }
        });

        AppCompatButton alterarSenha = view.findViewById(R.id.botao_alterar_senha);
        alterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser currentUser = mAuth.getCurrentUser();
                mAuth.sendPasswordResetEmail(currentUser.getEmail());

                Toast.makeText(getContext(), "Email de alteração de senha enviado", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void setSexo(String sexo) {
        if ("masculino".equals(sexo)) {
            sexoMasculino.setChecked(true);
        } else if ("feminino".equals(sexo)) {
            sexoFeminino.setChecked(true);
        }
    }

    private void setEstadoCivil(String estadoCivil) {
        switch (estadoCivil) {
            case "Casado(a)":
                estadoCivilSpinner.setSelection(0);
                break;
            case "Solteiro(a)":
                estadoCivilSpinner.setSelection(1);
                break;
            case "Viúvo(a)":
                estadoCivilSpinner.setSelection(2);
                break;
            case "Separado(a)":
                estadoCivilSpinner.setSelection(3);
                break;
            case "Outro":
                estadoCivilSpinner.setSelection(4);
                break;
        }


    }

}
