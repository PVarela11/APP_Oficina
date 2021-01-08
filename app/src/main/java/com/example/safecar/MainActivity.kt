package com.example.safecar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    lateinit var gpsFragment: GPSFragment
    lateinit var userFragment: UserFragment



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bot_nav)
        //setSupportActionBar(findViewById(R.id.toolbar))
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //if (savedInstanceState != null) {
            homeFragment = HomeFragment()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()
        //}

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.home -> {
                homeFragment = HomeFragment()
                        supportFragmentManager
                        . beginTransaction ()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }

                R.id.gps -> {

                    gpsFragment = GPSFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, gpsFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }

                R.id.user -> {

                    userFragment = UserFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.frame_layout, userFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }

                R.id.settings -> {
                    goToSettingsActivity()
                    //settingsFragment = SettingsFragment()
                    //supportFragmentManager
                      //      .beginTransaction()
                       //     .replace(R.id.frame_layout, settingsFragment)
                        //    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                         //   .commit()

                }
            }

            true
        }
    }

    private fun goToSettingsActivity(){
        val intent = Intent(this, PreferencesActivity::class.java)
        startActivity(intent)
    }
}