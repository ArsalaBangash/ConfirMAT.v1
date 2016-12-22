package com.example.arsalabangash.confirmat;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
public class EndScreen extends AppCompatActivity {
    Intent playAgainIntent, startIntent, endScreenInit, reportIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_end_screen);
        TextView wellDoneText = (TextView)findViewById(R.id.wellDoneText);
        TextView finalTimeText = (TextView)findViewById(R.id.finalTime);
        Typeface robotoFont = Typeface.createFromAsset(getAssets(), "fonts/roboto.ttf");
        finalTimeText.setTypeface(robotoFont);
        wellDoneText.setTypeface(robotoFont);
        playAgainIntent = new Intent(EndScreen.this, SpeedPractice.class);
        startIntent = new Intent(EndScreen.this, StartScreen.class);
        reportIntent = new Intent(EndScreen.this, Report.class);
        endScreenInit = getIntent();
        String timeTaken = endScreenInit.getStringExtra(Intent.EXTRA_TEXT);
        reportIntent.putExtra(Intent.EXTRA_TEXT, timeTaken);
        finalTimeText.setText("Your Time Taken: " + timeTaken);


    }

    public void playAgain(View view) {
        startActivity(playAgainIntent);
    }

    public void mainMenu(View view) {
        startActivity(startIntent);
    }

    public void startReport(View view) { startActivity(reportIntent);}
}
