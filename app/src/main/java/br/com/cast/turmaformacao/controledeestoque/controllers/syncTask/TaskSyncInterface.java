package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

/**
 * Created by Gustavo on 25/09/2015.
 */
public interface TaskSyncInterface {

    public void sincronizeList(List<Product> produtos);

}
