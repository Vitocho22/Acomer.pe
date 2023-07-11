package com.sinche.victor.loginyregister.restaurant.favoritos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sinche.victor.loginyregister.R
import com.sinche.victor.loginyregister.databinding.ActivityFavoritosBinding
import com.sinche.victor.loginyregister.mapa.MainActivity
import com.sinche.victor.loginyregister.restaurant.MainActivityRes
import com.sinche.victor.loginyregister.restaurant.RestauranteAdapter

class FavoritosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritosBinding
    private lateinit var favoritosAdapter: RestauranteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigationView() // Configurar el BottomNavigationView

        // Obtén la lista de favoritos del FavoritosManager
        val favoritos = FavoritosManager.obtenerFavoritos()

        // Configura el adaptador con la lista de favoritos
        favoritosAdapter = RestauranteAdapter(favoritos)

        // Configura el LinearLayoutManager y el adaptador del RecyclerView
        binding.recyclerViewFavoritos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewFavoritos.adapter = favoritosAdapter

        // Configura el OnClickListener del botón "Limpiar Favoritos"
        binding.btnLimpiarFavoritos.setOnClickListener {
            FavoritosManager.limpiarFavoritos()
            favoritosAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Se han limpiado los favoritos", Toast.LENGTH_SHORT).show()
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
                    //startActivity(Intent(this, FavoritosActivity::class.java))// Acción para el elemento "Favoritos"
                    true
                }
                else -> false
            }
        }
    }
}
