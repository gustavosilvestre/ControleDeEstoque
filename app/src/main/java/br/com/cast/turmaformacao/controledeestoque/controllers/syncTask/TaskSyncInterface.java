package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import java.util.List;

/**
 * Created by Gustavo on 25/09/2015.
 */
public interface TaskSyncInterface<T> {

    public void refreshList();
    public void sincronizeList(List<T> lista);

}
