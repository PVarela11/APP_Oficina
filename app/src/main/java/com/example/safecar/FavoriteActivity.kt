package com.example.safecar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safecar.model.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class FavoriteActivity : AppCompatActivity() {

    lateinit var favFragment: FavoriteFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorito)

        setSupportActionBar(findViewById(R.id.toolbar_favorito))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favFragment = FavoriteFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_fav, favFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()

        setSupportActionBar(findViewById(R.id.toolbar_favorito))
        //val recyclerAdapter: OficinasFavAdapter = OficinasFavAdapter()
        //rv_favs.adapter = OficinasFavAdapter(this)

    }

}