package com.example.t1_peafernandezjimmyjunior.model

data class Libro(
    val titulo: String,
    val precio: Double,
    val cantidad: Int,
    val categoria: String
) {
    val subtotal: Double
        get() = precio * cantidad
}