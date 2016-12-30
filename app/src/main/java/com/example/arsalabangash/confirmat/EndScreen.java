package com.example.arsalabangash.confirmat;

import android.content.Intent;
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
        TextView finalTimeText = (TextView)findViewById(R.id.finalTime);

        //Intents created for each button on the End Screen
        playAgainIntent = new Intent(EndScreen.this, SpeedPractice.class);
        startIntent = new Intent(EndScreen.this, StartScreen.class);
        reportIntent = new Intent(EndScreen.this, Report.class);
        endScreenInit = getIntent();

        //Final time is taken from the current intent's extra and put into the report's intent
        String timeTaken = endScreenInit.getStringExtra(Intent.EXTRA_TEXT);
        reportIntent.putExtra(Intent.EXTRA_TEXT, timeTaken);
        finalTimeText.setText("Your Time Taken: " + timeTaken);


    }

    /**
     * Starts Math practice activity again when the Play Again button is pressed
     * @param view The Play Again button
     */
    public void playAgain(View view) {
        playAgainIntent.putExtra(Intent.EXTRA_TEXT, "Initial");
        startActivity(playAgainIntent);
    }

    /**
     * Goes to Main menu activity when Main Menu button is pressed
     * @param view The Main Menu button
     */
    public void mainMenu(View view) {
        startActivity(startIntent);
    }

    /**
     * Goes to Final Report activity when Final Report button is pressed
     * @param view The Final Report button
     */
    public void startReport(View view) {
        startActivity(reportIntent);
    }
}
