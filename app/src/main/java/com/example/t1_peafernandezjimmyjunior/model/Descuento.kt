package com.example.t1_peafernandezjimmyjunior.model

import androidx.compose.ui.graphics.Color

enum class Descuento(val rango: IntRange?, val porcentaje: Int, val color: Color, val mensaje: String) {
    NINGUNO(1..2, 0, Color.Gray, "No hay descuento aplicado"),
    DIEZ(3..5, 10, Color.Green, "¡Genial!"),
    QUINCE(6..10, 15, Color.Blue, "¡Excelente!"),
    VEINTE(null, 20, Color(0xFFFFD700), "¡Increíble!");

    companion object {
        fun obtenerDescuento(totalLibros: Int): Descuento {
            return when {
                totalLibros in 1..2 -> NINGUNO
                totalLibros in 3..5 -> DIEZ
                totalLibros in 6..10 -> QUINCE
                totalLibros >= 11 -> VEINTE
                else -> NINGUNO
            }
        }
    }
}