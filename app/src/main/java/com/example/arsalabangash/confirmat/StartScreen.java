package com.example.arsalabangash.confirmat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_start_screen);
    }

    /**
     * Starts the Math Practice storm
     * @param view The Start Game button
     */
    public void startGame(View view) {
        Intent speedPracticeIntent = new Intent(StartScreen.this, SpeedPractice.class);
        speedPracticeIntent.putExtra(Intent.EXTRA_TEXT, "Initial");
        startActivity(speedPracticeIntent);
    }

    /**
     * Starts the Settings activity
     * @param view The settings button
     */
    public void startSettings(View view) {
        Intent settingsIntent = new Intent(StartScreen.this, Settings.class);
        startActivity(settingsIntent);
    }
}
