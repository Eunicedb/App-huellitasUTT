package com.example.project180.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project180.Adapter.TicketAdapter // Asegúrate de importar el nuevo adaptador
import com.example.project180.databinding.ActivityTicketBinding
import com.example.project180.Helper.ManagmentCart

class TicketActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTicketBinding
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        // Obtener los datos pasados desde CartActivity
        val programText = intent.getStringExtra("programText")

        // Mostrar el texto del programa educativo
        binding.programTextView.text = programText

        // Configurar el RecyclerView para mostrar los items del carrito
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this)

        // Usar el nuevo metodo para obtener solo los items para el ticket
        val cartItems = managmentCart.getCartItemsForTicket()

        binding.cartRecyclerView.adapter = TicketAdapter(cartItems)

        // Listener para el botón "Modificar pedido"
        binding.modifyOrderBtn.setOnClickListener {
            // Redirigir al usuario de vuelta al carrito
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        // Listener para el botón "Cancelar"
        binding.cancelOrderBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
