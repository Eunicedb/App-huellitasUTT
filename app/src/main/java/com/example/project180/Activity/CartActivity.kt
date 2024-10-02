package com.example.project180.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project180.Helper.ManagmentCart
import com.example.project180.Adapter.CartAdapter
import com.example.project180.Helper.ChangeNumberItemsListener
import com.example.project180.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var programText: String? = null // Guardar el programa educativo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)

        setVariable()
        initCartList()
        calculateCart()

        // Listener para el botón "Confirmar" (applyCopoun)
        binding.applyCopoun.setOnClickListener {
            // Capturamos el texto ingresado en el campo "copounEdt"
            programText = binding.copounEdt.text.toString()

            // Validamos que no esté vacío
            if (programText.isNullOrEmpty()) {
                binding.copounEdt.error = "Por favor, introduzca su programa educativo"
            } else {
                // Solo guardamos el programa, sin pasar a la actividad del ticket todavía
                binding.copounEdt.isEnabled = false // Bloquear edición tras confirmar
                binding.applyCopoun.isEnabled = false // Desactivar botón de confirmar
            }
        }

        // Listener para el botón "Confirmar pago"
        binding.checkOutBtn.setOnClickListener {
            // Validamos que se haya confirmado el programa educativo
            if (programText.isNullOrEmpty()) {
                binding.copounEdt.error = "Primero confirme su programa educativo"
            } else {
                // Iniciamos la actividad TicketActivity y pasamos el programa y los artículos del carrito
                val intent = Intent(this, TicketActivity::class.java)
                intent.putExtra("programText", programText)
                intent.putParcelableArrayListExtra("cartItems", ArrayList(managmentCart.getListCart()))
                startActivity(intent)
            }
        }
    }

    // Calcula el subtotal y el total
    private fun calculateCart() {
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100.0
        val total = itemTotal

        with(binding) {
            totalFeeTxt.text = "$$itemTotal"
            totalTxt.text = "$$total"
        }
    }

    private fun initCartList() {
        binding.cartView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.cartView.adapter =
            CartAdapter(managmentCart.getListCart(), this, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }
            })
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
