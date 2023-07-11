package com.sinche.victor.loginyregister.restaurant.Api

data class RestauranteX(
    val descripcion: String,
    val direccion: String,
    val id: Int,
    val nombre: String,
    // Otros atributos del restaurante
    var esFavorito: Boolean = false
)