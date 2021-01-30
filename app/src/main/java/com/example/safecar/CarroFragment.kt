package com.example.safecar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safecar.model.Carros
import com.example.safecar.model.CarrosAdpater
import com.example.safecar.model.OficinaAdapter
import com.example.safecar.model.idCarro
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_carro.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.ln



class CarroFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_carro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view_carro.adapter = CarrosAdpater(requireActivity())
        recycler_view_carro.layoutManager = LinearLayoutManager(activity)

        loadIdCarros()
    }



    private fun loadIdCarros() {
        //println("LoadCarros")

        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val docRef = Firebase.firestore.collection("Users").document(userId)
        docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val carros = document["idCarros"] as List<Map<String, Any>>?
                        //val teste = document["email"] as String
                        //println(teste)
                        loadCarros(carros)
                    } else {
                        Toast.makeText(context, "Usuário não existe", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener{ exception ->
                    Toast.makeText(context, "Ocorreu um erro na ligação com a base de dados", Toast.LENGTH_LONG).show()
        }
    }

    private fun loadCarros(lista: List<Map<String, Any>>?){
        var teste: ArrayList<String> = ArrayList()
        println("loadCarros")
        var x = 0
        var testeString: String
        println(lista!!.size)
        val listIterator = lista.listIterator()

        while (listIterator.hasNext()) {
            listIterator.next()
            teste.add(lista.get(x).toString())
            x += 1
        }


        x=0
        val carros = mutableListOf<Carros>()
        for (item: String in teste){
            testeString= item
            var carro = Firebase.firestore.collection("Carros").whereEqualTo("id", testeString)
            carro.addSnapshotListener { snapshot, e ->

                for (document in snapshot!!.documents){
                    println("Entrou no segundo ciclo")
                    val carro = Carros(
                            "${document.data?.get("id")}",
                            "${document.data?.get("Marca")}",
                            "${document.data?.get("Modelo")}",
                            "${document.data?.get("Foto")}",
                            "${document.data?.get("Ano")}",
                            "${document.data?.get("Histórico")}"
                    )
                    //println(carro)

                    carros += carro
                }
                //println(carros)
                val adapter = recycler_view_carro.adapter as CarrosAdpater
                adapter.submitList(carros)
            }
        }
    }
}
