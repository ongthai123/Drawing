package com.example.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        btnUndo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pv.points.size() > 0){
                    pv.temp.add(pv.points.remove(pv.points.size()-1));
                    btnUndo.setText(String.valueOf(pv.temp.size()));
                    btnRedo.setText(String.valueOf(pv.temp.size()));
                    pv.invalidate();
                }
            }
        });

        btnRedo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pv.temp.size() > 0){
                    pv.points.add(pv.temp.remove(pv.temp.size()-1));
                    btnUndo.setText(String.valueOf(pv.temp.size()));
                    btnRedo.setText(String.valueOf(pv.temp.size()));
                    pv.invalidate();
                }
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
