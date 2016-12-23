package com.example.arsalabangash.confirmat;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        TextView settingsText = (TextView)findViewById(R.id.settingsText);
        Typeface robotoFont = Typeface.createFromAsset(getAssets(), "fonts/roboto.ttf");
        settingsText.setTypeface(robotoFont);
    }
}
