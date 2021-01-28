package com.example.safecar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LoadingActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 3000 // 1 sec
    val user = FirebaseAuth.getInstance().currentUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)


        Handler().postDelayed({

            //FirebaseAuth.getInstance().signOut()

            // This method will be executed once the timer is over
            // Start your app main activity
            println(user)
            startActivity(Intent(this,LoginActivity::class.java))

            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
//    private val handler = Handler()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.splash_activity)
//    }
//
//    private val runnable = Runnable {
//        if (!isFinishing){
//            startActivity(Intent(applicationContext, LoginActivity::class.java))
//            finish()
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        handler.postDelayed(runnable, 3000)
//    }
//
//    fun OnPause(){
//        super.onPause()
//        handler.removeCallbacks(runnable)
//    }
//}

    // This is the loading time of the splash screen

}