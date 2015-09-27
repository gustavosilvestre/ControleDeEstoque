package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.model.service.ProductBusinessService;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProductSyncTaskDelete extends AsyncTask<Product,String,Void>{

    private ProgressDialog progressDialog;
    private Activity context;
    private TaskSyncInterface activity;

    public ProductSyncTaskDelete(Activity context, TaskSyncInterface activity) {
        this.context = context;
        this.activity = activity;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage(context.getString(R.string.msg_deleting_product));
        progressDialog.show();

    }

    @Override
    protected Void doInBackground(Product... produtos) {
        ProductBusinessService.delete(produtos[0]);
        publishProgress(context.getString(R.string.msg_updating_product_list));
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        progressDialog.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(Void lista) {
        super.onPostExecute(lista);
        progressDialog.dismiss();
        Toast.makeText(context, R.string.msg_product_delete_successfully,Toast.LENGTH_SHORT).show();
        activity.refreshList();
    }
}
