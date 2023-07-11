package com.sinche.victor.loginyregister.restaurant

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sinche.victor.loginyregister.R
import com.sinche.victor.loginyregister.restaurant.Api.RestauranteX

class RestauranteAdapter(private val restaurantes: List<RestauranteX>) :
    RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder>() {

    // Array de recursos de imágenes
    private val imagenesRestaurante = arrayOf(
        R.drawable.restaurante1,
        R.drawable.restaurante2,
        R.drawable.restaurante3,
        R.drawable.restaurante4,
        R.drawable.restaurante5
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurante, parent, false)
        return RestauranteViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestauranteViewHolder, position: Int) {
        val restaurante = restaurantes[position]
        holder.bind(restaurante)

// Obtiene el índice correspondiente en el array de imágenes
        val imagenIndex = restaurante.id % imagenesRestaurante.size

        // Establece la imagen en el ImageView
        holder.itemView.findViewById<ImageView>(R.id.ivRestaurante).setImageResource(imagenesRestaurante[imagenIndex])

        holder.itemView.setOnClickListener {
            val gson = Gson()
            val restauranteJson = gson.toJson(restaurante)

            val intent = Intent(holder.itemView.context, DetallesRestauranteActivity::class.java)
            intent.putExtra(DetallesRestauranteActivity.EXTRA_RESTAURANTE, restauranteJson)
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return restaurantes.size
    }

    inner class RestauranteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivRestaurante: ImageView = itemView.findViewById(R.id.ivRestaurante)
        private val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        private val tvDireccion: TextView = itemView.findViewById(R.id.tvDireccion)

        fun bind(restaurante: RestauranteX) {
            val nombreImagen = "restaurante${adapterPosition % 5 + 1}"
            val resourceId = itemView.context.resources.getIdentifier(
                nombreImagen, "drawable", itemView.context.packageName)
            ivRestaurante.setImageResource(resourceId)

            tvNombre.text = restaurante.nombre
            tvDireccion.text = restaurante.direccion
        }
    }

}
