package com.example.safecar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import kotlinx.android.synthetic.main.item_recycler_view.view.*

class ItemActivity : AppCompatActivity() {

    private lateinit var lista: ArrayList<String>
    private lateinit var txt_nome: TextView
    private lateinit var txt_desc: TextView
    private lateinit var txt_local: TextView
    private lateinit var txt_reboque: TextView
    private lateinit var txt_disp: TextView
    private lateinit var img_oficina: ImageView
    private lateinit var estrelas_oficina: RatingBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        setSupportActionBar(findViewById(R.id.toolbar_item))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        lista = intent.getSerializableExtra("Oficina") as ArrayList<String>
        println("--------------------------------------------------------------------------------------------------")
        println(lista)
        println("--------------------------------------------------------------------------------------------------")
        //Indexs(0-id, 1-nome, 2-desc, 3-disponibilidade, 4-estrelas, 5-oficinaIMG, 6-reboque, 7-morada)
        supportActionBar?.title = lista[1]
        txt_nome = findViewById(R.id.oficina_nome)
        txt_nome.text = lista[1]
        txt_desc = findViewById(R.id.oficina_desc)
        txt_desc.text = lista[2]
        txt_disp = findViewById(R.id.oficina_disp)
        txt_disp.text = "Disponibilidade da oficina" + "\n" +lista[3]
        println(lista[3])
        txt_reboque = findViewById(R.id.oficina_reboque)
        txt_reboque.text = "A oficina " + lista[6] + " dispoõe de reboque"
        txt_local = findViewById(R.id.oficina_local)
        txt_local.text = "Localização da oficina:" + "\n" + lista[7]
        img_oficina = findViewById(R.id.oficina_img)
        val packageName = "com.example.safecar"
        //val drawableId: Int = context.getResources().getIdentifier(caminho, "drawable", packageName)
        //val draw = "R.drawable."
        val caminho = lista[5]
        //println(lista[5])
        val imageResource: Int = this.getResources().getIdentifier(
            caminho,
            "drawable",
            packageName
        )
        img_oficina.setImageResource(imageResource)
        val numStar : String = lista[4]
        estrelas_oficina = findViewById(R.id.oficina_estrelas)
        if (numStar == "0"){
            estrelas_oficina.rating = 0.toFloat()
        }
        if (numStar == "0.5"){
            estrelas_oficina.rating = 0.5.toFloat()
        }
        if (numStar == "1"){
            estrelas_oficina.rating = 1.toFloat()
        }
        if (numStar == "1.5"){
            estrelas_oficina.rating = 1.5.toFloat()
        }
        if (numStar == "2"){
            estrelas_oficina.rating = 2.toFloat()
        }
        if (numStar == "2.5"){
            estrelas_oficina.rating = 2.5.toFloat()
        }
        if (numStar == "3"){
            estrelas_oficina.rating = 3.toFloat()
        }
        if (numStar == "3.5"){
            estrelas_oficina.rating = 3.5.toFloat()
        }
        if (numStar == "4"){
            estrelas_oficina.rating = 4.toFloat()
        }
        if (numStar == "4.5"){
            estrelas_oficina.rating = 4.5.toFloat()
        }
        if (numStar == "5"){
            estrelas_oficina.rating = 5.toFloat()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.activity_item_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.directions) {
            Toast.makeText(this, "Item One Clicked", Toast.LENGTH_LONG).show()
            return true
        }
        if (id == R.id.fav) {
            Toast.makeText(this, "Item Two Clicked", Toast.LENGTH_LONG).show()
            addFav(lista[0])
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    private fun addFav(id: String) {
        println(id)
        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val docRef = Firebase.firestore.collection("Users").document(userId)
        docRef.update("idFavoritos", FieldValue.arrayUnion(id))
    }
}