package ua.nure.hrunko.android.laba1;

/**
 * Created by Den on 05.11.2017.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBHelper extends SQLiteOpenHelper {

    final String LOG_TAG = "myLogs";
    public DBHelper(Context context) {
        // конструктор суперкласса
        super(context, "myDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "--- onCreate database ---");
        // создаем таблицу с полями
        db.execSQL("create table if not exists notes ("
                + "id integer primary key autoincrement, "
                + "name text, "
                + "description text, "
                + "importance text, "
                + "time text, "
                + "imageUri text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}