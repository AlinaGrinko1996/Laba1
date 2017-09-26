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
import java.sql.Timestamp;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
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
    RadioButton minor;
    RadioButton aver;
    RadioButton maj;
    Button save;
    Button del;

    public Note currentNote;
    public Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        imageView = (ImageView)findViewById(R.id.imageView);
        name = (TextView) findViewById(R.id.name);
        descr = (TextView) findViewById(R.id.decsr);


        minor = (RadioButton)findViewById(R.id.minor_radio);
        minor.setOnClickListener(radioButtonClickListener);

        aver = (RadioButton)findViewById(R.id.average_radio);
        aver.setOnClickListener(radioButtonClickListener);

        maj = (RadioButton)findViewById(R.id.major_radio);
        maj.setOnClickListener(radioButtonClickListener);

        save = (Button)findViewById(R.id.btn_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSave(v);
            }
        });
        del = (Button)findViewById(R.id.btn_del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDel(v);
            }
        });

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null &&  !extras.isEmpty()) {
            Log.d("name----------->",extras.getString("name"));
            currentNote = new Note(extras.getString("name"), extras.getString("descr"), extras.getString("importance"), extras.getString("timestamp"));
            if (extras.containsKey("image")) {
                currentNote.image = intent.getParcelableExtra("image");
                image = intent.getParcelableExtra("image");
            }
        }
        setFieldsNote(currentNote);
        if (currentNote == null ) {
            currentNote = new Note();
        }
        Button pickImage = (Button) findViewById(R.id.btn_pick);
        pickImage.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });
    }

    public void setFieldsNote(Note input) {
        if (input != null ) {
            Log.d("input.name----------->",input.name );
            name.setText(input.name);
            descr.setText(input.description);
            if (currentNote.importance == Importance.CRITICAL) {
                maj.setChecked(true);
            } else  if (currentNote.importance == Importance.AVARAGE) {
                aver.setChecked(true);
            } else {
                minor.setChecked(true);
            }

            if (input.image != null ) {
                imageView.setImageBitmap(image);
            }
        }
    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("------------->","view");
            RadioButton rb = (RadioButton)v;
            Log.d("------------->","radioButton");
            if(currentNote == null) {

            }
            switch (rb.getId()) {
                case R.id.minor_radio: {
                    currentNote.importance = Importance.MINOR;
                    break;
                }
                case R.id.average_radio: {
                    currentNote.importance = Importance.AVARAGE;
                    break;
                }
                case R.id.major_radio: {
                    currentNote.importance = Importance.CRITICAL;
                    break;
                }
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
                        currentNote.image = selectedImage;
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

    protected void onSave(View view) {
        if (currentNote == null) {
            currentNote=new Note(name.getText().toString(), descr.getText().toString(),
                    new Timestamp(System.currentTimeMillis()).toString());
        }
        Log.d("------------->","onSave");
        currentNote.name = name.getText().toString();
        currentNote.description = descr.getText().toString();

        Intent calcActivity = new Intent(getApplicationContext(), Notes.class);
        if (Storage.field != null ) {
            Log.d("Storage.size1()----->", Storage.field.size() + "");
            Storage.field.add(currentNote);
            Log.d("Storage.size2()----->", Storage.field.size() + "");
        }
        startActivity(calcActivity);
    }

    public void onDel(View view) {
        Log.d("------------->","onDel");
        Intent activity = new Intent(getApplicationContext(), Notes.class);
        startActivity(activity);
    }
}

