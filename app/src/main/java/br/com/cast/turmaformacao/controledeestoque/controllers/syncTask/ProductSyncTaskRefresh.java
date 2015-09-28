package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.model.http.ProductService;
import br.com.cast.turmaformacao.controledeestoque.model.service.ProductBusinessService;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProductSyncTaskRefresh extends AsyncTask<Void,Void,List<Product>>{

    private ProgressDialog progressDialog;
    private Activity context;
    private TaskSyncInterface activity;

    public ProductSyncTaskRefresh(Activity context, TaskSyncInterface activity) {
        this.context = context;
        this.activity = activity;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage(context.getString(R.string.msg_updating_product_list));
        progressDialog.show();
    }

    @Override
    protected List<Product> doInBackground(Void... params) {
        List<Product> lista = ProductBusinessService.findAll();
        return lista;
    }

    @Override
    protected void onPostExecute(List<Product> produtos) {
        super.onPostExecute(produtos);
        activity.sincronizeList(produtos);
        progressDialog.dismiss();

    }
}
