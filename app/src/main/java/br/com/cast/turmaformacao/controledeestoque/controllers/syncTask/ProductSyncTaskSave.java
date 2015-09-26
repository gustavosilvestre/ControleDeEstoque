package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.cast.turmaformacao.controledeestoque.controllers.activities.ProductListActivity;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.model.service.ProductBusinessService;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProductSyncTaskSave extends AsyncTask<Product, Void, Void> {

    private Activity context;
    private ProgressDialog progressDialog;

    public ProductSyncTaskSave(Activity context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Aguarde...");
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Product... produtos) {
        ProductBusinessService.save(produtos[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void produtos) {
        super.onPostExecute(produtos);
        progressDialog.dismiss();
        Toast.makeText(context, "Product salvo com sucesso!", Toast.LENGTH_SHORT).show();
        Intent gotoList = new Intent(context, ProductListActivity.class);
        context.startActivity(gotoList);
        context.finish();
    }
}
