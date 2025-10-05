package com.example.t1_peafernandezjimmyjunior.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogoNuevoLibro(
    onDismiss: () -> Unit,
    onAgregar: (String, Double, Int, String) -> Unit
) {
    var titulo by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }

    val categorias = listOf("Novela", "Ciencia", "Historia", "Tecnología")
    var expanded by remember { mutableStateOf(false) }

    var tituloError by remember { mutableStateOf(false) }
    var precioError by remember { mutableStateOf(false) }
    var cantidadError by remember { mutableStateOf(false) }
    var categoriaError by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = {
                    val precioNum = precio.toDoubleOrNull() ?: 0.0
                    val cantidadNum = cantidad.toIntOrNull() ?: 0

                    tituloError = titulo.isBlank()
                    precioError = precioNum <= 0
                    cantidadError = cantidadNum <= 0
                    categoriaError = categoria.isBlank()

                    if (tituloError || precioError || cantidadError || categoriaError) {
                        return@TextButton
                    }

                    onAgregar(titulo, precioNum, cantidadNum, categoria)
                    onDismiss()
                }
            ) { Text("Agregar") }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) { Text("Cancelar") }
        },
        title = { Text("Agregar Libro") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {

                OutlinedTextField(
                    value = titulo,
                    onValueChange = { titulo = it },
                    label = { Text("Título") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = tituloError
                )
                if (tituloError) {
                    Text("El título no puede estar vacío", color = MaterialTheme.colorScheme.error)
                }

                OutlinedTextField(
                    value = precio,
                    onValueChange = { precio = it },
                    label = { Text("Precio") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = precioError
                )
                if (precioError) {
                    Text("El precio debe ser mayor a 0", color = MaterialTheme.colorScheme.error)
                }

                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = cantidadError
                )
                if (cantidadError) {
                    Text("La cantidad debe ser mayor a 0", color = MaterialTheme.colorScheme.error)
                }

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = categoria,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Categoría") },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        isError = categoriaError
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        categorias.forEach { opcion ->
                            DropdownMenuItem(
                                text = { Text(opcion) },
                                onClick = {
                                    categoria = opcion
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                if (categoriaError) {
                    Text("Debe seleccionar una categoría", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    )
}