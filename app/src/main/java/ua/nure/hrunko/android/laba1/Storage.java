package ua.nure.hrunko.android.laba1;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alina on 27.09.2017.
 */

public class Storage {
    public static List<Note> field;
    final static String LOG_TAG = "myLogs";

    public static long maxIdIndex;

    public static List<Note> GetAll(DBHelper dbHelper,  ContentResolver cr) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("notes", null, null, null, null, null, null);
        // ставим позицию курсора на первую строку выборки
        // если в выборке нет строк, вернется false;
        List <Note> notes = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                Note note = new Note( c.getString( c.getColumnIndex("name")),
                        c.getString( c.getColumnIndex("description")),
                        c.getString( c.getColumnIndex("importance")),
                        c.getString( c.getColumnIndex("time")));
                String imageUri = c.getString( c.getColumnIndex("imageUri"));
                Log.e("IMPORTANCE", c.getString( c.getColumnIndex("importance")));
                if (imageUri!=null) {
                    note.imageUri = imageUri;
                    try {
                        final Uri imUri = Uri.parse(imageUri);
                        final InputStream imageStream = cr.openInputStream(imUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        note.image = selectedImage;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (c.getString(0) != null) {
                    Log.d("value--------------" , c.getString(0));
                    note.id = Integer.parseInt(c.getString(0));
                }
                notes.add(note);
            } while (c.moveToNext());
        } else
            Log.d(LOG_TAG, "0 rows");
        c.close();
        field = notes;
        return notes;
    }

    public static void Insert(Note note, DBHelper dbHelper, ContentResolver cr) {
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cv.put("name", note.name);
        cv.put("description", note.description);
        cv.put("importance", note.importance.toString());
        cv.put("time", note.time);
        if (note.imageUri != null) {
            cv.put("imageUri", note.imageUri);
        }
        long rowID = db.insert("notes", null, cv);
        Log.d("inserted -------------", String.valueOf(rowID));
        maxIdIndex = rowID;
    }

    public static long getMaxIndex(DBHelper dbHelper) {
        Log.d("get max index", "");
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("notes",
                new String[] {"MAX(id) AS maximum"},
                null, null, null, null, null);
        Log.d("cursor created", "");
        String max = "0";
        try {
            Log.d("in try", "");
            if (c.moveToFirst()) {
                Log.d("in move to first", "");
                do {
                    Log.d("in do block", "");
                    String maxim = c.getString( c.getColumnIndex("maximum"));
                    max = (maxim==null) ? "0" : maxim;
                    Log.d("max - ", max);
                } while (c.moveToNext());
            } else
                Log.d(LOG_TAG, "0 rows");
        } catch (Exception e) { }
        maxIdIndex =  Long.getLong(max);
        return Long.getLong(max);
    }

    public static void Delete(Note note, DBHelper dbHelper) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("notes", "id = " + note.id, null);
    }

    public void Update (Note note) {

    }

}
