package com.example.safecar

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class PreferencesFragment_2 : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}