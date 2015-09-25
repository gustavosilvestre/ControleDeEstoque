package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Produto;
import br.com.cast.turmaformacao.controledeestoque.model.service.ProdutoBusinessService;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProdutoSyncTaskDelete extends AsyncTask<Produto,Void,Void>{

    private ProgressDialog progressDialog;
    private Activity context;

    public ProdutoSyncTaskDelete(Activity context) {
        this.context = context;
        progressDialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setMessage("Atualizando Lista...");
        progressDialog.show();

    }


    @Override
    protected Void doInBackground(Produto... produtos) {
        ProdutoBusinessService.delete(produtos[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.dismiss();
        Toast.makeText(context,"Produto deletado com sucesso!",Toast.LENGTH_SHORT).show();
    }
}
