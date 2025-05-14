package com.example.app_vehiculos.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "vehicles")
data class Vehicle (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val placa: String,
    val marca: String,
    val anio: Int,
    val color: String,
    val costoPorDia: Double,
    val activo: Boolean
) : Serializable