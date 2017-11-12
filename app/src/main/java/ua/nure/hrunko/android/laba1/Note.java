package ua.nure.hrunko.android.laba1;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Alina on 25.09.2017.
 */

public class Note{
    public int id;
    public String name;
    public String description;
    public Importance importance;
    public String time;
    public String imageUri;

    Bitmap image;

    public Note(String name, String description, String importance) {
        if (importance == "MINOR") {
            this.importance = Importance.MINOR;
        } else if (importance == "AVARAGE") {
            this.importance = Importance.AVARAGE;
        } else {
            this.importance = Importance.CRITICAL;
        }
        this.name = name;
        this.description = description;
        this.time = new Timestamp(System.currentTimeMillis()).toString();
    }

    public Note(String name, String description, String importance, String timestamp) {
        if (importance == "MINOR") {
            this.importance = Importance.MINOR;
        } else if (importance == "AVARAGE") {
            this.importance = Importance.AVARAGE;
        } else {
            this.importance = Importance.CRITICAL;
        }
        this.name = name;
        this.description = description;
        this.time = timestamp;
    }

    public Note() {}

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
