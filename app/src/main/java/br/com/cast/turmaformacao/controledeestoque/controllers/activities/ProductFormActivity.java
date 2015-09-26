package br.com.cast.turmaformacao.controledeestoque.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import br.com.cast.turmaformacao.controledeestoque.R;
import br.com.cast.turmaformacao.controledeestoque.controllers.syncTask.ProductSyncTaskSave;
import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;
import br.com.cast.turmaformacao.controledeestoque.util.FormerHelper;


/**
 * Created by Administrador on 25/09/2015.
 */
public class ProductFormActivity extends AppCompatActivity {

    private Product produto;
    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextStock;
    private EditText editTextMinStock;
    private EditText editTextUnitPrice;
    private ImageView imageViewProduct;
    private Button buttonLoadImagem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);
        init();
        bindEditText();
        bindImagemViewProduto();
        bindButtonLoadImagem();
    }

    private void bindButtonLoadImagem() {
        buttonLoadImagem = (Button) findViewById(R.id.activity_produto_form_buttonLoadImage);
        buttonLoadImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(ProductFormActivity.this,"",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void bindImagemViewProduto() {
        imageViewProduct = (ImageView) findViewById(R.id.activity_produto_form_ProductImage);
    }

    private void init() {

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            produto = extras.getParcelable(ProductListActivity.PARAM_PRODUCT);
        }

        produto = produto == null ? new Product() : produto;

    }

    private void bindEditText() {

        editTextName = (EditText) findViewById(R.id.activity_produto_form_name);
        editTextName.setText(produto.getName() == null ? "" : produto.getName());

        editTextDescription = (EditText) findViewById(R.id.activity_produto_form_description);
        editTextDescription.setText(produto.getDescription() == null ? "" : produto.getDescription());

        editTextStock = (EditText) findViewById(R.id.activity_produto_form_stock);
        editTextStock.setText(produto.getStock() == null ? "" : String.valueOf(produto.getStock()));

        editTextMinStock = (EditText) findViewById(R.id.activity_produto_form_minStock);
        editTextMinStock.setText(produto.getMinStock() == null ? "" : String.valueOf(produto.getMinStock()));

        editTextUnitPrice = (EditText) findViewById(R.id.activity_produto_form_unitPrice);
        editTextUnitPrice.setText(produto.getUnitPrice() == null ? "" : String.valueOf(produto.getUnitPrice()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product_form, menu);
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

        String requiredMessage = getString(R.string.msg_requiredField);

        if (!FormerHelper.validateRequired(requiredMessage, editTextName, editTextDescription, editTextStock, editTextUnitPrice, editTextMinStock)) {
            bindProduto();
            new ProductSyncTaskSave(this).execute(produto);
        }
    }

    private void bindProduto() {
        produto.setName(editTextName.getText().toString());
        produto.setDescription(editTextDescription.getText().toString());
        produto.setStock(Integer.parseInt(editTextStock.getText().toString()));
        produto.setMinStock(Integer.parseInt(editTextMinStock.getText().toString()));
        produto.setUnitPrice(Double.parseDouble(editTextUnitPrice.getText().toString()));
    }
}
