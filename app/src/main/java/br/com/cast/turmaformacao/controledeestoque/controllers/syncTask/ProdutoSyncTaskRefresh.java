package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import android.os.AsyncTask;
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
public abstract class ProdutoSyncTaskRefresh extends AsyncTask<View,Void,List<Produto>>{


    @Override
    protected List<Produto> doInBackground(View... views) {
        List<Produto> lista = ProdutoBusinessService.findAll();
        return null;
    }
}
