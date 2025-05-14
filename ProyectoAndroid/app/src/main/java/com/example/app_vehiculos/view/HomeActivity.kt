package com.example.app_vehiculos.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app_vehiculos.R
import com.example.app_vehiculos.controller.VehicleController
import com.example.app_vehiculos.model.AppDataBase
import com.example.app_vehiculos.model.Vehicle
import com.example.app_vehiculos.view.adapters.VehicleAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var vehicleController: VehicleController
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VehicleAdapter
    private lateinit var logoutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.rvVehicles)
        logoutButton = findViewById(R.id.btnLogout)

        val dao = AppDataBase.getDatabase(this).vehicleDao()
        vehicleController = VehicleController(dao)

        adapter = VehicleAdapter(
            onEdit = { vehicle -> navigateToEdit(vehicle) },
            onDelete = { vehicle -> deleteVehicle(vehicle) }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        observeVehicles()

        logoutButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }

    private fun observeVehicles() {
        lifecycleScope.launch {
            vehicleController.getAllVehicles().collectLatest { vehicles ->
                adapter.submitList(vehicles)
            }
        }
    }

    private fun navigateToEdit(vehicle: Vehicle) {
        val intent = Intent(this, VehicleFormActivity::class.java)
        intent.putExtra("vehiculo", vehicle)
        startActivity(intent)
    }

    private fun deleteVehicle(vehicle: Vehicle) {
        lifecycleScope.launch {
            vehicleController.deleteVehicle(vehicle)
            Toast.makeText(this@HomeActivity, "Vehículo eliminado", Toast.LENGTH_SHORT).show()
        }
    }
}