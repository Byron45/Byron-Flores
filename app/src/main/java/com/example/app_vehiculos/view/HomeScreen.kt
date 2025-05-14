package com.example.app_vehiculos.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import com.example.app_vehiculos.model.Vehiculo

@Composable
fun HomeScreen(
    vehiculos: List<Vehiculo>,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Inicio", style = MaterialTheme.typography.headlineMedium)
            Button(onClick = onLogout) {
                Text("Cerrar sesión")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(vehiculos) { vehiculo ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = vehiculo.imagenResId),
                        contentDescription = "Imagen de ${vehiculo.marca}",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Placa: ${vehiculo.placa}")
                        Text("Marca: ${vehiculo.marca}")
                        Text("Año: ${vehiculo.anio}")
                        Text("Color: ${vehiculo.color}")
                        Text("Costo/día: $${vehiculo.costoPorDia}")
                        Text("Activo: ${if (vehiculo.activo) "Sí" else "No"}")
                    }
                }
            }
        }
    }
}


