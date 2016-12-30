package com.example.arsalabangash.confirmat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class Settings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        SwitchCompat additionSwitch = (SwitchCompat) findViewById(R.id.additionSwitch);
        additionSwitch.setOnCheckedChangeListener(this);
        SwitchCompat subtractionSwitch = (SwitchCompat) findViewById(R.id.subtractionSwitch);
        subtractionSwitch.setOnCheckedChangeListener(this);
        SwitchCompat multiplySwitch = (SwitchCompat) findViewById(R.id.multiplySwitch);
        multiplySwitch.setOnCheckedChangeListener(this);
        SwitchCompat divideSwitch = (SwitchCompat) findViewById(R.id.divideSwitch);
        divideSwitch.setOnCheckedChangeListener(this);
        SwitchCompat logSwitch = (SwitchCompat) findViewById(R.id.logSwitch);
        logSwitch.setOnCheckedChangeListener(this);
        SwitchCompat expSwitch = (SwitchCompat) findViewById(R.id.expSwitch);
        expSwitch.setOnCheckedChangeListener(this);
        SwitchCompat rootSwitch = (SwitchCompat) findViewById(R.id.rootSwitch);
        rootSwitch.setOnCheckedChangeListener(this);
        SwitchCompat modSwitch = (SwitchCompat) findViewById(R.id.modSwitch);
        modSwitch.setOnCheckedChangeListener(this);
        SwitchCompat decimalAddSwitch = (SwitchCompat) findViewById(R.id.decimalAddSwitch);
        decimalAddSwitch.setOnCheckedChangeListener(this);
        SwitchCompat decimalSubSwitch = (SwitchCompat) findViewById(R.id.decimalSubSwitch);
        decimalSubSwitch.setOnCheckedChangeListener(this);



    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        SharedPreferences boltsPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor boltsEditor = boltsPreferences.edit();
        switch (compoundButton.getId()) {
            case R.id.additionSwitch:
                boltsEditor.putBoolean("Addition", b);
                break;
            case R.id.subtractionSwitch:
                boltsEditor.putBoolean("Subtraction",b);
                break;
            case R.id.multiplySwitch:
                boltsEditor.putBoolean("Multiplication",b);
                break;
            case R.id.divideSwitch:
                boltsEditor.putBoolean("Division",b);
                break;
            case R.id.logSwitch:
                boltsEditor.putBoolean("Log",b);
                break;
            case R.id.expSwitch:
                boltsEditor.putBoolean("Exponents",b);
                break;
            case R.id.rootSwitch:
                boltsEditor.putBoolean("Root",b);
                break;
            case R.id.modSwitch:
                boltsEditor.putBoolean("Modulus",b);
                break;
            case R.id.decimalAddSwitch:
                boltsEditor.putBoolean("DecimalAddition",b);
                break;
            case R.id.decimalSubSwitch:
                boltsEditor.putBoolean("DecimalSubtraction",b);
                break;
        }

        boltsEditor.commit();
    }
}
