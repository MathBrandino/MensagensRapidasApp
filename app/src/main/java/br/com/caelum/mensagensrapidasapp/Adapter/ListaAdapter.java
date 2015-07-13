package br.com.caelum.mensagensrapidasapp.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.mensagensrapidasapp.Modelo.Mensagem;
import br.com.caelum.mensagensrapidasapp.R;

/**
 * Created by matheus on 13/07/15.
 */
public class ListaAdapter extends BaseAdapter {

    private List<Mensagem> mensagens;
    private Activity activity;

    public ListaAdapter(List<Mensagem> mensagens , Activity activity) {
        this.mensagens = mensagens;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mensagens.size();
    }

    @Override
    public Object getItem(int position) {
        return mensagens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mensagens.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(activity, R.layout.mensagens_itens, null);

        Mensagem mensagem = (Mensagem) getItem(position);


        TextView nome = (TextView) view.findViewById(R.id.nome_card_view);
        TextView corpo = (TextView) view.findViewById(R.id.corpo_card_view);

        nome.setText(mensagem.getNome());
        corpo.setText(mensagem.getCorpo());


        return view;
    }
}
