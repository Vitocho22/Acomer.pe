package com.sinche.victor.loginyregister.restaurant.favoritos

import com.sinche.victor.loginyregister.restaurant.Api.RestauranteX

object FavoritosManager {
    private val favoritosList = mutableListOf<RestauranteX>()

    fun agregarRestaurante(restaurante: RestauranteX) {
        favoritosList.add(restaurante)
    }

    fun obtenerFavoritos(): List<RestauranteX> {
        return favoritosList.toList()
    }
    fun limpiarFavoritos() {
        favoritosList.clear()
    }

    // Puedes implementar más métodos según tus necesidades
}
