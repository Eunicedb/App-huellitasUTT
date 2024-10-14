package com.example.project180.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project180.Model.Product
import com.example.project180.Adapter.ProductAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CrudActivity : AppCompatActivity() {

    private lateinit var editTextProductName: EditText
    private lateinit var editTextProductDescription: EditText
    private lateinit var editTextProgramName: EditText
    private lateinit var buttonAddProduct: Button
    private lateinit var buttonUpdateProduct: Button
    private lateinit var buttonDeleteProduct: Button
    private lateinit var recyclerViewProducts: RecyclerView

    private lateinit var databaseReference: DatabaseReference
    private lateinit var productAdapter: ProductAdapter
    private var productList: MutableList<Product> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crud)

        // Inicialización de vistas
        editTextProductName = findViewById(R.id.editTextProductName)
        editTextProductDescription = findViewById(R.id.editTextProductDescription)
        editTextProgramName = findViewById(R.id.editTextProgramName)
        buttonAddProduct = findViewById(R.id.buttonAddProduct)
        buttonUpdateProduct = findViewById(R.id.buttonUpdateProduct)
        buttonDeleteProduct = findViewById(R.id.buttonDeleteProduct)
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts)

        // Inicialización de Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("Products")

        // Configuración de RecyclerView
        recyclerViewProducts.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(productList)
        recyclerViewProducts.adapter = productAdapter

        // Botón para agregar producto
        buttonAddProduct.setOnClickListener {
            addProduct()
        }

        // Aquí puedes implementar los listeners para actualizar y eliminar productos
    }

    private fun addProduct() {
        val name = editTextProductName.text.toString().trim()
        val description = editTextProductDescription.text.toString().trim()
        val program = editTextProgramName.text.toString().trim()

        if (name.isEmpty() || description.isEmpty() || program.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Crear un nuevo producto
        val productId = databaseReference.push().key
        val product = Product(productId, name, description, program)

        // Agregar el producto a Firebase
        if (productId != null) {
            databaseReference.child(productId).setValue(product).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show()
                    // Limpiar los campos
                    editTextProductName.text.clear()
                    editTextProductDescription.text.clear()
                    editTextProgramName.text.clear()
                } else {
                    Toast.makeText(this, "Error al agregar producto", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Aquí puedes implementar los métodos para actualizar y eliminar productos
}
