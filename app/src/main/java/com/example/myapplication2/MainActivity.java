package com.example.myapplication2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PaintView pv = findViewById(R.id.view);

        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(50);
        final Button btnRedo = findViewById(R.id.btnRedo);
        final Button btnUndo = findViewById(R.id.btnUndo);
        final Button btnClear = findViewById(R.id.btnClear);

        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pv.Undo();
//                Toast.makeText(MainActivity.this,
//                        "point1: "+ pv.point1.size() + " point2: " + pv.point2.size(), Toast.LENGTH_SHORT).show();
            }
        });

        btnRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pv.Redo();
//                Toast.makeText(MainActivity.this,
//                        "point1: " + pv.point1.size() + " point2: " + pv.point2.size(), Toast.LENGTH_SHORT).show();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pv.point1.clear();
                pv.point2.clear();
                pv.p1.clear();
                pv.p2.clear();
                pv.undo1 = 0;
                pv.undo2 = 0;
                pv.invalidate();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pv.shapeSize = i + 10;
                pv.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
