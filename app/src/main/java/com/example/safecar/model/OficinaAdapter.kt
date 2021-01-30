package com.example.safecar.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler_view.view.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.example.safecar.ItemActivity
import com.example.safecar.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.carros_rv.view.*

//class OficinaAdapter(val context: Context,val oficinas: MutableList<Oficina>) : RecyclerView.Adapter<OficinaAdapter.OficinaViewHolder>() {
class OficinaAdapter(val context: Context): ListAdapter<Oficina, OficinaAdapter.OficinaViewHolder>(
    DiffCallback()
){
    //var filterList = ArrayList<String>()
    var lista: ArrayList<String> = ArrayList()
    inner class OficinaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        init {
            //Quando clica num item entra na função em baixo
            itemView.setOnClickListener {
                println("TesteONCLICK(dfgjbjkfdhgdfkhgluikdfhglkhdfklghklfdhgihdfkljghfdg)")
                val pos = absoluteAdapterPosition
                val oficina2 = getItem(pos)
                lista.removeAll(lista)
                //val list = arrayListOf<String>(oficina2.id, oficina2.nome, oficina2.descricao, oficina2.disponibilidade, oficina2.estrelas, oficina2.oficinaIMG, oficina2.reboque, oficina2.morada)
                lista.add(oficina2.id)
                lista.add(oficina2.nome)
                lista.add(oficina2.descricao)
                lista.add(oficina2.disponibilidade)
                lista.add(oficina2.estrelas)
                lista.add(oficina2.oficinaIMG)
                lista.add(oficina2.reboque)
                lista.add(oficina2.morada)
                val intent = Intent(itemView.context, ItemActivity::class.java)
                intent.putExtra("Oficina", lista)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OficinaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_recycler_view,
            parent,
            false
        )
        return OficinaViewHolder(view)
    }

    override fun onBindViewHolder(holder: OficinaViewHolder, position: Int) {


        val oficina = getItem(position)


        holder.itemView.txt_user.text = oficina.nome
        holder.itemView.txt_subject.text = oficina.morada


//        val packageName = "com.example.safecar"
//        val caminho = oficina.oficinaIMG
//        val imageResource: Int = context.getResources().getIdentifier(
//            caminho,
//            "drawable",
//            packageName
//        )
//        holder.itemView.img_oficina.setImageResource(imageResource)

        val numDisp : String = oficina.disponibilidade
        holder.itemView.img_disponibilidade.setImageResource(
            when (numDisp) {
                "0" -> R.drawable.ponto_vermelho
                "1" -> R.drawable.ponto_amarelo
                else -> R.drawable.ponto_verde
            }
        )


        val numStar : String = oficina.estrelas

        if (numStar == "0"){
            holder.itemView.ratingBar.rating = 0.toFloat()
        }
        if (numStar == "0.5"){
            holder.itemView.ratingBar.rating = 0.5.toFloat()
        }
        if (numStar == "1"){
            holder.itemView.ratingBar.rating = 1.toFloat()
        }
        if (numStar == "1.5"){
            holder.itemView.ratingBar.rating = 1.5.toFloat()
        }
        if (numStar == "2"){
            holder.itemView.ratingBar.rating = 2.toFloat()
        }
        if (numStar == "2.5"){
            holder.itemView.ratingBar.rating = 2.5.toFloat()
        }
        if (numStar == "3"){
            holder.itemView.ratingBar.rating = 3.toFloat()
        }
        if (numStar == "3.5"){
            holder.itemView.ratingBar.rating = 3.5.toFloat()
        }
        if (numStar == "4"){
            holder.itemView.ratingBar.rating = 4.toFloat()
        }
        if (numStar == "4.5"){
            holder.itemView.ratingBar.rating = 4.5.toFloat()
        }
        if (numStar == "5"){
            holder.itemView.ratingBar.rating = 5.toFloat()
        }


        var imageref = Firebase.storage.reference.child("oficinaIMG/${oficina.oficinaIMG}.jpg")



        imageref.downloadUrl.addOnSuccessListener {Uri->

            val imageURL = Uri.toString()
            var imageoficina = holder.itemView.img_oficina

            Glide.with(context)
                .load(imageURL)
                .into(imageoficina)

        }
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].id.toLong()
    }



    class DiffCallback : DiffUtil.ItemCallback<Oficina>() {

        override fun areItemsTheSame(oldItem: Oficina, newItem: Oficina) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Oficina, newItem: Oficina) =
            oldItem == newItem
    }
}
