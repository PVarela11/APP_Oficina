package com.example.safecar

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.safecar.databinding.FragmentUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.android.synthetic.main.fragment_user.view.*
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.properties.Delegates


class UserFragment : Fragment() {

    var firebaseUser = FirebaseAuth.getInstance().currentUser
    private lateinit var view: FragmentUserBinding
    private var data by Delegates.notNull<Long>()
    private lateinit var nome: String
    private val GALLERY_REQUEST_CODE = 438
    val caminho = FirebaseAuth.getInstance().currentUser?.uid.toString() + ".jpg"
    var imageref = Firebase.storage.reference.child("userIMG/$caminho")
    val userDoc = Firebase.firestore.collection("Users").document(FirebaseAuth.getInstance().currentUser?.uid.toString())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        view = FragmentUserBinding.inflate(LayoutInflater.from(context))


        return view.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        data()

        view.imageButton.setOnClickListener(){
            selectImageFromGallery()
        }

        imageref.downloadUrl.addOnSuccessListener {Uri->

            val imageURL = Uri.toString()
            var imagetest = view.imageButton


            Glide.with(this)
                .load(imageURL)
                .into(imagetest)

        }

        toolbar_user.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.settings_user -> {
                    activity?.let {
                        val intent = Intent(it, PreferencesActivity::class.java)
                        it.startActivity(intent)
                    }
                    true
                }
                R.id.favorito -> {
                    activity?.let {
                        val intent = Intent(it, FavoriteActivity::class.java)
                        it.startActivity(intent)
                    }
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun data() {
        userDoc.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    nome = document["nome"] as String
                    data = document["criado"] as Long
                    println(nome)
                    dataText(nome,data)

                } else {
                    Toast.makeText(context, "Usuário não existe", Toast.LENGTH_LONG).show()
                }
            }
            .addOnFailureListener{ exception ->
                Toast.makeText(context, "Ocorreu um erro na ligação com a base de dados", Toast.LENGTH_LONG).show()
            }
    }




    @RequiresApi(Build.VERSION_CODES.O)
    private fun dataText(username: String, data:Long) {
        println(data)
        val date = Date(data * 1000)
        val format = SimpleDateFormat("yyyy/MM/dd HH:mm")


        view.textusername.text = username
        val string = "Membro desde: ${format.format(date)}"
        view.txtTimestamp.text = string
    }

    private fun selectImageFromGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(
            Intent.createChooser(
                intent,
                "Please select..."
            ),
            GALLERY_REQUEST_CODE
        )
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {

        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )


        if (requestCode == GALLERY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK
            && data != null
            && data.data != null
        ) {

            // Get the Uri of data
            val file_uri = data.data
            Toast.makeText(context, "Uploading image", Toast.LENGTH_LONG ).show()
            uploadImageToFirebase(file_uri)
        }
    }

    private fun uploadImageToFirebase(fileUri: Uri?) {
        if (fileUri != null) {
            val refStorage = FirebaseStorage.getInstance().reference.child("userIMG/$caminho")
            //var uploadTask:StorageTask<*>
            var uploadTask = refStorage.putFile(fileUri)
        }
    }
}