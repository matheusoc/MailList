package br.com.zontar.malllist.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.com.zontar.malllist.db.DBCore;
import br.com.zontar.malllist.model.List;

/**
 * Created by matheusoliveira on 21/08/2017.
 */

public class SQLQuery {

    private static SQLQuery mSqlQuery;

    private String SQL_SELECT_LISTS = "SELECT * FROM list;";
    private String SQL_DELETE_LIST = "DELETE FROM list where idList = ?;";

    private SQLiteDatabase mDb;

    public SQLQuery(Context context) {
        DBCore auxDB = new DBCore(context);

        mDb = auxDB.getWritableDatabase();
    }

    public static SQLQuery getInstance (Context context) {
        if (mSqlQuery == null) {
            mSqlQuery = new SQLQuery(context);
        }
        return mSqlQuery;
    }

    public long insertList (String listName) {
        ContentValues values = new ContentValues();
        values.put("nameList", listName);

        long id = mDb.insert("list", null, values);

        return id;
    }

    public ArrayList<List> getList () {
        Cursor cursor = mDb.rawQuery(SQL_SELECT_LISTS, null);
        ArrayList<List> lists = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                List list = new List();
                list.setIdList(cursor.getInt(0));
                list.setNameList(cursor.getString(1));
                lists.add(list);
            } while (cursor.moveToNext());
        }

        return  lists;
    }

    public void deleteList (List list) {

        String[] idList = {String.valueOf(list.getIdList())};
        mDb.execSQL(SQL_DELETE_LIST, idList);

    }


}
