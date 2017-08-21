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

    private SQLiteDatabase mDb;

    private Context mContext;

    public SQLInsert(Context context) {
        this.mContext = context;
        DBCore auxDB = new DBCore(context);

        mDb = auxDB.getWritableDatabase();
    }

    public void insertList() {
        ContentValues values = new ContentValues();
        values.put("produto", "maçã");
        values.put("valor", 12.80);
        values.put("quant", 5);

        mDb.insert("listaValorada", null, values);

        Log.d("LOG", "Sucesso");
    }


}
