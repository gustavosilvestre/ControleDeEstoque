package br.com.cast.turmaformacao.controledeestoque.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.cast.turmaformacao.controledeestoque.util.ApplicationUtil;

/**
 * Created by Administrador on 25/09/2015.
 */
public final class DataBaseHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "controleestoquedb";
    private final static int DATABASE_VERSION = 1;

    private DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DataBaseHelper getIstance(){
        return new DataBaseHelper(ApplicationUtil.getApplicationContext());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = ProdutoContract.getScriptCreateTable();

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
