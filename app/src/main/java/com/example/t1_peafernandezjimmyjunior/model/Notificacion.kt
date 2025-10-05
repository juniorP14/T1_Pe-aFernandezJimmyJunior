package com.example.t1_peafernandezjimmyjunior.model

data class Notificacion(
    val mensaje: String,
    val tipo: TipoNotificacion,
    val visible: Boolean = true
)