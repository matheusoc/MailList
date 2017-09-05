package br.com.zontar.malllist.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import br.com.zontar.malllist.db.DBCore;
import br.com.zontar.malllist.model.List;
import br.com.zontar.malllist.model.Product;

/**
 * Created by matheusoliveira on 21/08/2017.
 */

public class SQLQuery {

    private static SQLQuery mSqlQuery;

    private String SQL_SELECT_LISTS = "SELECT * FROM list;";
    private String SQL_DELETE_LIST = "DELETE FROM list where idList = ?;";
    private String SQL_UPDATE_LIST = "UPDATE list SET nameList = ? WHERE idList = ?;";
    private String SQL_DELETE_LIST_SUBSEQUENT = "DELETE FROM product where idList_Product = ?;";

    private String SQL_SELECT_PRODUCTS_LIST = "SELECT * FROM product WHERE idList_Product = ?;";
    private String SQL_DELETE_PRODUCT = "DELETE FROM product where idProduct = ?;";
    private String SQL_UPDATE_PRODUCT = "UPDATE product SET nameProduct = ?, priceProduct = ?, " +
            "quantProduct = ? WHERE idProduct = ?;";


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
        mDb.execSQL(SQL_DELETE_LIST_SUBSEQUENT, idList);

    }

    public void updateList (List list, String newName) {

        String[] values = {newName, String.valueOf(list.getIdList())};
        mDb.execSQL(SQL_UPDATE_LIST, values);

    }

    public long addItemToList (int idList, String itemName, String qnt, String price) {

        ContentValues values = new ContentValues();
        values.put("idList_Product", idList);
        values.put("nameProduct", itemName);
        values.put("quantProduct", qnt);
        values.put("priceProduct", price);

        long id = mDb.insert("product", null, values);

        return id;

    }

    public ArrayList<Product> getProducts (int idList) {
        String[] args = {String.valueOf(idList)};
        Cursor cursor = mDb.rawQuery(SQL_SELECT_PRODUCTS_LIST, args);

        ArrayList<Product> products = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Product product = new Product();
                product.setIdProduct(cursor.getInt(0));
                product.setProductName(cursor.getString(1));
                product.setProductQnt(cursor.getInt(4));
                product.setProductPrice(cursor.getFloat(2));
                products.add(product);
            } while (cursor.moveToNext());
        }
        return products;
    }

    public void deleteProduct (int id) {

        String[] idList = {String.valueOf(id)};
        mDb.execSQL(SQL_DELETE_PRODUCT, idList);

    }

    public void updateProduct (int id, String name, int qnt, float value) {

        String[] values = {name, String.valueOf(qnt), String.valueOf(value), String.valueOf(id)};

        mDb.execSQL(SQL_UPDATE_PRODUCT, values);

    }


}
