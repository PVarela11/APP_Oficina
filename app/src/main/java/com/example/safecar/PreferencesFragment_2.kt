package com.example.safecar

import android.content.Intent
import android.os.Bundle
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth


// declare the GoogleSignInClient
lateinit var mGoogleSignInClient1: GoogleSignInClient
// val auth is initialized by lazy
private val auth by lazy {
    FirebaseAuth.getInstance()
}
private val mGoogleApiClient: GoogleApiClient? = null
class PreferencesFragment_2 : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)


        val myPref = findPreference("logout") as Preference?
        myPref!!.setOnPreferenceClickListener(object : Preference.OnPreferenceClickListener {
            override fun onPreferenceClick(preference: Preference?): Boolean {
                //open browser or intent here
                if (preference.toString() == "Log Out") {
                    signOut()
                }
                return true
            }
        })
    }

    fun signOut() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("704467391456-tfkrpukra6tm28hmujs7jc8ofkehesvc.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient= GoogleSignIn.getClient(requireActivity(), gso)

        mGoogleSignInClient.signOut().addOnCompleteListener {
            activity?.let {
                val intent = Intent(it, LoginActivity::class.java)
                it.startActivity(intent)
            }
        }
    }
}