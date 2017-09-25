package ua.nure.hrunko.android.laba1;

import android.graphics.Bitmap;

import java.sql.Timestamp;

/**
 * Created by Alina on 25.09.2017.
 */

public class Note {
    public String name;
    public String description;
    public Importance importance;
    public Timestamp time;

    Bitmap image;

    public Note(String name, String description, Importance importance) {
        this.name = name;
        this.description = description;
        this.importance = importance;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
