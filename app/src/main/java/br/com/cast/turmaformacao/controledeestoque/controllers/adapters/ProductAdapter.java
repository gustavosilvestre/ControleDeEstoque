package br.com.cast.turmaformacao.controledeestoque.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.controllers.syncTask.ProductBitmapTask;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProductAdapter extends BaseAdapter {

    private Activity context;
    private List<Product> lista;
    private static final String COLOR_SINCRONIZED = "#4CAF50";
    private static final String COLOR_NOT_SINCRONIZED = "#F44336";

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

        View view;

        if (convertView == null) {
            view = context.getLayoutInflater().inflate(R.layout.list_item_product_card, parent, false);
        } else {
            view = convertView;
        }

        Product produto = getItem(position);

        //TextView id = (TextView) view.findViewById(R.id.list_item_product_id);
        TextView name = (TextView) view.findViewById(R.id.list_item_product_name);
        //TextView description = (TextView) view.findViewById(R.id.list_item_product_description);
        TextView stock = (TextView) view.findViewById(R.id.list_item_product_stock);
        //TextView minStock = (TextView) view.findViewById(R.id.list_item_product_minStock);
        TextView unitPrice = (TextView) view.findViewById(R.id.list_item_product_unitPrice);
        ImageView productImage = (ImageView) view.findViewById(R.id.list_item_product_image);

        //id.setText(produto.getId().toString());
        name.setText(produto.getName());
        //description.setText(produto.getDescription());
        stock.setText(produto.getStock().toString());
        //minStock.setText(produto.getMinStock().toString());
        unitPrice.setText(produto.getUnitPrice().toString());
        new ProductBitmapTask(produto.getImage(), productImage).execute();

        /*
        int hexColor;

        if (produto.isFlag()) {
            hexColor = android.graphics.Color.parseColor(COLOR_SINCRONIZED);
        } else {
            hexColor = android.graphics.Color.parseColor(COLOR_NOT_SINCRONIZED);
        }

        view.setBackgroundColor(hexColor);
        */

        /*
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(produto.getImage(), 0, produto.getImage().length);
            productImage.setImageBitmap(bitmap);
        }catch (Exception e){
            productImage.setImageResource(R.drawable.no_image_found);
        }
        */

        return view;
    }
}
