package com.example.app_vehiculos.view

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.app_vehiculos.R
import com.example.app_vehiculos.controller.VehicleController
import com.example.app_vehiculos.model.AppDataBase
import com.example.app_vehiculos.model.Vehicle
import kotlinx.coroutines.launch

class VehicleFormActivity : AppCompatActivity() {

    private lateinit var controller: VehicleController
    private var vehicleToEdit: Vehicle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_form)

        val placaEdit = findViewById<EditText>(R.id.etPlaca)
        val marcaEdit = findViewById<EditText>(R.id.etMarca)
        val anioEdit = findViewById<EditText>(R.id.etAnio)
        val colorEdit = findViewById<EditText>(R.id.etColor)
        val costoEdit = findViewById<EditText>(R.id.etCosto)
        val activoCheck = findViewById<CheckBox>(R.id.cbActivo)
        val btnGuardar = findViewById<Button>(R.id.btnGuardar)

        val dao = AppDataBase.getDatabase(this).vehicleDao()
        controller = VehicleController(dao)

        vehicleToEdit = intent.getSerializableExtra("vehiculo") as? Vehicle

        vehicleToEdit?.let {
            title = "Editar Vehículo"
            placaEdit.setText(it.placa)
            marcaEdit.setText(it.marca)
            anioEdit.setText(it.anio.toString())
            colorEdit.setText(it.color)
            costoEdit.setText(it.costoPorDia.toString())
            activoCheck.isChecked = it.activo
        } ?: run {
            title = "Nuevo Vehículo"
        }

        btnGuardar.setOnClickListener {
            val placa = placaEdit.text.toString()
            val marca = marcaEdit.text.toString()
            val anio = anioEdit.text.toString().toIntOrNull() ?: 0
            val color = colorEdit.text.toString()
            val costo = costoEdit.text.toString().toDoubleOrNull() ?: 0.0
            val activo = activoCheck.isChecked

            if (placa.isBlank() || marca.isBlank() || anio == 0 || color.isBlank() || costo == 0.0) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val vehiculo = Vehicle(
                id = vehicleToEdit?.id ?: 0,
                placa = placa,
                marca = marca,
                anio = anio,
                color = color,
                costoPorDia = costo,
                activo = activo
            )

            lifecycleScope.launch {
                if (vehicleToEdit == null) {
                    controller.insertVehicle(vehiculo)
                    Toast.makeText(
                        this@VehicleFormActivity,
                        "Vehículo insertado",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    controller.updateVehicle(vehiculo)
                    Toast.makeText(
                        this@VehicleFormActivity,
                        "Vehículo actualizado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                finish()
            }
        }
    }
}