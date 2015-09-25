package br.com.cast.turmaformacao.controledeestoque.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.AccessibleObject;
import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Produto;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProdutoAdapter extends BaseAdapter {

    private Activity context;
    private List<Produto> lista;

    public ProdutoAdapter(Activity context, List<Produto> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Produto getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = context.getLayoutInflater().inflate(R.layout.list_item_produto,parent,false);

        Produto produto = getItem(position);

        TextView id = (TextView) view.findViewById(R.id.activity_produto_list_id);
        TextView nome = (TextView) view.findViewById(R.id.activity_produto_list_nome);
        TextView descricao = (TextView) view.findViewById(R.id.activity_produto_list_descricao);
        TextView quantidade = (TextView) view.findViewById(R.id.activity_produto_list_estoque);
        TextView quantidadeMinima = (TextView) view.findViewById(R.id.activity_produto_list_estoqueMinimo);
        TextView valorUnitario = (TextView) view.findViewById(R.id.activity_produto_list_valorUnitario);

        id.setText(id.getText().toString()+produto.getId().toString());
        nome.setText(nome.getText().toString()+produto.getNome());
        descricao.setText(descricao.getText().toString()+produto.getDescricao());
        quantidade.setText(quantidade.getText().toString()+produto.getQuantidade().toString());
        quantidadeMinima.setText(quantidadeMinima.getText().toString()+produto.getQuantidadeMinima().toString());
        valorUnitario.setText(valorUnitario.getText().toString()+produto.getValorUnitario().toString());

        return view;
    }
}
