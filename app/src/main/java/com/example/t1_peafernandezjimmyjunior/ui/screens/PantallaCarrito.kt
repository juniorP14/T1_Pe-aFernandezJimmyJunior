package com.example.t1_peafernandezjimmyjunior.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteSweep
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.t1_peafernandezjimmyjunior.model.*
import com.example.t1_peafernandezjimmyjunior.ui.components.*

@Composable
fun PantallaCarrito() {
    var libros by remember { mutableStateOf(listOf<Libro>()) }
    var notificacion by remember { mutableStateOf<Notificacion?>(null) }
    var mostrarDialogoLibro by remember { mutableStateOf(false) }
    var mostrarDialogoConfirmacion by remember { mutableStateOf<Pair<String, (() -> Unit)>?>(null) }
    var totalSinDescuento by remember { mutableStateOf(0.0) }
    var totalFinal by remember { mutableStateOf(0.0) }
    var descuentoAplicado by remember { mutableStateOf(Descuento.NINGUNO) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { mostrarDialogoLibro = true }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar libro")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Carrito de Libros", style = MaterialTheme.typography.headlineSmall)

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(libros) { libro ->
                    TarjetaLibro(libro) {
                        mostrarDialogoConfirmacion = "¿Eliminar '${libro.titulo}' del carrito?" to {
                            libros = libros - libro
                            notificacion = Notificacion("Libro eliminado del carrito", TipoNotificacion.ADVERTENCIA)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    if (libros.isEmpty()) {
                        notificacion = Notificacion("No hay libros en el carrito", TipoNotificacion.ERROR)
                        return@Button
                    }
                    totalSinDescuento = libros.sumOf { it.subtotal }
                    val cantidadTotal = libros.sumOf { it.cantidad }
                    descuentoAplicado = Descuento.obtenerDescuento(cantidadTotal)
                    val descuentoMonto = totalSinDescuento * (descuentoAplicado.porcentaje / 100.0)
                    totalFinal = totalSinDescuento - descuentoMonto

                    notificacion = Notificacion(
                        "${descuentoAplicado.mensaje} $descuentoMonto",
                        TipoNotificacion.EXITO
                    )
                }) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = "Calcular total")
                    Spacer(Modifier.width(4.dp))
                    Text("CALCULAR TOTAL")
                }

                Button(onClick = {
                    if (libros.isEmpty()) {
                        notificacion = Notificacion("No hay nada que limpiar", TipoNotificacion.INFO)
                        return@Button
                    }
                    mostrarDialogoConfirmacion = "¿Está seguro de limpiar el carrito?" to {
                        libros = emptyList()
                        totalSinDescuento = 0.0
                        totalFinal = 0.0
                        descuentoAplicado = Descuento.NINGUNO
                        notificacion = Notificacion("Carrito limpiado", TipoNotificacion.INFO)
                    }
                }) {
                    Icon(Icons.Default.DeleteSweep, contentDescription = "Limpiar carrito")
                    Spacer(Modifier.width(4.dp))
                    Text("LIMPIAR CARRITO")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text("Subtotal: S/. $totalSinDescuento")

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Descuento: ${descuentoAplicado.porcentaje}%  ")
                AssistChip(
                    onClick = {},
                    label = { Text(descuentoAplicado.mensaje) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = descuentoAplicado.color
                    )
                )
            }

            Text("Total: S/. $totalFinal")
        }
    }

    if (mostrarDialogoLibro) {
        DialogoNuevoLibro(
            onDismiss = { mostrarDialogoLibro = false },
            onAgregar = { titulo, precio, cantidad, categoria ->
                if (titulo.isBlank()) {
                    notificacion = Notificacion("Debe ingresar un título", TipoNotificacion.ERROR)
                    return@DialogoNuevoLibro
                }
                if (precio <= 0 || cantidad <= 0) {
                    notificacion = Notificacion("Precio y cantidad deben ser > 0", TipoNotificacion.ERROR)
                    return@DialogoNuevoLibro
                }
                if (categoria.isBlank()) {
                    notificacion = Notificacion("Debe ingresar una categoría", TipoNotificacion.ERROR)
                    return@DialogoNuevoLibro
                }
                libros = libros + Libro(titulo, precio, cantidad, categoria)
                notificacion = Notificacion("Libro agregado al carrito", TipoNotificacion.EXITO)
                mostrarDialogoLibro = false
            }
        )
    }

    mostrarDialogoConfirmacion?.let { (mensaje, onConfirmar) ->
        DialogoConfirmacion(
            mensaje = mensaje,
            onConfirmar = {
                onConfirmar()
                mostrarDialogoConfirmacion = null
            },
            onDismiss = { mostrarDialogoConfirmacion = null }
        )
    }

    BarraNotificacion(notificacion = notificacion) {
        notificacion = null
    }
}