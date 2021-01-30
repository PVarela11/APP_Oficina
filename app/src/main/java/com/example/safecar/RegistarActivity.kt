package com.example.safecar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegistarActivity : AppCompatActivity() {

    var firebaseUser = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registar)

        val btn_registo = findViewById<Button>(R.id.btn_registar)

        val txt = findViewById<TextView>(R.id.txt_click)
        txt.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

        btn_registo.setOnClickListener{
            registar()
        }
    }

    private fun registar() {
        val email = findViewById<TextView>(R.id.txt_email).text.toString()
        val passe = findViewById<TextView>(R.id.txt_password).text.toString()

        if (email.isEmpty() || passe.isEmpty()){
            Toast.makeText(this, "Something went wrong, make sure you enter all 3 credentials", Toast.LENGTH_SHORT).show()
        }else{
        println("REGISTARRRRR")
        println(email)
        println(passe)
        val fail = findViewById<TextView>(R.id.tv_error)

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,passe)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    println("User Criado ${it.result?.user?.uid}")
                    //saveUser(it.result?.user?.uid)
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                .addOnFailureListener{
                    fail.text = "Algo correu mal com o registo"
                    Toast.makeText(this, "Algo correu mal com o registo", Toast.LENGTH_SHORT).show()
                }
    }
    }
}