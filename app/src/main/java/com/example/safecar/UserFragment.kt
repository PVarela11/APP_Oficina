package com.example.safecar

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment


class UserFragment : Fragment() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        findViewById<Button>(R.id.btncarro).setOnClickListener(){
            val intent = Intent(this, CarFragment::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnfav).setOnClickListener(){
            val intent = Intent(this, FavoritoFragment::class.java)
            startActivity(intent)
        }


    }