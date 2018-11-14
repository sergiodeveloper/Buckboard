package br.unicamp.ft.s187064_m184082.buckboard.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Autenticador;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Compartilhador;
import br.unicamp.ft.s187064_m184082.buckboard.controller.Publicador;
import br.unicamp.ft.s187064_m184082.buckboard.model.Postagem;
import br.unicamp.ft.s187064_m184082.buckboard.view.fragment.PublicacoesFragment;

public class AdapterListaPosts extends BaseAdapter {

    public static interface PostShareListener {
        void compartilhar(Postagem postagem);

        void postar(Postagem postagem);
    }

    private List<Postagem> postagens;
    private Context context;


    private PostShareListener onPostShareListener;

    public AdapterListaPosts(List<Postagem> postagens, Context context) {
        this.postagens = postagens;
        this.context = context;
    }

    @Override
    public int getCount() {
        return postagens.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        if(position == 0) return null;
        return postagens.get(position - 1);
    }

    @Override
    public long getItemId(int position) {
        if (position == 0) return 0;
        return postagens.get(position - 1).getIdLong();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (position == 0) { // Mostrar cabecalho de publicacoes
            View cabecalho = inflater.inflate(R.layout.cabecalho_publicacoes, parent, false);

            View botaoPublicar = cabecalho.findViewById(R.id.btn_publicar);
            EditText entradaPublicacao = cabecalho.findViewById(R.id.entrada_publicacao);

            botaoPublicar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String publicacaoNova = entradaPublicacao.getText().toString().trim();
                    if (publicacaoNova.isEmpty()) {
                        return;
                    }

                    Postagem postagem = new Postagem(publicacaoNova, null, Autenticador.getIdUsuarioLogado(), "normal", null);

                    onPostShareListener.postar(postagem);

                    entradaPublicacao.setText("");
                }
            });

            return cabecalho;
        }

        Postagem postagem = postagens.get(position - 1);

        LinearLayout view;

        if (postagem.getTipo().equals("normal")) {
            view = inflatePostagem(inflater, postagem, parent);
        } else { // tipo: share
            view = (LinearLayout) inflater.inflate(R.layout.template_postagem_compartilhada, parent, false);

            ((TextView) view.findViewById(R.id.nome_publicador)).setText(postagem.getIdUsuario());

            LinearLayout container = view.findViewById(R.id.postArea);

            if(postagem.getIdReferenciada() == null || postagem.getIdReferenciada().isEmpty()) {
                container.removeAllViews();
                TextView textView = new TextView(view.getContext());
                textView.setText("Compartilhamento inválido");
                container.addView(textView);
                return view;
            }

            Compartilhador.obterPostagemCompartilhada(postagem.getIdReferenciada(), new Compartilhador.ListenerObterPostagem() {
                @Override
                public void sucesso(Postagem postagem) {
                    View postagemReferenciada = inflatePostagem(inflater, postagem, view);

                    container.removeAllViews();
                    container.addView(postagemReferenciada);
                }

                @Override
                public void erro() {
                    container.removeAllViews();
                    TextView textView = new TextView(view.getContext());
                    textView.setText("Falha ao encontrar postagem compartilhada");
                    container.addView(textView);
                }
            });


        }

        return view;
    }

    private LinearLayout inflatePostagem(LayoutInflater inflater, Postagem postagem, ViewGroup parent) {
        LinearLayout view = (LinearLayout) inflater.inflate(R.layout.template_postagem, parent, false);

        String nomePublicador = postagem.getIdUsuario();
        String conteudo = postagem.getConteudo();

        ((TextView) view.findViewById(R.id.nome_publicador)).setText(nomePublicador);
        ((TextView) view.findViewById(R.id.conteudo_postagem)).setText(conteudo);
        ((ImageView) view.findViewById(R.id.imagem_post)).setImageResource(R.drawable.cerca);
        ((ImageView) view.findViewById(R.id.imagem_perfil)).setImageResource(R.drawable.googleg_color);

        view.findViewById(R.id.compartilhar1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPostShareListener != null) {
                    onPostShareListener.compartilhar(postagem);
                }
            }
        });

        return view;
    }

    public void setOnPostShareListener(PostShareListener onPostShareListener) {
        this.onPostShareListener = onPostShareListener;
    }
}
