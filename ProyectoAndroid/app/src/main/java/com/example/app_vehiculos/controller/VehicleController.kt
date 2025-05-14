package com.example.app_vehiculos.controller

import com.example.app_vehiculos.model.Vehicle
import com.example.app_vehiculos.model.VehicleDao
import kotlinx.coroutines.flow.Flow

class VehicleController(private val vehicleDao: VehicleDao) {

    fun getAllVehicles(): Flow<List<Vehicle>> {
        return vehicleDao.getAllVehicles()
    }

    suspend fun insertVehicle(vehicle: Vehicle) {
        vehicleDao.insert(vehicle)
    }

    suspend fun updateVehicle(vehicle: Vehicle) {
        vehicleDao.update(vehicle)
    }

    suspend fun deleteVehicle(vehicle: Vehicle) {
        vehicleDao.delete(vehicle)
    }
}