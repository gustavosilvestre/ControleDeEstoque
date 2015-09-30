package br.com.cast.turmaformacao.controledeestoque.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;
import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.controllers.adapters.ProductAdapter;
import br.com.cast.turmaformacao.controledeestoque.controllers.adapters.RvProductAdapter;
import br.com.cast.turmaformacao.controledeestoque.controllers.syncTask.ProductSyncTaskDelete;
import br.com.cast.turmaformacao.controledeestoque.controllers.syncTask.ProductSyncWebRefresh;
import br.com.cast.turmaformacao.controledeestoque.controllers.syncTask.TaskSyncInterface;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;


public class ProductListActivity extends AppCompatActivity implements TaskSyncInterface<Product> {

    private RecyclerView recyclerView;
    private ListView listView;
    private Product selectProduct;
    public final static String PARAM_PRODUCT = "SELECT_PRODUCT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        //bindRecyclerView();
        bindListView();
    }

    private void bindListView() {
        listView = (ListView) findViewById(R.id.product_lv);
    }

    private void bindRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ProductListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }

    private void onUpdateList(List<Product> lista) {

        listView.setAdapter(new ProductAdapter(this, lista));
        ProductAdapter adapter = (ProductAdapter) listView.getAdapter();
        adapter.notifyDataSetChanged();


        // recyclerView.setAdapter(new RvProductAdapter(this,lista));
        //recyclerView.getAdapter().notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_product, menu);
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

        Intent gotoProdutoForm = new Intent(ProductListActivity.this, ProductFormActivity.class);
        gotoProdutoForm.putExtra(PARAM_PRODUCT, selectProduct);
        startActivity(gotoProdutoForm);
    }

    private void onMenuContextExcluir() {

        new AlertDialog.Builder(ProductListActivity.this)
                .setTitle(R.string.msg_confirmation)
                .setMessage(R.string.msg_confirmation_delete_product)
                .setPositiveButton(R.string.msg_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new ProductSyncTaskDelete(ProductListActivity.this,ProductListActivity.this).execute(selectProduct);
                    }
                })
                .setNeutralButton(R.string.msg_no, null)
                .show();
    }

    private void onMenuAddClick() {
        Intent gotoToProdutoFormActivity = new Intent(ProductListActivity.this, ProductFormActivity.class);
        startActivity(gotoToProdutoFormActivity);
    }

    @Override
    public void refreshList() {
        new ProductSyncWebRefresh(this,this).execute();
    }

    @Override
    public void sincronizeList(List<Product> produtos) {
        onUpdateList(produtos);
    }


}
