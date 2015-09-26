package br.com.cast.turmaformacao.controledeestoque.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.controllers.adapters.ProdutoAdapter;
import br.com.cast.turmaformacao.controledeestoque.controllers.syncTask.ProdutoSyncTaskDelete;
import br.com.cast.turmaformacao.controledeestoque.controllers.syncTask.ProdutoSyncTaskRefresh;
import br.com.cast.turmaformacao.controledeestoque.controllers.syncTask.TarefaInterface;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Produto;
import br.com.cast.turmaformacao.controledeestoque.model.service.ProdutoBusinessService;


public class ProdutoListActivity extends AppCompatActivity implements TarefaInterface{

    private ListView listViewProduto;
    private Produto selectProduto;
    public final static String PARAM_PRODUTO = "SELECT_PRODUTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_list);
        bindListViewProduto();
    }

    private void bindListViewProduto() {
        listViewProduto = (ListView) findViewById(R.id.activity_produto_list_listView);
        registerForContextMenu(listViewProduto);
        listViewProduto.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                ProdutoAdapter adapter = (ProdutoAdapter) listViewProduto.getAdapter();
                selectProduto = adapter.getItem(position);
                return false;
            }
        });

    }

    private void onUpdateList(List<Produto> lista) {

        listViewProduto.setAdapter(new ProdutoAdapter(ProdutoListActivity.this, lista));
        ProdutoAdapter adapter = (ProdutoAdapter) listViewProduto.getAdapter();
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new ProdutoSyncTaskRefresh(this,this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_produto_list, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_produto, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_produto_list_add) {
            onMenuAddClick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_context_editar:
                onMenuContextEditar();
                break;
            case R.id.menu_context_excluir:
                onMenuContextExcluir();
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void onMenuContextEditar() {

        Intent gotoProdutoForm = new Intent(ProdutoListActivity.this, ProdutoFormActivity.class);
        gotoProdutoForm.putExtra(PARAM_PRODUTO, selectProduto);
        startActivity(gotoProdutoForm);
    }

    private void onMenuContextExcluir() {

        new AlertDialog.Builder(ProdutoListActivity.this)
                .setTitle("Confirmação")
                .setMessage("Deseja realmente excluir esse produto ?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new ProdutoSyncTaskDelete(ProdutoListActivity.this,ProdutoListActivity.this).execute(selectProduto);
                    }
                })
                .setNeutralButton("No", null)
                .show();
    }


    private void onMenuAddClick() {
        Intent gotoToProdutoFormActivity = new Intent(ProdutoListActivity.this, ProdutoFormActivity.class);
        startActivity(gotoToProdutoFormActivity);
    }


    @Override
    public void sincronizeList(List<Produto> produtos) {
        onUpdateList(produtos);
    }
}
