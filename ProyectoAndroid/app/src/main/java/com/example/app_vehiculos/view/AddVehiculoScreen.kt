package com.example.app_vehiculos.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.app_vehiculos.model.Vehiculo
import com.example.app_vehiculos.R

@Composable
fun AddVehiculoScreen(
    onSave: (Vehiculo) -> Unit,
    onCancel: () -> Unit
) {
    var placa by remember { mutableStateOf("") }
    var marca by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var color by remember { mutableStateOf("") }
    var costoPorDia by remember { mutableStateOf("") }
    var activo by remember { mutableStateOf(true) }
    var imagenSeleccionada by remember { mutableStateOf("toyota") }

    val imagenes = mapOf(
        "toyota" to R.drawable.toyota,
        "chevrolet" to R.drawable.chevrolet,
        "nissan" to R.drawable.nissan
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Nuevo Vehículo", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(value = placa, onValueChange = { placa = it }, label = { Text("Placa") })
        OutlinedTextField(value = marca, onValueChange = { marca = it }, label = { Text("Marca") })
        OutlinedTextField(
            value = anio,
            onValueChange = { anio = it },
            label = { Text("Año") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        OutlinedTextField(value = color, onValueChange = { color = it }, label = { Text("Color") })
        OutlinedTextField(
            value = costoPorDia,
            onValueChange = { costoPorDia = it },
            label = { Text("Costo por día") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = activo, onCheckedChange = { activo = it })
            Text("¿Activo?")
        }

        // Imagen Dropdown
        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            OutlinedTextField(
                value = imagenSeleccionada,
                onValueChange = {},
                readOnly = true,
                label = { Text("Imagen") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                imagenes.keys.forEach { nombre ->
                    DropdownMenuItem(
                        text = { Text(nombre.capitalize()) },
                        onClick = {
                            imagenSeleccionada = nombre
                            expanded = false
                        }
                    )
                }
            }
        }

        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                onSave(
                    Vehiculo(
                        placa = placa,
                        marca = marca,
                        anio = anio.toIntOrNull() ?: 0,
                        color = color,
                        costoPorDia = costoPorDia.toDoubleOrNull() ?: 0.0,
                        activo = activo,
                        imagenResId = imagenes[imagenSeleccionada] ?: R.drawable.toyota
                    )
                )
            }) {
                Text("Guardar")
            }

            OutlinedButton(onClick = onCancel) {
                Text("Cancelar")
            }
        }
    }
}


