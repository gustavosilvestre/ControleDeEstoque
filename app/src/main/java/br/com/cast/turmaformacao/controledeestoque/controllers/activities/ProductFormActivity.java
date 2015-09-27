package br.com.cast.turmaformacao.controledeestoque.controllers.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
    private Button buttonTakePicture;
    private static final int IMAGE_GALLERY = 10;
    private static final int IMAGE_CAMERA = 20;
    private Bitmap selectImage;
    private byte[] selectImageArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);
        init();
        bindEditText();
        bindImagemViewProduto();
        bindButtonLoadImagem();
        bindTakePicture();
    }

    private void bindTakePicture() {
        buttonTakePicture = (Button) findViewById(R.id.activity_product_form_buttonTakePicture);
        buttonTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(gotoCamera, IMAGE_CAMERA);
            }
        });
    }

    private void bindButtonLoadImagem() {
        buttonLoadImagem = (Button) findViewById(R.id.activity_product_form_buttonLoadImage);
        buttonLoadImagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentPictureGallery = new Intent(Intent.ACTION_PICK);
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath();
                Uri picturesDirectory = Uri.parse(path);
                intentPictureGallery.setDataAndType(picturesDirectory, "image/*");
                startActivityForResult(intentPictureGallery, IMAGE_GALLERY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case IMAGE_GALLERY:
                    Uri photoLocation = data.getData();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(photoLocation);
                        selectImage = BitmapFactory.decodeStream(inputStream);
                        imageViewProduct.setImageBitmap(selectImage);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        selectImage.compress(Bitmap.CompressFormat.JPEG,70,stream);
                        selectImageArray = stream.toByteArray();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                    } catch (SecurityException e) {
                        Toast.makeText(this, "Usuario nao tem permissao para acessar essa funcao", Toast.LENGTH_LONG).show();
                    }
                    break;
                case IMAGE_CAMERA :
                    Bundle extras = data.getExtras();

                    if (extras != null){
                        Bitmap img = (Bitmap) extras.get("data");
                        imageViewProduct.setImageBitmap(img);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        img.compress(Bitmap.CompressFormat.JPEG, 70, stream);
                        selectImageArray = stream.toByteArray();
                    }

                    break;

            }

        }

    }

    private void bindImagemViewProduto() {
        imageViewProduct = (ImageView) findViewById(R.id.activity_product_form_productImage);

        if(produto.getImage() == null)
            return;

        imageViewProduct.setImageBitmap(BitmapFactory.decodeByteArray(produto.getImage(),0,produto.getImage().length));

    }

    private void init() {

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            produto = extras.getParcelable(ProductListActivity.PARAM_PRODUCT);
        }

        produto = produto == null ? new Product() : produto;

    }

    private void bindEditText() {

        editTextName = (EditText) findViewById(R.id.activity_product_form_name);
        editTextName.setText(produto.getName() == null ? "" : produto.getName());

        editTextDescription = (EditText) findViewById(R.id.activity_product_form_description);
        editTextDescription.setText(produto.getDescription() == null ? "" : produto.getDescription());

        editTextStock = (EditText) findViewById(R.id.activity_product_form_stock);
        editTextStock.setText(produto.getStock() == null ? "" : String.valueOf(produto.getStock()));

        editTextMinStock = (EditText) findViewById(R.id.activity_product_form_minStock);
        editTextMinStock.setText(produto.getMinStock() == null ? "" : String.valueOf(produto.getMinStock()));

        editTextUnitPrice = (EditText) findViewById(R.id.activity_product_form_unitPrice);
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
        produto.setImage(selectImageArray);
    }
}
