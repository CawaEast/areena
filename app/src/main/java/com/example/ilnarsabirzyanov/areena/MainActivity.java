package com.example.ilnarsabirzyanov.areena;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ImageButton playButton, easyButton, mediumButton, hardButton, soundButton;
    enum Difficulty {
        EASY, MEDIUM, HARD
    }
    Difficulty difficulty = Difficulty.EASY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = (ImageButton)findViewById(R.id.playButton);
        easyButton = (ImageButton)findViewById(R.id.easyButton);
        mediumButton = (ImageButton)findViewById(R.id.mediumButton);
        hardButton = (ImageButton)findViewById(R.id.hardButton);
        soundButton = (ImageButton)findViewById(R.id.soundButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: start game
            }
        });

        easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDifficulty(v.getId());
            }
        });
        mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDifficulty(v.getId());
            }
        });
        hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeDifficulty(v.getId());
            }
        });
    }

    protected void changeDifficulty(int id) {
        //easyButton.setColorFilter(Color.argb(255, 255, 255, 255));
        //mediumButton.setColorFilter(Color.argb(255, 255, 255, 255));
        //hardButton.setColorFilter(Color.argb(255, 255, 255, 255));
        easyButton.setImageResource(R.drawable.ic_lock_24dp);
        mediumButton.setImageResource(R.drawable.ic_lock_24dp);
        hardButton.setImageResource(R.drawable.ic_lock_24dp);
        switch (id) {
            case R.id.easyButton:
                difficulty = Difficulty.EASY;
                //easyButton.setColorFilter(Color.argb(255, 0, 0, 0));
                easyButton.setImageResource(R.drawable.ic_lock_open_24dp);
                break;
            case R.id.mediumButton:
                difficulty = Difficulty.MEDIUM;
                //mediumButton.setColorFilter(Color.argb(255, 0, 0, 0));
                mediumButton.setImageResource(R.drawable.ic_lock_open_24dp);
                break;
            case R.id.hardButton:
                difficulty = Difficulty.HARD;
                //hardButton.setColorFilter(Color.argb(255, 0, 0, 0));
                hardButton.setImageResource(R.drawable.ic_lock_open_24dp);
                break;
        }
    }

}
