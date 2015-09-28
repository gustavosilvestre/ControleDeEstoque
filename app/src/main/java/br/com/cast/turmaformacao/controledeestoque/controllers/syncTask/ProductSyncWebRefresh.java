package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.model.service.ProductBusinessService;

/**
 * Created by Administrador on 28/09/2015.
 */
public class ProductSyncWebRefresh extends AsyncTask<Void,Void,List<Product>>{

    private Context context;
    private ProgressDialog progressDialog;
    private TaskSyncInterface activity;

    public ProductSyncWebRefresh(Context context,TaskSyncInterface activity){
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Aguarde...");
        progressDialog.show();
    }

    @Override
    protected List<Product> doInBackground(Void... voids) {
        ProductBusinessService.sincronized();
        return ProductBusinessService.findAll();
    }

    @Override
    protected void onPostExecute(List<Product> lista) {
        super.onPostExecute(lista);
        progressDialog.dismiss();
        activity.sincronizeList(lista);
    }
}
