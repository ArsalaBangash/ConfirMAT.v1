package com.example.arsalabangash.confirmat;

import android.content.Intent;
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
        TextView titleText = (TextView)findViewById(R.id.titleText);
        Typeface robotoFont = Typeface.createFromAsset(getAssets(), "fonts/roboto.ttf");
        titleText.setTypeface(robotoFont);
    }

    public void startGame(View view) {
        Intent speedPracticeIntent = new Intent(StartScreen.this, SpeedPractice.class);
        startActivity(speedPracticeIntent);
    }

    public void startSettings(View view) {
        Intent settingsIntent = new Intent(StartScreen.this, Settings.class);
        startActivity(settingsIntent);
    }
}
