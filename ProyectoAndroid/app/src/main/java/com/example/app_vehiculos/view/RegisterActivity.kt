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

class RegisterActivity : AppCompatActivity() {

    private lateinit var authController: AuthController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val nameEditText = findViewById<EditText>(R.id.etName)
        val lastNameEditText = findViewById<EditText>(R.id.etLastName)
        val registerButton = findViewById<Button>(R.id.btnRegister)
        val backLogin = findViewById<TextView>(R.id.tvBackToLogin)

        val userDao = AppDataBase.getDatabase(this).userDao()
        authController = AuthController(userDao)

        registerButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()

            if (name.isEmpty() || lastName.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese su nombre y apellido", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val success = authController.register(name, lastName)
                if (success) {
                    Toast.makeText(this@RegisterActivity, "Usuario registrado con éxito", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    finish()
                }else{
                    Toast.makeText(this@RegisterActivity, "El usuario ya existe", Toast.LENGTH_SHORT)
                        .show()

                }

            }

        }

        backLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}