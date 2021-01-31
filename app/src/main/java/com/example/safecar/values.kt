package com.example.safecar

import android.content.Context
import android.content.SharedPreferences
import android.os.Build.MANUFACTURER
import android.os.Build.MODEL
import androidx.preference.PreferenceManager
import com.google.firebase.auth.FirebaseAuth


fun userID() = FirebaseAuth.getInstance().currentUser?.displayName
//fun deviceName() = "$MODEL-$MANUFACTURER"

object SavedPreference {

    const val EMAIL= "email"
    const val USERNAME="username"

    private  fun getSharedPreference(ctx: Context?): SharedPreferences? {
        return PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    private fun  editor(context: Context, const:String, string: String){
        getSharedPreference(
            context
        )?.edit()?.putString(const,string)?.apply()
    }

    fun getEmail(context: Context)= getSharedPreference(
        context
    )?.getString(EMAIL,"")

    fun setEmail(context: Context, email: String){
        editor(
            context,
            EMAIL,
            email
        )
    }

    fun setUsername(context: Context, username:String){
        editor(
            context,
            USERNAME,
            username
        )
    }

    fun getUsername(context: Context) = getSharedPreference(
        context
    )?.getString(USERNAME,"")

}