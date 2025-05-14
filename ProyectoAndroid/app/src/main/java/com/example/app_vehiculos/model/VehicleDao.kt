package com.example.app_vehiculos.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao{

    @Query("SELECT * FROM vehicles")
    fun getAllVehicles(): Flow<List<Vehicle>>

    @Insert
    suspend fun insert(vehicle: Vehicle)

    @Update
    suspend fun update(vehicle: Vehicle)

    @Delete
    suspend fun delete(vehicle: Vehicle)

}