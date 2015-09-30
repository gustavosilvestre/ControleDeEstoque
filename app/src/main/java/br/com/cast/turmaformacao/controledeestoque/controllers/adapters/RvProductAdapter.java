package br.com.cast.turmaformacao.controledeestoque.controllers.adapters;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.controllers.syncTask.ProductBitmapTask;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.util.BitmapHelper;

public class RvProductAdapter extends RecyclerView.Adapter<RvProductAdapter.ProductViewHolder> {

    private List<Product> productList;
    private LayoutInflater layoutInflater;

    public RvProductAdapter(Context context, List<Product> productList){
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = layoutInflater.inflate(R.layout.list_item_card, viewGroup, false);
        ProductViewHolder pvh = new ProductViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i) {
        Product product = productList.get(i);
        new ProductBitmapTask(product.getImage(),productViewHolder.productImage).execute();
        productViewHolder.productName.setText(product.getName());
        productViewHolder.productUnitPrice.setText(product.getUnitPrice().toString());
        productViewHolder.productStock.setText(product.getStock().toString());
    }


    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        ImageView productImage;
        TextView productName;
        TextView productUnitPrice;
        TextView productStock;

        public ProductViewHolder(View itemView) {
            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cardViewProduct);
            productName = (TextView) itemView.findViewById(R.id.card_item_product_name);
            productUnitPrice = (TextView) itemView.findViewById(R.id.card_item_product_unitPrice);
            productStock = (TextView) itemView.findViewById(R.id.card_item_product_stock);
            productImage = (ImageView) itemView.findViewById(R.id.card_item_product_image);

        }
    }



}
