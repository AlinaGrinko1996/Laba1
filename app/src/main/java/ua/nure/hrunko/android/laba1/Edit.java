package ua.nure.hrunko.android.laba1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class Edit extends AppCompatActivity {
    private final int SELECT_PHOTO = 1;
    private ImageView imageView;
    private TextView name;
    private TextView descr;


    public static Note currentNote;
    public static List<Note> list;
    public static int index;
    public Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        imageView = (ImageView)findViewById(R.id.imageView);
        setFieldsNote(currentNote);
        Button pickImage = (Button) findViewById(R.id.btn_pick);
        pickImage.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        RadioButton minor = (RadioButton)findViewById(R.id.minor_radio);
        minor.setOnClickListener(radioButtonClickListener);

        RadioButton aver = (RadioButton)findViewById(R.id.average_radio);
        aver.setOnClickListener(radioButtonClickListener);

        RadioButton maj = (RadioButton)findViewById(R.id.major_radio);
        maj.setOnClickListener(radioButtonClickListener);
    }

    public void setFieldsNote(Note input) {
        if (input != null ) {
            name.setText(input.name);
            descr.setText(input.description);
            if (input.image != null ) {
                imageView.setImageBitmap(image);
            }
        }
    }

    public void setNote(Note note, List<Note> notes) {
        currentNote = note;
        notes.remove(note);
        list = notes;
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.minor_radio: currentNote.importance = Importance.MINOR;
                    break;
                case R.id.average_radio: currentNote.importance = Importance.AVARAGE;
                    break;
                case R.id.major_radio: currentNote.importance = Importance.CRITICAL;
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = imageReturnedIntent.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        image = selectedImage;
                        imageView.setImageBitmap(selectedImage);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    protected void onSave(Bundle savedInstanceState) {
        Note note = new Note(name.getText().toString(), descr.getText().toString(), currentNote.importance);
        note.setImage(currentNote.image);

    }

    public void onDel(View view) {
        Intent rgbActivity = new Intent(getApplicationContext(), RGB.class);
        startActivity(rgbActivity);
    }

}

