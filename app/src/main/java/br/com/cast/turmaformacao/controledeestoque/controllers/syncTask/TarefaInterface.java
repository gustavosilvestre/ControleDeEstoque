package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Produto;

/**
 * Created by Gustavo on 25/09/2015.
 */
public interface TarefaInterface {

    public void sincronizeList(List<Produto> produtos);

}
