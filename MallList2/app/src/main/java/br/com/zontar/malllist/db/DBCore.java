package br.com.zontar.malllist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.zontar.malllist.Constants;

/**
 * Created by matheusoliveira on 21/08/2017.
 */

public class DBCore extends SQLiteOpenHelper {

    private String TABLE_LIST = "CREATE TABLE list ("
                                + "idList	INTEGER PRIMARY KEY AUTOINCREMENT,"
	                            + "nameList	INTEGER"
                                + ");";

    private String TABLE_PRODUCT = "CREATE TABLE product ("
                                    + "idProduct	INTEGER PRIMARY KEY AUTOINCREMENT,"
	                                + "nameProduct	TEXT NOT NULL,"
                                    + "priceProduct	REAL DEFAULT 0,"
                                    + "idList_Product	INTEGER NOT NULL,"
                                    + "quantProduct	INTEGER DEFAULT 1,"
                                    + "FOREIGN KEY(idList_Product) REFERENCES list(idList)"
                                    + ");";

    public DBCore(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_LIST);
        db.execSQL(TABLE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS list, product" );
        onCreate(db);
    }
}
