package br.com.cast.turmaformacao.controledeestoque.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Produto;

/**
 * Created by Administrador on 25/09/2015.
 */
public final class ProdutoRepository {

    private ProdutoRepository() {
        super();
    }

    public static void save(Produto produto) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        ContentValues values = ProdutoContract.getContentValues(produto);

        if (produto.getId() == null) {

            db.insert(ProdutoContract.TABLE, null, values);

        } else {

            String where = ProdutoContract.ID+" = ? ";
            String[] params = {String.valueOf(produto.getId())};

            db.update(ProdutoContract.TABLE,values,where,params);
        }

        dataBaseHelper.close();
        db.close();

    }

    public static List<Produto> findAll(){

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();

        Cursor cursor = db.query(ProdutoContract.TABLE, ProdutoContract.COLUNS, null, null, null, null, ProdutoContract.NOME);

        List<Produto> lista = ProdutoContract.getProdutos(cursor);

        return lista;

    }

    public static void delete(Long id) {

        DataBaseHelper dataBaseHelper = DataBaseHelper.getIstance();
        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
        String where = ProdutoContract.ID + " = ?";
        String params[] = {String.valueOf(id)};

        db.delete(ProdutoContract.TABLE,where,params);

        dataBaseHelper.close();
        db.close();

    }
}
