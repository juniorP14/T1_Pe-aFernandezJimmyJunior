package com.example.t1_peafernandezjimmyjunior.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.t1_peafernandezjimmyjunior.model.Libro

@Composable
fun TarjetaLibro(
    libro: Libro,
    onEliminar: (Libro) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = libro.titulo, style = MaterialTheme.typography.titleMedium)
                Text(text = "Categoría: ${libro.categoria}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Cantidad: ${libro.cantidad}", style = MaterialTheme.typography.bodySmall)
                Text(text = "Subtotal: S/. ${libro.subtotal}", style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = { onEliminar(libro) }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar libro")
            }
        }
    }
}