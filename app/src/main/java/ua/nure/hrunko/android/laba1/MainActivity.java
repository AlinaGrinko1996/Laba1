package ua.nure.hrunko.android.laba1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRGB(View view) {
        Intent rgbActivity = new Intent(getApplicationContext(), RGB.class);
        startActivity(rgbActivity);
    }

    public void onCalculator(View view) {
        Intent calcActivity = new Intent(getApplicationContext(), Calculator.class);
        startActivity(calcActivity);
    }

    public void onNote(View view) {
        Intent calcActivity = new Intent(getApplicationContext(), Notes.class);
        startActivity(calcActivity);
    }
}
