package br.unicamp.ft.s187064_m184082.buckboard.view.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Mensageiro;
import br.unicamp.ft.s187064_m184082.buckboard.model.Conversa;


/**
 * A simple {@link Fragment} subclass.
 */
public class MensagensFragment extends Fragment {

    private View view;

    private List<Conversa> conversas;

    private EditText input;

    public MensagensFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_mensagens, container, false);

            View addConversa = view.findViewById(R.id.add_nova_conversa);

            addConversa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exibirDialogNovaConversa();
                }
            });
        }

        conversas = new ArrayList<>();

        final ListView listv = view.findViewById(R.id.lista_mensagens);

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
                    textViewNome.setText(conversa.getNome());
                    textViewMensagem.setText(conversa.getIdUltimaMensagem());
                    imageView.setImageResource(R.mipmap.ic_launcher);
                    return view;
                }

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
                conversaFragment.setArguments(conversa.getId(), conversa.getNome());
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

                for(Conversa conversaVisivel : conversas){
                    //Se for edição de mensagem
                    if(conversa.getId().equals(conversaVisivel.getId())) {
                        conversaVisivel.setId(conversa.getIdUltimaMensagem());
                        adapter.remove(conversaVisivel);
                        adapter.add(conversa);

                        adapter.sort(new Comparator<Conversa>() {
                            @Override
                            public int compare(Conversa c1, Conversa c2) {
                                return ((Long) c1.getTimestamp()).compareTo((Long) c2.getTimestamp());
                            }
                        });

                        adapter.notifyDataSetChanged();
                        return;
                    }
                }

                //Caso a mensagem nao existe
                adapter.add(conversa);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void conversaRemovida(Conversa conversa) {
                Mensageiro.removerListenerMensagem(conversa.getId());
            }
        });
    }

    private void exibirDialogNovaConversa(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Novo chat");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Informe um nome para o chat:");
        input = new EditText(getContext());
        builder.setView(input);
        builder.setPositiveButton("Criar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                final Conversa novaConversa = new Conversa(input.getText().toString());
                Mensageiro.criarConversa(novaConversa);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog ad = builder.create();
        ad.show();
    }
}
