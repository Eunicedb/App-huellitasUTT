package com.example.project180.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project180.Model.Product
import com.example.project180.R

class ProductAdapter(
    private val productList: MutableList<Product>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewProductName: TextView = itemView.findViewById(R.id.textViewProductName)
        val textViewProductDescription: TextView = itemView.findViewById(R.id.textViewProductDescription)
        val textViewProgramName: TextView = itemView.findViewById(R.id.textViewProgramName)
        val buttonEdit: Button = itemView.findViewById(R.id.buttonEdit)
        val buttonDelete: Button = itemView.findViewById(R.id.buttonDelete)

        init {
            buttonEdit.setOnClickListener {
                onItemClickListener.onEditClick(adapterPosition)
            }
            buttonDelete.setOnClickListener {
                onItemClickListener.onDeleteClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.textViewProductName.text = product.name
        holder.textViewProductDescription.text = product.description
        holder.textViewProgramName.text = product.program
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    interface OnItemClickListener {
        fun onEditClick(position: Int)
        fun onDeleteClick(position: Int)
    }
}
