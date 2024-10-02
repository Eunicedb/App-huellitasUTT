package com.example.project180.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project180.Model.ItemsModel
import com.example.project180.R

class TicketAdapter(private val cartItems: List<ItemsModel>) : RecyclerView.Adapter<TicketAdapter.TicketViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        // Inflar un nuevo diseño que solo muestra el ticket (sin botones de modificación)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_ticket , parent, false)
        return TicketViewHolder(view)
    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val item = cartItems[position]

        // Configurar el nombre y el precio del producto
        holder.itemName.text = item.title
        holder.itemPrice.text = "$${item.price}"
        holder.itemQuantity.text = "Cantidad: ${item.quantity}"

        // No se muestran botones ni lógica de incremento o decremento, ya que es solo para mostrar
        // la lista de productos con su cantidad y precio fijo
    }

    override fun getItemCount(): Int = cartItems.size

    class TicketViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.cartRecyclerView)
        val itemPrice: TextView = itemView.findViewById(R.id.priceTxt)
        val itemQuantity: TextView = itemView.findViewById(R.id.totalTxt)
    }
}
