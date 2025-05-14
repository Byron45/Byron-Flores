package com.example.app_vehiculos.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.app_vehiculos.R
import com.example.app_vehiculos.controller.AuthController
import com.example.app_vehiculos.model.AppDataBase
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var authController : AuthController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val nameEditText = findViewById<EditText>(R.id.etName)
        val lastNameEditText = findViewById<EditText>(R.id.etLastName)
        val loginButton = findViewById<Button>(R.id.btnLogin)
        val registerTextView = findViewById<TextView>(R.id.tvRegister)

        val userDao = AppDataBase.getDatabase(this).userDao()
        authController = AuthController(userDao)

        loginButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()

            if (name.isEmpty() || lastName.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese su nombre y apellido", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val success = authController.login(name, lastName)
                if (success) {
                    Toast.makeText(this@LoginActivity, "Bienvenido", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this@LoginActivity, "Usuario no encontrado", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        registerTextView.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}