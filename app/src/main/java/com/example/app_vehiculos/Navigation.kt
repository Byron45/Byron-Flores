package com.example.app_vehiculos.navigation

import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.app_vehiculos.model.Usuario
import com.example.app_vehiculos.model.Vehiculo
import com.example.app_vehiculos.R
import com.example.app_vehiculos.view.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val usuariosRegistrados = remember {
        mutableStateListOf(
            Usuario("Byron", "Flores"),
            Usuario("Jordi", "Pila"),
            Usuario("Veyker", "Barrionuevo")
        )
    }

    val vehiculos = remember {
        mutableStateListOf(
            Vehiculo("ABC123", "Toyota", 2020, "Rojo", 50.0, true, R.drawable.toyota),
            Vehiculo("XYZ789", "Chevrolet", 2019, "Azul", 45.0, false, R.drawable.chevrolet),
            Vehiculo("DEF456", "Nissan", 2022, "Blanco", 60.0, true, R.drawable.nissan)
        )
    }

    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(
                usuariosRegistrados = usuariosRegistrados,
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onGoToRegister = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                usuarios = usuariosRegistrados,
                onRegisterSuccess = {
                    usuariosRegistrados.add(it)
                    navController.popBackStack() // Vuelve al login
                }
            )
        }

        composable("home") {
            HomeScreen(
                vehiculos = vehiculos,
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                onAddVehiculo = {
                    navController.navigate("addVehiculo")
                },
                onDeleteVehiculo = { vehiculo ->
                    vehiculos.remove(vehiculo)
                },
                onEditVehiculo = { vehiculo ->
                }
            )
        }

        composable("addVehiculo") {
            AddVehiculoScreen(
                onSave = {
                    vehiculos.add(it)
                    navController.popBackStack()
                },
                onCancel = {
                    navController.popBackStack()
                }
            )
        }
    }
}


