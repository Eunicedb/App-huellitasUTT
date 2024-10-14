package com.example.project180.Activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project180.R // Asegúrate de que este import apunte a tu archivo de recursos

class LoginAdminActivity : AppCompatActivity() {

    private lateinit var adminCorreoEditText: EditText
    private lateinit var adminPasswordEditText: EditText
    private lateinit var loginButton: Button

    private var failedLoginAttempts = 0
    private val maxFailedAttempts = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginadmin) // Asegúrate de que el nombre del layout sea correcto

        adminCorreoEditText = findViewById(R.id.adminCorreoEditText)
        adminPasswordEditText = findViewById(R.id.adminPasswordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            validateAndLogin()
        }
    }

    private fun validateAndLogin() {
        val username = adminCorreoEditText.text.toString().trim()
        val password = adminPasswordEditText.text.toString().trim()

        // Validación de correo electrónico
        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            Toast.makeText(this, "Por favor ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show()
            return
        }

        // Validación de campos vacíos
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Por favor ingresa un correo", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor ingresa una contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        // Validación de longitud mínima de la contraseña
        if (password.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        // Límite de intentos fallidos para evitar ataques de fuerza bruta
        if (failedLoginAttempts >= maxFailedAttempts) {
            Toast.makeText(this, "Demasiados intentos fallidos. Inténtalo más tarde.", Toast.LENGTH_SHORT).show()
            return
        }

        // Simulación de un login exitoso
        if (username == "admin@example.com" && password == "password123") {
            // Redirigir a MainAdminActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finaliza esta actividad
        } else {
            failedLoginAttempts++
            Toast.makeText(this, "Credenciales incorrectas. Intentos restantes: ${maxFailedAttempts - failedLoginAttempts}", Toast.LENGTH_SHORT).show()
        }
    }
}
