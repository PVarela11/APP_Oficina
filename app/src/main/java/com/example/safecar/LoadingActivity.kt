package com.example.safecar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class LoadingActivity : AppCompatActivity() {

    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val runnable = Runnable {
        if (!isFinishing){
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)
    }

    fun OnPause(){
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}