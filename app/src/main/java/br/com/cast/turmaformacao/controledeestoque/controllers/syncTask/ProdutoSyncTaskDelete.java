package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Produto;
import br.com.cast.turmaformacao.controledeestoque.model.service.ProdutoBusinessService;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProdutoSyncTaskDelete extends AsyncTask<Produto,Void,List<Produto>>{

    private ProgressDialog progressDialog;
    private Activity context;
    private TarefaInterface activity;

    public ProdutoSyncTaskDelete(Activity context,TarefaInterface activity) {
        this.context = context;
        this.activity = activity;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Deletando o produto...");
        progressDialog.show();

    }

    @Override
    protected List<Produto> doInBackground(Produto... produtos) {
        ProdutoBusinessService.delete(produtos[0]);
        return ProdutoBusinessService.findAll();
    }

    @Override
    protected void onPostExecute(List<Produto> produtos) {
        super.onPostExecute(produtos);
        progressDialog.dismiss();
        Toast.makeText(context,"Produto deletado com sucesso!",Toast.LENGTH_SHORT).show();
        activity.sincronizeList(produtos);
    }
}
