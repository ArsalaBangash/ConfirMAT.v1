package com.example.arsalabangash.confirmat;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class Report extends AppCompatActivity {
    Intent reportInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_report);
        TextView reportTimeTakenText = (TextView)findViewById(R.id.reportTimeTaken);
        Typeface robotoFont = Typeface.createFromAsset(getAssets(), "fonts/roboto.ttf");
        reportTimeTakenText.setTypeface(robotoFont);
        reportInit = getIntent();
        String timeTaken = reportInit.getStringExtra(Intent.EXTRA_TEXT);
        reportTimeTakenText.setText("Your Time Taken: " + timeTaken);
    }
}
