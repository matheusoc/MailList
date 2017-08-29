package br.com.zontar.malllist.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import br.com.zontar.malllist.db.DBCore;

/**
 * Created by matheusoliveira on 21/08/2017.
 */

public class SQLInsert {

    private static SQLInsert mSqlInsert;

    private SQLiteDatabase mDb;

    public SQLInsert (Context context) {
        DBCore auxDB = new DBCore(context);

        mDb = auxDB.getWritableDatabase();
    }

    public static SQLInsert getInstance (Context context) {
        if (mSqlInsert == null) {
            mSqlInsert = new SQLInsert(context);
        }
        return mSqlInsert;
    }

    public void insertList () {
        ContentValues values = new ContentValues();
        values.put("nameProduct", "Abacaxi");
        values.put("idList_Product", 1);

        mDb.insert("product", null, values);

        Log.d("LOG", "Sucesso");

    }


}
