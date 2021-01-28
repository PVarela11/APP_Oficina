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
import com.example.safecar.ItemActivity
import com.example.safecar.R

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
                //Toast.makeText(context, oficina2.nome, Toast.LENGTH_SHORT).show()
                //aval item = OficinaViewHolder(itemView.).absoluteAdapterPosition
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
        //holder.bind(oficinas[position])

        val oficina = getItem(position)
        //val pos1 = holder.absoluteAdapterPosition
        //filterList.add(oficina.nome)
        //println(filterList)
        //holder.bind(oficina)
        //holder.itemView.setOnClickListener { listener(oficina) }

        holder.itemView.txt_user.text = oficina.nome
        holder.itemView.txt_subject.text = oficina.morada


        val packageName = "com.example.safecar"
        //val drawableId: Int = context.getResources().getIdentifier(caminho, "drawable", packageName)
        //val draw = "R.drawable."
        val caminho = oficina.oficinaIMG
        val imageResource: Int = context.getResources().getIdentifier(
            caminho,
            "drawable",
            packageName
        )
        holder.itemView.img_oficina.setImageResource(imageResource)

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
