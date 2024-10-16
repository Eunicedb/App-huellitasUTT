package com.example.project180.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project180.R
import com.example.project180.Model.Product

class ProductAdapter(
    private val productList: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    // ViewHolder para tu RecyclerView
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.productName)
        val productDescription: TextView = itemView.findViewById(R.id.productDescription)
        val programName: TextView = itemView.findViewById(R.id.programName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.productName.text = product.name
        holder.productDescription.text = product.description
        holder.programName.text = product.program
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}