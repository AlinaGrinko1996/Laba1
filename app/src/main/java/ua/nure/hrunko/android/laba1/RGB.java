package ua.nure.hrunko.android.laba1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.SeekBar;

public class RGB extends AppCompatActivity {
    SurfaceHolder holder;
    Canvas canvas;
    private SeekBar mRedSeekBar, mGreenSeekBar, mBlueSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rgb);
        Log.d("------------->","after start activity");
        SurfaceView surface = (SurfaceView) findViewById(R.id.surface1);

        mRedSeekBar = (SeekBar) findViewById(R.id.redBar);
        mGreenSeekBar = (SeekBar) findViewById(R.id.greenBar);
        mBlueSeekBar = (SeekBar) findViewById(R.id.blueBar);

        mRedSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        mGreenSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        mBlueSeekBar.setOnSeekBarChangeListener(seekBarChangeListener);

        mBlueSeekBar.setProgress(0);
        mRedSeekBar.setProgress(0);
        mGreenSeekBar.setProgress(0);

        holder = surface.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                // Do some drawing when surface is ready
                canvas = holder.lockCanvas();
                canvas.drawColor(Color.rgb(0,0,0));
                holder.unlockCanvasAndPost(canvas);
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {}

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}
        });
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            updateBackground();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };

    private void updateBackground() {
        canvas = holder.lockCanvas();
        int redValue, greenValue, blueValue;
        redValue = mRedSeekBar.getProgress();
        greenValue = mGreenSeekBar.getProgress();
        blueValue = mBlueSeekBar.getProgress();
        canvas.drawColor(Color.rgb(redValue, greenValue, blueValue));
        holder.unlockCanvasAndPost(canvas);
    }
}
