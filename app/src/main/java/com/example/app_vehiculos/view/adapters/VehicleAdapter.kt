package com.example.app_vehiculos.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.app_vehiculos.R
import com.example.app_vehiculos.model.Vehicle

class VehicleAdapter(
    val onEdit: (Vehicle) -> Unit,
    val onDelete: (Vehicle) -> Unit
) : ListAdapter<Vehicle, VehicleAdapter.VehicleViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vehicle, parent, false)
        return VehicleViewHolder(view, onEdit, onDelete)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class VehicleViewHolder(
        view: View,
        private val onEdit: (Vehicle) -> Unit,
        private val onDelete: (Vehicle) -> Unit
    ) : RecyclerView.ViewHolder(view) {

        private val placaText: TextView = view.findViewById(R.id.tvPlaca)
        private val marcaText: TextView = view.findViewById(R.id.tvMarca)
        private val anioText: TextView = view.findViewById(R.id.tvAnio)
        private val colorText: TextView = view.findViewById(R.id.tvColor)
        private val costoText: TextView = view.findViewById(R.id.tvCosto)
        private val activoText: TextView = view.findViewById(R.id.tvActivo)
        private val imageView: ImageView = view.findViewById(R.id.ivVehicle)

        private val btnEditar: Button = view.findViewById(R.id.btnEditar)
        private val btnEliminar: Button = view.findViewById(R.id.btnEliminar)

        fun bind(vehicle: Vehicle) {
            placaText.text = "Placa: ${vehicle.placa}"
            marcaText.text = "Marca: ${vehicle.marca}"
            anioText.text = "Año: ${vehicle.anio}"
            colorText.text = "Color: ${vehicle.color}"
            costoText.text = "Costo por día: $${vehicle.costoPorDia}"
            activoText.text = if (vehicle.activo) "Activo" else "Inactivo"
            imageView.setImageResource(R.drawable.hyundai)

            btnEditar.setOnClickListener { onEdit(vehicle) }
            btnEliminar.setOnClickListener { onDelete(vehicle) }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Vehicle>() {
        override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle) = oldItem == newItem
    }
}
