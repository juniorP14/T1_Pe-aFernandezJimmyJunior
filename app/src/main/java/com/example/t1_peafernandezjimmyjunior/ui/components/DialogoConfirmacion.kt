package com.example.t1_peafernandezjimmyjunior.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun DialogoConfirmacion(
    mensaje: String,
    onConfirmar: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = { onConfirmar() }) { Text("Sí") }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text("No") }
        },
        title = { Text("Confirmación") },
        text = { Text(mensaje) }
    )
}