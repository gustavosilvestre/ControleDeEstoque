package br.com.cast.turmaformacao.controledeestoque.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

/**
 * Created by Administrador on 25/09/2015.
 */
public final class ProdutoRepository {

    private ProdutoRepository() {
        super();
    }

    public static void save(Product product) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = ProdutoContract.getContentValues(product);

        if (product.getId() == null) {

            db.insert(ProdutoContract.TABLE, null, values);

        } else {

            String where = ProdutoContract.ID + " = ? ";
            String[] params = {String.valueOf(product.getId())};

            db.update(ProdutoContract.TABLE, values, where, params);
        }

        dataBaseHelper.close();
        db.close();

    }

    public static List<Product> findAll() {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        Cursor cursor = db.query(ProdutoContract.TABLE, ProdutoContract.COLUNS, null, null, null, null, ProdutoContract.NAME);

        List<Product> lista = ProdutoContract.getProduts(cursor);

        return lista;

    }

    public static void delete(Long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String where = ProdutoContract.ID + " = ?";
        String params[] = {String.valueOf(id)};

        db.delete(ProdutoContract.TABLE, where, params);

        dataBaseHelper.close();
        db.close();

    }

    public static Long getIdByWebId(Long web_id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = ProdutoContract.WEB_ID + " = ? ";
        String[] params = {web_id.toString()};

        Cursor cursor = db.query(ProdutoContract.TABLE, ProdutoContract.COLUNS, where, params, null, null, null);

        Product p = ProdutoContract.getProduct(cursor);

        return p == null ? null : p.getId();
    }

    public static Product getById(Long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        String where = ProdutoContract.ID + " = ? ";
        String[] params = {id.toString()};

        Cursor cursor = db.query(ProdutoContract.TABLE, ProdutoContract.COLUNS, where, params, null, null, null);

        Product product = ProdutoContract.getProduct(cursor);

        return product;


    }
}
