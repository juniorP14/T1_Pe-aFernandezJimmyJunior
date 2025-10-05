package com.example.t1_peafernandezjimmyjunior.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.t1_peafernandezjimmyjunior.model.Notificacion
import com.example.t1_peafernandezjimmyjunior.model.TipoNotificacion

@Composable
fun BarraNotificacion(
    notificacion: Notificacion?,
    onDismiss: () -> Unit
) {
    notificacion?.let {
        if (it.visible) {
            Snackbar(
                modifier = Modifier,
                action = {
                    TextButton(onClick = { onDismiss() }) {
                        Text("OK")
                    }
                }
            ) {
                val color = when (it.tipo) {
                    TipoNotificacion.EXITO -> MaterialTheme.colorScheme.primary
                    TipoNotificacion.ERROR -> MaterialTheme.colorScheme.error
                    TipoNotificacion.ADVERTENCIA -> MaterialTheme.colorScheme.tertiary
                    TipoNotificacion.INFO -> MaterialTheme.colorScheme.secondary
                }
                Text(text = it.mensaje, color = color)
            }
        }
    }
}


