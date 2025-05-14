package com.example.app_vehiculos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.app_vehiculos.model.Usuario
import com.example.app_vehiculos.ui.theme.App_VehiculosTheme
import com.example.app_vehiculos.view.LoginScreen

class MainActivity : ComponentActivity() {
    private val usuariosRegistrados = listOf(
        Usuario("Byron", "Flores"),
        Usuario("Jordi", "Pila"),
        Usuario("Veyker", "Barrionuevo")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_VehiculosTheme {
                LoginScreen(
                    usuariosRegistrados = usuariosRegistrados,
                    onLoginSuccess = {
                        // Aquí luego navegaremos a la pantalla de inicio
                    }
                )
            }
        }
    }
}
