package com.example.safecar

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safecar.model.Oficina
import kotlinx.android.synthetic.main.item_recycler_view.*
import kotlinx.android.synthetic.main.item_recycler_view.view.*
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

//class OficinaAdapter(val context: Context,val oficinas: MutableList<Oficina>) : RecyclerView.Adapter<OficinaAdapter.OficinaViewHolder>() {
class OficinaAdapter(val context: Context): ListAdapter<Oficina, OficinaAdapter.OficinaViewHolder>(
    DiffCallback()
){

    inner class OficinaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(oficina: Oficina) {
            with(oficina){
                //itemView.findViewById<TextView>(R.id.txt_user).text = oficina.nome.first().toString()
                itemView.txt_user.text = nome
                itemView.txt_subject.text = descricao


                val packageName = "com.example.safecar"
                //val drawableId: Int = context.getResources().getIdentifier(caminho, "drawable", packageName)
                //val draw = "R.drawable."
                val caminho = oficinaIMG
                val imageResource: Int = context.getResources().getIdentifier(
                    caminho,
                    "drawable",
                    packageName
                )
                itemView.img_oficina.setImageResource(imageResource)

                val numDisp : String = disponibilidade
                itemView.img_disponibilidade.setImageResource(
                    when (numDisp) {
                        "0" -> R.drawable.ponto_vermelho
                        "1" -> R.drawable.ponto_amarelo
                        else -> R.drawable.ponto_verde
                    }
                )


                val numStar : String = estrelas

                if (numStar == "0"){
                    itemView.ratingBar.rating = 0.toFloat()
                }
                if (numStar == "0.5"){
                    itemView.ratingBar.rating = 0.5.toFloat()
                }
                if (numStar == "1"){
                    itemView.ratingBar.rating = 1.toFloat()
                }
                if (numStar == "1.5"){
                    itemView.ratingBar.rating = 1.5.toFloat()
                }
                if (numStar == "2"){
                    itemView.ratingBar.rating = 2.toFloat()
                }
                if (numStar == "2.5"){
                    itemView.ratingBar.rating = 2.5.toFloat()
                }
                if (numStar == "3"){
                    itemView.ratingBar.rating = 3.toFloat()
                }
                if (numStar == "3.5"){
                    itemView.ratingBar.rating = 3.5.toFloat()
                }
                if (numStar == "4"){
                    itemView.ratingBar.rating = 4.toFloat()
                }
                if (numStar == "4.5"){
                    itemView.ratingBar.rating = 4.5.toFloat()
                }
                if (numStar == "5"){
                    itemView.ratingBar.rating = 5.toFloat()
                }


                //this.rb.setRating(2)
                //var rate : String = estrelas
                //itemView.ratingBar.rating = numStar.toFloat()

                //itemView.ratingBar.rating

                //itemView.img_stars.setImageResource(
                    //when (numStar) {
                        //"1" -> R.drawable.estrela1
                        //"2" -> R.drawable.estrela2
                        //"3" -> R.drawable.estrela3
                        //"4" -> R.drawable.estrela4
                        //else -> R.drawable.estrela5
                    //}
                //)
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
        //holder.bind(oficinas[position])
        val oficina = getItem(position)
        holder.bind(oficina)
    }

    override fun getItemId(position: Int): Long {
        return currentList[position].id.toLong()
    }

    //override fun getItemCount(): Int = oficina.size

    private class DiffCallback : DiffUtil.ItemCallback<Oficina>() {

        override fun areItemsTheSame(oldItem: Oficina, newItem: Oficina) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Oficina, newItem: Oficina) =
            oldItem == newItem
    }
}
