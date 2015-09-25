package br.com.cast.turmaformacao.controledeestoque.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.controllers.syncTask.ProdutoSyncTaskSave;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Produto;
import br.com.cast.turmaformacao.controledeestoque.model.service.ProdutoBusinessService;
import br.com.cast.turmaformacao.controledeestoque.util.FormerHelper;


/**
 * Created by Administrador on 25/09/2015.
 */
public class ProdutoFormActivity extends AppCompatActivity {

    private Produto produto;
    private EditText editTextNome;
    private EditText editTextDescricao;
    private EditText editTextQuantidade;
    private EditText editTextQuantidadeMinima;
    private EditText editTextValorUnitario;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_form);
        init();
        bindEditText();

    }

    private void init() {

        Bundle extras = getIntent().getExtras();

        if (extras != null) {

            produto = extras.getParcelable(ProdutoListActivity.PARAM_PRODUTO);
        }

        produto = produto == null ? new Produto() : produto;

    }

    private void bindEditText() {

        editTextNome = (EditText) findViewById(R.id.activity_produto_form_nome);
        editTextNome.setText(produto.getNome() == null ? "" : produto.getNome());

        editTextDescricao = (EditText) findViewById(R.id.activity_produto_form_descricao);
        editTextDescricao.setText(produto.getDescricao() == null ? "" : produto.getDescricao());

        editTextQuantidade = (EditText) findViewById(R.id.activity_produto_form_estoque);
        editTextQuantidade.setText(produto.getQuantidade() == null ? "" : String.valueOf(produto.getQuantidade()));

        editTextQuantidadeMinima = (EditText) findViewById(R.id.activity_produto_form_estoqueMinimo);
        editTextQuantidadeMinima.setText(produto.getQuantidadeMinima() == null ? "" : String.valueOf(produto.getQuantidadeMinima()));

        editTextValorUnitario = (EditText) findViewById(R.id.activity_produto_form_valorUnitario);
        editTextValorUnitario.setText(produto.getValorUnitario() == null ? "" : String.valueOf(produto.getValorUnitario()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_produto_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_produto_form_done:
                onMenuDoneClick();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onMenuDoneClick() {

        String requiredMessage = "Campo Obrigatório";

        if (!FormerHelper.validateRequired(requiredMessage, editTextNome, editTextDescricao, editTextQuantidade, editTextValorUnitario, editTextQuantidadeMinima)) {
            bindProduto();
            ProdutoBusinessService.save(produto);
            finish();
        }
    }

    private void bindProduto() {
        produto.setNome(editTextNome.getText().toString());
        produto.setDescricao(editTextDescricao.getText().toString());
        produto.setQuantidade(Integer.parseInt(editTextQuantidade.getText().toString()));
        produto.setQuantidadeMinima(Integer.parseInt(editTextQuantidadeMinima.getText().toString()));
        produto.setValorUnitario(Double.parseDouble(editTextValorUnitario.getText().toString()));
    }
}
