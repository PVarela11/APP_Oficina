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
import kotlin.properties.Delegates

class OficinaAdapter(val context: Context,val oficinas: MutableList<Oficina>) : RecyclerView.Adapter<OficinaAdapter.OficinaViewHolder>() {

    var id by Delegates.notNull<Int>()

    inner class OficinaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(oficina: Oficina) {
            with(oficina){
                //itemView.findViewById<TextView>(R.id.txt_user).text = oficina.nome.first().toString()
                itemView.txt_user.text = nome.toString()
                itemView.txt_subject.text = descricao.toString()


                val packageName = "com.example.safecar"
                //val drawableId: Int = context.getResources().getIdentifier(caminho, "drawable", packageName)
                //val draw = "R.drawable."
                val caminho = oficinaIMG.toString()
                val imageResource: Int = context.getResources().getIdentifier(caminho, "drawable", packageName)
                itemView.img_oficina.setImageResource(imageResource)

                var numDisp : Int = disponibilidade
                itemView.img_disponibilidade.setImageResource(
                    when (numDisp) {
                        0 -> R.drawable.ponto_vermelho
                        1 -> R.drawable.ponto_amarelo
                        else -> R.drawable.ponto_verde
                    }
                )
                var numStar : Int = estrelas
                itemView.img_stars.setImageResource(
                    when (numStar) {
                        1 -> R.drawable.estrela1
                        2 -> R.drawable.estrela2
                        3 -> R.drawable.estrela3
                        4 -> R.drawable.estrela4
                        else -> R.drawable.estrela5
                    }
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OficinaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return OficinaViewHolder(view)
    }

    override fun onBindViewHolder(holder: OficinaViewHolder, position: Int) {
        holder.bind(oficinas[position])
    }

    override fun getItemCount(): Int = oficinas.size
}
