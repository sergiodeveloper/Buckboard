package br.unicamp.ft.s187064_m184082.buckboard;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MensagensFragment extends Fragment {

    private View view;

    public MensagensFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(view == null){
            view = inflater.inflate(R.layout.fragment_mensagens, container, false);
        }

        final List<String[]> mensagensList = new LinkedList<>();
        mensagensList.add(new String[] { "Mateus", "A entrega é até segunda"});
        mensagensList.add(new String[] { "Sérgio", "Blz, estou fazendo o vídeo"});
        mensagensList.add(new String[] { "Laís", "Pra quando é o trabalho de android"});
        mensagensList.add(new String[] { "Ramon", "Semana que vem eu vou"});
        mensagensList.add(new String[] { "Guilherme", "Falow valeu"});
        mensagensList.add(new String[] { "Alexandre", "Que sala é a palestra?"});
        mensagensList.add(new String[] { "Alex", "Valeu"});
        mensagensList.add(new String[] { "Felipe", "Precisa colocar ListView"});
        mensagensList.add(new String[] { "Kelvin", "Amanhã tem a palestra na PA07"});

        ArrayAdapter<String[]> adapter = new ArrayAdapter<String[]>(view.getContext(), R.layout.layout_list_view_mensagem, R.id.nome_contato, mensagensList){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view =  super.getView(position, convertView, parent);

                String[] entry = mensagensList.get(position);
                TextView textViewNome = view.findViewById(R.id.nome_contato);
                TextView textViewMensagem = view.findViewById(R.id.ultima_mensagem);
                textViewNome.setText(entry[0]);
                textViewMensagem.setText(entry[1]);

                return view;
            }
        };

        ListView listv = view.findViewById(R.id.lista_mensagens);
        listv.setAdapter(adapter);

        return view;
    }

}
