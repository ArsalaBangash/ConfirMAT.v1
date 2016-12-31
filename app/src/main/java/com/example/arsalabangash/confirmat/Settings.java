package com.example.arsalabangash.confirmat;

import android.content.SharedPreferences;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.os.Bundle;
import android.widget.Toast;
import java.util.Collection;
import java.util.Map;


/**
 * The Settings activity allows the user to choose which questions the user wants to attempt
 * during Math Practice
 */
public class Settings extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    PreferenceFragment settingsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settingsFragment = new SettingsFragment();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);

        //Adds the Settings fragment to its requisite container inside the settings layout
        getFragmentManager().beginTransaction()
                .add(R.id.fragmentContainer, settingsFragment)
                .commit();

    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        /*
        A counter for the number of on switches is made, and the switches are iterated through. The
        counter is incremented every time a switch is turned on
         */
        int switchOnCount = 0;
            Map<String, ?> allPreferences = sharedPreferences.getAll();
            Collection boolSet = allPreferences.values();
            for (Object prefObj: boolSet) {
                Boolean prefBool = (Boolean) prefObj;
                if (prefBool) {
                    switchOnCount++;
                }
            }

        //If there are no switches that are switched on, then the last switch turned off is kept on
        if (switchOnCount == 0) {
            Toast.makeText(this, "One Question Type Must be selected", Toast.LENGTH_SHORT).show();
            sharedPreferences.edit().putBoolean(s,!sharedPreferences.getBoolean(s,true)).apply();
            SwitchPreference switchPreference = (SwitchPreference) settingsFragment.findPreference(s);
            switchPreference.setChecked(true);
        }

    }
}
