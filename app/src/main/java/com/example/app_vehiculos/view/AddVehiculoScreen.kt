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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddVehiculoScreen(
    onSave: (Vehiculo) -> Unit,
    onCancel: () -> Unit,
    vehiculoAEditar: Vehiculo? = null
) {
    val imagenes = mapOf(
        "toyota" to R.drawable.toyota,
        "chevrolet" to R.drawable.chevrolet,
        "nissan" to R.drawable.nissan
    )

    var placa by remember { mutableStateOf(vehiculoAEditar?.placa ?: "") }
    var marca by remember { mutableStateOf(vehiculoAEditar?.marca ?: "") }
    var anio by remember { mutableStateOf(vehiculoAEditar?.anio?.toString() ?: "") }
    var color by remember { mutableStateOf(vehiculoAEditar?.color ?: "") }
    var costoPorDia by remember { mutableStateOf(vehiculoAEditar?.costoPorDia?.toString() ?: "") }
    var activo by remember { mutableStateOf(vehiculoAEditar?.activo ?: true) }

    var imagenSeleccionada by remember {
        mutableStateOf(
            when (vehiculoAEditar?.imagenResId) {
                R.drawable.chevrolet -> "chevrolet"
                R.drawable.nissan -> "nissan"
                else -> "toyota"
            }
        )
    }

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            if (vehiculoAEditar == null) "Nuevo Vehículo" else "Editar Vehículo",
            style = MaterialTheme.typography.headlineSmall
        )

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
                        text = { Text(nombre.replaceFirstChar { it.uppercase() }) },
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
