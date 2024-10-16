package com.example.project180.Activity

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project180.R
import com.example.project180.Adapter.ProductAdapter
import com.example.project180.Model.Product

class CrudActivity : AppCompatActivity() {

    // Definir variables para los elementos de la UI
    private lateinit var editTextProductName: EditText
    private lateinit var editTextProductDescription: EditText
    private lateinit var editTextProgramName: EditText
    private lateinit var recyclerViewProducts: RecyclerView

    private lateinit var buttonAddProduct: ImageView
    private lateinit var buttonUpdateProduct: ImageView
    private lateinit var buttonDeleteProduct: ImageView

    private lateinit var productAdapter: ProductAdapter
    private val productList = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crudadmin)

        // Vincular elementos del layout con el código
        editTextProductName = findViewById(R.id.editTextProductName)
        editTextProductDescription = findViewById(R.id.editTextProductDescription)
        editTextProgramName = findViewById(R.id.editTextProgramName)
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts)

        buttonAddProduct = findViewById(R.id.buttonAddProduct)
        buttonUpdateProduct = findViewById(R.id.buttonUpdateProduct)
        buttonDeleteProduct = findViewById(R.id.buttonDeleteProduct)

        // Configurar RecyclerView
        recyclerViewProducts.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(productList)
        recyclerViewProducts.adapter = productAdapter

        // Listener para agregar productos
        buttonAddProduct.setOnClickListener {
            addProduct()
        }

        // Listener para actualizar productos
        buttonUpdateProduct.setOnClickListener {
            updateProduct()
        }

        // Listener para eliminar productos
        buttonDeleteProduct.setOnClickListener {
            deleteProduct()
        }
    }

    // Función para agregar un producto
    private fun addProduct() {
        val name = editTextProductName.text.toString()
        val description = editTextProductDescription.text.toString()
        val program = editTextProgramName.text.toString()

        if (name.isNotEmpty() && description.isNotEmpty() && program.isNotEmpty()) {
            val product = Product(name, description, program)
            productList.add(product)
            productAdapter.notifyDataSetChanged()
            clearFields()
            Toast.makeText(this, "Producto agregado", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
        }
    }

    // Función para actualizar un producto
    private fun updateProduct() {
        // Aquí puedes agregar la lógica para actualizar un producto
        Toast.makeText(this, "Funcionalidad para actualizar", Toast.LENGTH_SHORT).show()
    }

    // Función para eliminar un producto
    private fun deleteProduct() {
        // Aquí puedes agregar la lógica para eliminar un producto
        Toast.makeText(this, "Funcionalidad para eliminar", Toast.LENGTH_SHORT).show()
    }

    // Limpiar los campos de texto después de agregar/actualizar un producto
    private fun clearFields() {
        editTextProductName.text.clear()
        editTextProductDescription.text.clear()
        editTextProgramName.text.clear()
    }
}
