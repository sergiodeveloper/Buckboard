package br.unicamp.ft.s187064_m184082.buckboard.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.unicamp.ft.s187064_m184082.buckboard.R;
import br.unicamp.ft.s187064_m184082.buckboard.model.Postagem;
import br.unicamp.ft.s187064_m184082.buckboard.view.fragment.PublicacoesFragment;

public class AdapterListaPosts extends BaseAdapter {

    public static interface PostShareListener {
        void compartilhar(Postagem postagem);
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
        return postagens.size();
    }

    @Override
    public Object getItem(int position) {
        return postagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return postagens.get(position).getIdLong();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.template_postagem, parent, false);

        Postagem postagem = postagens.get(position);

        ((TextView)view.findViewById(R.id.nome_publicador)).setText(postagem.getIdUsuario());
        ((TextView)view.findViewById(R.id.conteudo_postagem)).setText(postagem.getConteudo());
        ((ImageView)view.findViewById(R.id.imagem_post)).setImageResource(R.drawable.cerca);
        ((ImageView)view.findViewById(R.id.imagem_perfil)).setImageResource(R.drawable.googleg_color);

        view.findViewById(R.id.compartilhar1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPostShareListener != null) {
                    //Postagem postagem = new Postagem("Olha o que eu construí!", "");

                    //onPostShareListener.compartilhar(postagem);
                }
            }
        });

        return view;
    }

    public void setOnPostShareListener(PostShareListener onPostShareListener) {
        this.onPostShareListener = onPostShareListener;
    }
}
