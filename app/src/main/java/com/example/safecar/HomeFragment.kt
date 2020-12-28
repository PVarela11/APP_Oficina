package com.example.safecar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.safecar.model.Oficina
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
        //mudar as imagens das estrelas para o widget android de rating

        //recycler_view.adapter = OficinaAdapter(requireActivity(),fakeOficinas())
        recycler_view.adapter = OficinaAdapter(requireActivity())
        recycler_view.layoutManager = LinearLayoutManager(activity)
        loadOficinas()
    }

    private fun loadOficinas() {
        val docs = Firebase.firestore.collection("Oficinas").orderBy("nome")
        docs.addSnapshotListener { snapshot, e ->
            val oficinas = mutableListOf<Oficina>()
            for (document in snapshot!!.documents){
                val oficina = Oficina(
                    "${document.data?.get("nome")}",
                    "${document.data?.get("nome")}",
                    "${document.data?.get("descricao")}",
                    "${document.data?.get("disponibilidade")}",
                    "${document.data?.get("estrelas")}",
                    "${document.data?.get("oficinaIMG")}"
                )

                oficinas += oficina
            }

            val adapter = recycler_view.adapter as OficinaAdapter
            adapter.submitList(oficinas)
        }
    }

}