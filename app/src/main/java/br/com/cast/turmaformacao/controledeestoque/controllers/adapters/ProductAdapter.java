package br.com.cast.turmaformacao.controledeestoque.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProductAdapter extends BaseAdapter {

    private Activity context;
    private List<Product> lista;

    public ProductAdapter(Activity context, List<Product> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Product getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = context.getLayoutInflater().inflate(R.layout.list_item_produto,parent,false);

        Product produto = getItem(position);

        TextView id = (TextView) view.findViewById(R.id.activity_produto_list_id);
        TextView name = (TextView) view.findViewById(R.id.activity_produto_list_name);
        TextView description = (TextView) view.findViewById(R.id.activity_produto_list_description);
        TextView stock = (TextView) view.findViewById(R.id.activity_produto_list_stock);
        TextView minStock = (TextView) view.findViewById(R.id.activity_produto_list_minStock);
        TextView unitPrice = (TextView) view.findViewById(R.id.activity_produto_list_unitPrice);

        id.setText(produto.getId().toString());
        name.setText(produto.getName());
        description.setText(produto.getDescription());
        stock.setText(produto.getStock().toString());
        minStock.setText(produto.getMinStock().toString());
        unitPrice.setText(produto.getUnitPrice().toString());

        return view;
    }
}
