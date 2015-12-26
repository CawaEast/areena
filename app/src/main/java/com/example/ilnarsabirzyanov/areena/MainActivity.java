package com.example.ilnarsabirzyanov.areena;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("difficulty", difficulty.ordinal());
                startActivity(intent);
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
        easyButton.setBackgroundResource(R.drawable.easy2);
        mediumButton.setBackgroundResource(R.drawable.medium2);
        hardButton.setBackgroundResource(R.drawable.hard2);
        switch (id) {
            case R.id.easyButton:
                difficulty = Difficulty.EASY;
                //easyButton.setColorFilter(Color.argb(255, 0, 0, 0));
                easyButton.setBackgroundResource(R.drawable.easy);
                break;
            case R.id.mediumButton:
                difficulty = Difficulty.MEDIUM;
                //mediumButton.setColorFilter(Color.argb(255, 0, 0, 0));
                mediumButton.setBackgroundResource(R.drawable.medium);
                break;
            case R.id.hardButton:
                difficulty = Difficulty.HARD;
                //hardButton.setColorFilter(Color.argb(255, 0, 0, 0));
                hardButton.setBackgroundResource(R.drawable.hard);
                break;
        }
    }

}
