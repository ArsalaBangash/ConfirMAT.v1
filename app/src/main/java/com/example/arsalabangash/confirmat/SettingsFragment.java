package com.example.arsalabangash.confirmat;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Preference Fragment class for the Settings Activity
 */
public class SettingsFragment extends PreferenceFragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
    }
}
