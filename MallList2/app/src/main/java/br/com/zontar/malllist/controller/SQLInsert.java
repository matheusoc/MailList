package br.com.zontar.malllist.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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


}
