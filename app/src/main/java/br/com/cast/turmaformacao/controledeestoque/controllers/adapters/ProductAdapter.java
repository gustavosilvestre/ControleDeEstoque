package br.com.cast.turmaformacao.controledeestoque.controllers.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

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

        View view = context.getLayoutInflater().inflate(R.layout.list_item_card, parent, false);

        Product produto = getItem(position);

        ImageView productImage = (ImageView) view.findViewById(R.id.card_item_product_image);
        TextView productName = (TextView) view.findViewById(R.id.card_item_product_name);
        TextView productUnitaryPrice = (TextView) view.findViewById(R.id.card_item_product_unitPrice);
        TextView productStock = (TextView) view.findViewById(R.id.card_item_product_stock);

        //new ProductBitmapTask(produto.getImage(),productImage).execute();
        productName.setText(produto.getName());
        productStock.setText(produto.getStock().toString());
        productUnitaryPrice.setText(produto.getUnitPrice().toString());

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
