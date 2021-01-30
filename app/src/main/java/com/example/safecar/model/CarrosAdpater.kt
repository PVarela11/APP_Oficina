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
import com.example.safecar.model.CarrosAdpater.ViewHolder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.carros_rv.view.*
import kotlinx.android.synthetic.main.fragment_user.view.*
import kotlinx.android.synthetic.main.item_recycler_view.view.*

class CarrosAdpater(val context: Context): ListAdapter<Carros, ViewHolder>(
    DiffCallback()
){



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.carros_rv,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].id.toLong()
    }

    override fun onBindViewHolder(holder: CarrosAdpater.ViewHolder, position: Int) {
        println("adapter")
        val carros = getItem(position)
        holder.itemView.txtmarca.text = carros.marca
        holder.itemView.txtmodelo.text =carros.modelo
        holder.itemView.txtano.text = carros.ano


        var imageref = Firebase.storage.reference.child("carroIMG/${carros.foto}.jpg")



        imageref.downloadUrl.addOnSuccessListener {Uri->

            val imageURL = Uri.toString()
            var imagecarro = holder.itemView.img_carro

            Glide.with(context)
                .load(imageURL)
                .into(imagecarro)

        }


}

    class DiffCallback : DiffUtil.ItemCallback<Carros>() {

        override fun areItemsTheSame(oldItem: Carros, newItem: Carros) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Carros, newItem: Carros) =
            oldItem == newItem
    }


}
