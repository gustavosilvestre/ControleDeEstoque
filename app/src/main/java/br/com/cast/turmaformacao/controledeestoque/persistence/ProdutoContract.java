package br.com.cast.turmaformacao.controledeestoque.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.ColorFilter;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.controledeestoque.model.entities.Produto;

/**
 * Created by Administrador on 25/09/2015.
 */
public class ProdutoContract {

    public final static String TABLE = "produto";
    public final static String ID = "id";
    public final static String NOME = "nome";
    public final static String DESCRICAO = "descricao";
    public final static String QUANTIDADE = "quantidade";
    public final static String QUANTIDADE_MINIMA = "quantidademinima";
    public final static String VALOR_UNITARIO = "valorunitario";
    public final static String FOTO = "foto";

    public final static String[] COLUNS = {ID, NOME, DESCRICAO, QUANTIDADE, QUANTIDADE_MINIMA, VALOR_UNITARIO,FOTO};

    public static ContentValues getContentValues(Produto produto) {

        ContentValues values = new ContentValues();

        values.put(ID, produto.getId());
        values.put(NOME, produto.getNome());
        values.put(DESCRICAO, produto.getDescricao());
        values.put(QUANTIDADE, produto.getQuantidade());
        values.put(QUANTIDADE_MINIMA, produto.getQuantidadeMinima());
        values.put(VALOR_UNITARIO, produto.getValorUnitario());
        values.put(FOTO,produto.getFoto());

        return values;
    }

    public static Produto getProduto(Cursor cursor) {

        while (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            Produto produto = new Produto();
            produto.setId(cursor.getLong(cursor.getColumnIndex(ID)));
            produto.setNome(cursor.getString(cursor.getColumnIndex(NOME)));
            produto.setDescricao(cursor.getString(cursor.getColumnIndex(DESCRICAO)));
            produto.setQuantidade(cursor.getInt(cursor.getColumnIndex(QUANTIDADE)));
            produto.setQuantidadeMinima(cursor.getInt(cursor.getColumnIndex(QUANTIDADE_MINIMA)));
            produto.setValorUnitario(cursor.getDouble(cursor.getColumnIndex(VALOR_UNITARIO)));
            produto.setFoto(cursor.getString(cursor.getColumnIndex(FOTO)));
            return produto;
        }

        return null;
    }

    public static List<Produto> getProdutos(Cursor cursor) {

        List<Produto> lista = new ArrayList<>();

        while (cursor.moveToNext()) {

            lista.add(getProduto(cursor));
        }

        return lista;
    }

    public static String getScriptCreateTable() {
        StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE PRODUTO ");
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NOME + " TEXT NOT NULL, ");
        create.append(DESCRICAO + " TEXT NOT NULL, ");
        create.append(QUANTIDADE + " INTEGER DEFAULT 0, ");
        create.append(QUANTIDADE_MINIMA + " INTEGER DEFAULT 0, ");
        create.append(VALOR_UNITARIO + " DOUBLE NOT NULL, ");
        create.append(FOTO+" TEXT ");
        create.append(" ); ");

        return create.toString();
    }


}
