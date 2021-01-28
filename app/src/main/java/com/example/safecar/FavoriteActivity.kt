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
import kotlinx.android.synthetic.main.fragment_user.*

class FavoriteActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorito)

        setSupportActionBar(findViewById(R.id.toolbar_favorito))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_favorito_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.getItemId()

        if (id == R.id.back_fav) {
            Toast.makeText(this, "Item One Clicked", Toast.LENGTH_LONG).show()
            return true
        }
            if (id == R.id.home_fav) {
           Toast.makeText(this, "Item Two Clicked", android.widget.Toast.LENGTH_LONG).show()
           return true
        }

        return super.onOptionsItemSelected(item)
    }

}