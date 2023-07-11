package com.sinche.victor.loginyregister.restaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.sinche.victor.loginyregister.R
import com.sinche.victor.loginyregister.databinding.ActivityDetallesRestauranteBinding
import com.sinche.victor.loginyregister.mapa.MainActivity
import com.sinche.victor.loginyregister.restaurant.Api.RestauranteX
import com.sinche.victor.loginyregister.restaurant.favoritos.FavoritosActivity
import com.sinche.victor.loginyregister.restaurant.favoritos.FavoritosManager

class DetallesRestauranteActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_RESTAURANTE = "restaurante"
    }

    lateinit var binding: ActivityDetallesRestauranteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesRestauranteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigationView() // Configurar el BottomNavigationView

        val restauranteJson = intent.getStringExtra(EXTRA_RESTAURANTE)
        val gson = Gson()
        val restaurante = gson.fromJson(restauranteJson, RestauranteX::class.java)

        // Mostrar los detalles del restaurante en la interfaz de usuario
        binding.ivRestaurante.setImageResource(getImageResource(restaurante.id))
        binding.tvNombre.text = restaurante.nombre
        binding.tvDireccion.text = restaurante.direccion
        binding.tvDescripcion.text = restaurante.descripcion

        // Configurar el OnClickListener del botón
        binding.btnAgregarFavoritos.setOnClickListener {
            val restaurante = gson.fromJson(restauranteJson, RestauranteX::class.java)
            FavoritosManager.agregarRestaurante(restaurante)
            Toast.makeText(this, "Agregado a favoritos", Toast.LENGTH_SHORT).show()
        }


    }
    private fun setupBottomNavigationView() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_mapa -> {
                    startActivity(Intent(this, MainActivity::class.java))// Acción para el elemento "Mapa"
                    true
                }
                R.id.action_restaurantes -> {
                    startActivity(Intent(this, MainActivityRes::class.java))// Acción para el elemento "Restaurantes"
                    true
                }
                R.id.action_favoritos -> {
                    startActivity(Intent(this, FavoritosActivity::class.java))// Acción para el elemento "Favoritos"
                    true
                }
                else -> false
            }
        }
    }

    private fun getImageResource(restauranteId: Int): Int {
        val nombreImagen = "restaurante${restauranteId % 5 + 1}"
        return resources.getIdentifier(nombreImagen, "drawable", packageName)
    }
}
