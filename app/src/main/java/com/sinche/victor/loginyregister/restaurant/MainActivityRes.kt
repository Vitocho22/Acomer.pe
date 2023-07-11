package com.sinche.victor.loginyregister.restaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sinche.victor.loginyregister.R
import com.sinche.victor.loginyregister.databinding.ActivityMainResBinding
import com.sinche.victor.loginyregister.mapa.MainActivity
import com.sinche.victor.loginyregister.restaurant.Api.Restaurante
import com.sinche.victor.loginyregister.restaurant.Api.RetrofitClient
import com.sinche.victor.loginyregister.restaurant.favoritos.FavoritosActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityRes : AppCompatActivity() {

    private lateinit var binding: ActivityMainResBinding
    private lateinit var restauranteAdapter: RestauranteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainResBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigationView() // Configurar el BottomNavigationView

        // Obtener datos de la API (similar a tu código original)

        val retroftiTraer = RetrofitClient.consumirApi.getTraer()

        retroftiTraer.enqueue(object : Callback<Restaurante> {
            override fun onResponse(call: Call<Restaurante>, response: Response<Restaurante>) {
                val restaurante = response.body()
                val restaurantesList = restaurante?.restaurantes

                // Configurar RestauranteAdapter
                restauranteAdapter = RestauranteAdapter(restaurantesList.orEmpty())

                // Configurar LayoutManager y Adapter del RecyclerView
                binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivityRes)
                binding.recyclerView.adapter = restauranteAdapter
            }

            override fun onFailure(call: Call<Restaurante>, t: Throwable) {
                Toast.makeText(this@MainActivityRes, "Error al consultar Api Rest", Toast.LENGTH_SHORT).show()
            }
        })
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
                    // Acción para el elemento "Restaurantes"
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
}