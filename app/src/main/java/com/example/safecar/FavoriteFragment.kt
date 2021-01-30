package com.example.safecar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safecar.model.FavOficinas
import com.example.safecar.model.OficinasFavAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view_favs.adapter = OficinasFavAdapter(requireActivity())
        recycler_view_favs.layoutManager = LinearLayoutManager(activity)
        println("WHYYYY")
        loadIdFavs()
    }

//    override fun onDestroy() {
//        super.onDestroy()
////        favs.clear()
////        recyclerView?.adapter?.notifyDataSetChanged()
//        println("Destroyyyyy")
//    }


    private fun loadIdFavs() {
        println("LODA ID FAV")

        val userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
        val docRef = Firebase.firestore.collection("Users").document(userId)
        docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        val favs = document["idFavoritos"] as List<Map<String, Any>>?
                        loadFavs(favs)
                        println(favs)
                    } else {
                        Toast.makeText(requireContext(), "Usuário não existe", Toast.LENGTH_LONG).show()
                    }
                }
                .addOnFailureListener{ exception ->
                    Toast.makeText(requireContext(), "Ocorreu um erro na ligação com a base de dados", Toast.LENGTH_LONG).show()
                }
    }

    private fun loadFavs(lista: List<Map<String, Any>>?){
        var teste: ArrayList<String> = ArrayList()
        println("load favs")
        var x = 0
        var testeString: String
        println(lista!!.size)
        val listIterator = lista.listIterator()

        while (listIterator.hasNext()) {
            listIterator.next()
            teste.add(lista.get(x).toString())
            println(lista[x])
            x += 1
        }


        x=0
        val favs = mutableListOf<FavOficinas>()
        for (item: String in teste){
            testeString= item
            var fav = Firebase.firestore.collection("Oficinas").whereEqualTo("id", testeString)
            fav.addSnapshotListener { snapshot, e ->

                for (document in snapshot!!.documents){
                    println("Entrou no segundo ciclo")
                    val favoritos = FavOficinas(
                            "${document.data?.get("id")}",
                            "${document.data?.get("nome")}",
                            "${document.data?.get("descricao")}",
                            "${document.data?.get("disponibilidade")}",
                            "${document.data?.get("estrelas")}",
                            "${document.data?.get("oficinaIMG")}",
                            "${document.data?.get("reboque")}",
                            "${document.data?.get("morada")}"
                    )
                    //println(carro)

                    favs += favoritos
                }
                println(favs)

                val adapter = recycler_view_favs.adapter as OficinasFavAdapter
                //adapter.submitList(favs)
                adapter.submitList(if (favs != null) ArrayList(favs) else null)
            }
        }
    }
}