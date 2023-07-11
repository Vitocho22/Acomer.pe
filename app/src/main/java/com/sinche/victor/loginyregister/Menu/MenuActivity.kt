package com.sinche.victor.loginyregister.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sinche.victor.loginyregister.R
import com.sinche.victor.loginyregister.mapa.MainActivity
import com.sinche.victor.loginyregister.restaurant.MainActivityRes
import com.sinche.victor.loginyregister.restaurant.favoritos.FavoritosActivity

class MenuActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)

        // Obtén una referencia al elemento del menú correspondiente
        val menuItem = bottomNavigationView.menu.findItem(R.id.action_mapa)
        // Marca el elemento como seleccionado
        menuItem?.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_mapa -> {
                    // No se realiza ninguna acción porque ya estamos en la actividad correspondiente
                    true
                }
                R.id.action_restaurantes -> {
                    startActivity(Intent(this, MainActivityRes::class.java))
                    true
                }
                R.id.action_favoritos -> {
                    startActivity(Intent(this, FavoritosActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}




