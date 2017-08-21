package br.com.zontar.malllist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.zontar.malllist.Constants;

/**
 * Created by matheusoliveira on 21/08/2017.
 */

public class DBCore extends SQLiteOpenHelper {

    private String SQL = "BEGIN TRANSACTION"
                          +  "CREATE TABLE listas ("
                                  +  "`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
                                  +  "`nome`	TEXT NOT NULL,"
                                  +  "`id_idListaValorada`	INTEGER,"
                                  +  "FOREIGN KEY(`id_idListaValorada`) REFERENCES listaValorada(id)"
                                  +  ");"
                                  +  "CREATE TABLE listaValorada ("
                                  +  "`id`	    INTEGER PRIMARY KEY AUTOINCREMENT,"
                                  +  "`produto`	TEXT NOT NULL,"
                                  +  "`valor`	NUMERIC,"
                                  +  "`quant`	INTEGER DEFAULT 1"
                                  +  ");"
                                  + "COMMIT;";

    public DBCore(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
