package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.controllers.adapters.ProdutoAdapter;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Produto;
import br.com.cast.turmaformacao.controledeestoque.model.service.ProdutoBusinessService;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProdutoSyncTaskRefresh extends AsyncTask<Void,Void,List<Produto>>{

    private ProgressDialog progressDialog;
    private Activity context;
    private TarefaInterface activity;

    public ProdutoSyncTaskRefresh(Activity context, TarefaInterface activity) {
        this.context = context;
        this.activity = activity;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Atualizando Lista de Produtos");
        progressDialog.show();
    }

    @Override
    protected List<Produto> doInBackground(Void... params) {
        List<Produto> lista = ProdutoBusinessService.findAll();
        return lista;
    }

    @Override
    protected void onPostExecute(List<Produto> produtos) {
        super.onPostExecute(produtos);
        activity.sincronizeList(produtos);
        progressDialog.dismiss();

    }
}
