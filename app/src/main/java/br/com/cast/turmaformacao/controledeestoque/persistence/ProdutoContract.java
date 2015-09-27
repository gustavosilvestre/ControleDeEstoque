package br.com.cast.turmaformacao.controledeestoque.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Product;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProdutoContract {

    public final static String TABLE = "produto";
    public final static String ID = "id";
    public final static String NAME = "name";
    public final static String DESCRIPTION = "description";
    public final static String STOCK = "stock";
    public final static String MIN_STOCK = "minstock";
    public final static String UNIT_PRICE = "unitprice";
    public final static String IMAGE = "image";

    public final static String[] COLUNS = {ID, NAME, DESCRIPTION, STOCK, MIN_STOCK, UNIT_PRICE, IMAGE};

    public static ContentValues getContentValues(Product produto) {

        ContentValues values = new ContentValues();

        values.put(ID, produto.getId());
        values.put(NAME, produto.getName());
        values.put(DESCRIPTION, produto.getDescription());
        values.put(STOCK, produto.getStock());
        values.put(MIN_STOCK, produto.getMinStock());
        values.put(UNIT_PRICE, produto.getUnitPrice());
        values.put(IMAGE,produto.getImage());

        return values;
    }

    public static Product getProduct(Cursor cursor) {

        while (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Product product = new Product();
            product.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            product.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            product.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
            product.setStock(cursor.getInt(cursor.getColumnIndex(STOCK)));
            product.setMinStock(cursor.getInt(cursor.getColumnIndex(MIN_STOCK)));
            product.setUnitPrice(cursor.getDouble(cursor.getColumnIndex(UNIT_PRICE)));
            byte[] blob = cursor.getBlob(cursor.getColumnIndex(IMAGE));
            product.setImage(blob);


            return product;
        }

        return null;
    }

    public static List<Product> getProduts(Cursor cursor) {

        List<Product> lista = new ArrayList<>();

        while (cursor.moveToNext()) {

            lista.add(getProduct(cursor));
        }

        return lista;
    }

    public static String getScriptCreateTable() {
        StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE PRODUTO ");
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL, ");
        create.append(DESCRIPTION + " TEXT NOT NULL, ");
        create.append(STOCK + " INTEGER DEFAULT 0, ");
        create.append(MIN_STOCK + " INTEGER DEFAULT 0, ");
        create.append(UNIT_PRICE + " DOUBLE NOT NULL, ");
        create.append(IMAGE +" BLOB ");
        create.append(" ); ");

        return create.toString();
    }


}
