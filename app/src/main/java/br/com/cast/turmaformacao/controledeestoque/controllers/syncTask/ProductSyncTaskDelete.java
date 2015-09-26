package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.model.service.ProductBusinessService;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProductSyncTaskDelete extends AsyncTask<Product,String,List<Product>>{

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
    protected List<Product> doInBackground(Product... produtos) {
        ProductBusinessService.delete(produtos[0]);
        publishProgress(context.getString(R.string.msg_updating_product_list));
        List<Product> lista = ProductBusinessService.findAll();
        return lista;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        progressDialog.setMessage(values[0]);
    }

    @Override
    protected void onPostExecute(List<Product> lista) {
        super.onPostExecute(lista);
        progressDialog.dismiss();
        Toast.makeText(context,"Product deletado com sucesso!",Toast.LENGTH_SHORT).show();
        activity.sincronizeList(lista);
    }
}
