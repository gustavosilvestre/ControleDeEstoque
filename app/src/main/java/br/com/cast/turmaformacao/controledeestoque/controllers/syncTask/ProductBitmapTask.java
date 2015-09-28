package br.com.cast.turmaformacao.controledeestoque.controllers.syncTask;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import br.com.cast.turmaformacao.controledeestoque.util.BitmapHelper;

/**
 * Created by Administrador on 28/09/2015.
 */
public class ProductBitmapTask extends AsyncTask<Void, String, Bitmap> {

    public ImageView imageView;
    public String url;

    public ProductBitmapTask(String url,ImageView imageView) {
        this.imageView = imageView;
        this.url = url;
    }

    @Override
    protected Bitmap doInBackground(Void... strings) {
        Bitmap bitmap = BitmapHelper.getBitmapFromURL(url);
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }
}
