package com.example.safecar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.safecar.model.Carros
import com.example.safecar.model.idCarro
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.ln


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CarroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CarroFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        loadIdCarros()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carro, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CarroFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarroFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



    private fun loadIdCarros() {
        println("LoadCarros")
        val userId = "1"
        val user = Firebase.firestore.collection("Users").whereEqualTo("id", userId)
        user.addSnapshotListener { snapshot, e ->
            val idCarros = mutableListOf<idCarro>()
            for (document in snapshot!!.documents){
                val carros = document!!["idCarros"] as List<Map<String, Any>>?
                //println("ola tipo")
                println(carros)

                loadCarros(carros)
            }
        }

    }

    private fun loadCarros(lista: List<Map<String, Any>>?){
        var teste: ArrayList<String> = ArrayList()
        var x = 0
        var testeString: String
        println(lista)
        print("tamanho da lista:")
        println(lista!!.size)
        val listIterator = lista.listIterator()

        while (listIterator.hasNext()) {
            listIterator.next()
            //println("printando ids dos carros")
            //println(lista[x])
            teste.add(lista.get(x).toString())
            //println("Printando o teste")
            //println(teste[x])
            x += 1
        }


        x=0
        for (item: String in teste){
            testeString= item
            println(item)
            var carro = Firebase.firestore.collection("Carros").whereEqualTo("id", testeString)
            println("acedeu a colletcion")
            println(testeString)
            carro.addSnapshotListener { snapshot, e ->
                val carros = mutableListOf<Carros>()
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
                    println(carros)
                }
            }
        }
    }
}
