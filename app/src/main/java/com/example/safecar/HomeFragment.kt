package com.example.safecar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safecar.model.Oficina
import com.example.safecar.model.OficinaAdapter
import com.google.firebase.firestore.Query
//import com.example.safecar.model.fakeOficinas
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    //private lateinit var recyclerView: RecyclerView
    //private lateinit var viewAdapter: RecyclerView.Adapter<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.adapter = OficinaAdapter(requireActivity())
        recycler_view.layoutManager = LinearLayoutManager(activity)
        loadOficinas(0)

        home_toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.rating -> {
                    val ordena = 1
                    loadOficinas(ordena)
                    true
                }
                R.id.disponibilidade -> {
                    val ordena = 2
                    loadOficinas(ordena)
                    true
                }

                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

    private fun loadOficinas(ordena: Int) {
        var teste = "id"
        if (ordena == 1){
            println("Ordenar por rating")
            teste = "estrelas"
        }else if (ordena == 2){
            teste = "disponibilidade"
        }else{
            teste = "id"
        }
        val docs = Firebase.firestore.collection("Oficinas").orderBy(teste, Query.Direction.DESCENDING)
        docs.addSnapshotListener { snapshot, e ->
            val oficinas = mutableListOf<Oficina>()
            for (document in snapshot!!.documents) {
                val oficina = Oficina(
                        "${document.data?.get("id")}",
                        "${document.data?.get("nome")}",
                        "${document.data?.get("descricao")}",
                        "${document.data?.get("disponibilidade")}",
                        "${document.data?.get("estrelas")}",
                        "${document.data?.get("oficinaIMG")}",
                        "${document.data?.get("reboque")}",
                        "${document.data?.get("morada")}"
                )
                oficinas += oficina
            }
            val adapter = recycler_view.adapter as OficinaAdapter
            adapter.submitList(oficinas)
        }
    }
}