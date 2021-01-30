package com.example.safecar.model

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.safecar.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_favorito.view.*
import kotlinx.android.synthetic.main.item_recycler_view.view.*
import kotlinx.android.synthetic.main.item_recycler_view.view.txt_user
import kotlinx.android.synthetic.main.rv_favs.view.*

class OficinasFavAdapter(val context: Context): ListAdapter<FavOficinas, OficinasFavAdapter.ViewHolder>(
        DiffCallback()
){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
                R.layout.rv_favs,
                parent,
                false
        )
        return ViewHolder(view)
    }
    override fun getItemId(position: Int): Long {
        return currentList[position].id.toLong()
    }


//    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        println("ADAPTEEEEEEERRRRRRRR")
        val fav = getItem(position)
        println(fav.nome)
        println(fav.descricao)
        println(fav.disponibilidade)
        println(fav.estrelas)
        println(fav.oficinaIMG)
        holder.itemView.fav_user.text = fav.nome
        holder.itemView.fav_subject.text = fav.morada

        val numDisp : String = fav.disponibilidade
        holder.itemView.fav_disponibilidade.setImageResource(
                when (numDisp) {
                    "0" -> R.drawable.ponto_vermelho
                    "1" -> R.drawable.ponto_amarelo
                    else -> R.drawable.ponto_verde
                })
        val numStar : String = fav.estrelas
        if (numStar == "0"){
            holder.itemView.fav_ratingBar.rating = 0.toFloat()
        }
        if (numStar == "0.5"){
            holder.itemView.fav_ratingBar.rating = 0.5.toFloat()
        }
        if (numStar == "1"){
            holder.itemView.fav_ratingBar.rating = 1.toFloat()
        }
        if (numStar == "1.5"){
            holder.itemView.fav_ratingBar.rating = 1.5.toFloat()
        }
        if (numStar == "2"){
            holder.itemView.fav_ratingBar.rating = 2.toFloat()
        }
        if (numStar == "2.5"){
            holder.itemView.fav_ratingBar.rating = 2.5.toFloat()
        }
        if (numStar == "3"){
            holder.itemView.fav_ratingBar.rating = 3.toFloat()
        }
        if (numStar == "3.5"){
            holder.itemView.fav_ratingBar.rating = 3.5.toFloat()
        }
        if (numStar == "4"){
            holder.itemView.fav_ratingBar.rating = 4.toFloat()
        }
        if (numStar == "4.5"){
            holder.itemView.fav_ratingBar.rating = 4.5.toFloat()
        }
        if (numStar == "5"){
            holder.itemView.fav_ratingBar.rating = 5.toFloat()
        }

        var imageref = Firebase.storage.reference.child("oficinaIMG/${fav.oficinaIMG}.jpg")

        imageref.downloadUrl.addOnSuccessListener {Uri->

            val imageURL = Uri.toString()
            var imagefav = holder.itemView.fav_img

            Glide.with(context)
                    .load(imageURL)
                    .into(imagefav)

        }
    }
    //        holder.bind(fav)


    class DiffCallback : DiffUtil.ItemCallback<FavOficinas>() {

        override fun areItemsTheSame(oldItem: FavOficinas, newItem: FavOficinas) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: FavOficinas, newItem: FavOficinas) =
            oldItem == newItem
    }
}