package com.example.project180.Activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project180.R // Asegúrate de que este import apunte a tu archivo de recursos
import com.google.firebase.auth.FirebaseAuth



class LoginAdminActivity : AppCompatActivity() {

    private lateinit var adminUsernameEditText: EditText
    private lateinit var adminPasswordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var forgotPasswordButton: Button // Nuevo botón
    private lateinit var auth: FirebaseAuth // Inicializa FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginadmin)

        // Inicializa FirebaseAuth
        auth = FirebaseAuth.getInstance()

        adminUsernameEditText = findViewById(R.id.adminCorreoEditText) // Asegúrate de que este ID sea correcto
        adminPasswordEditText = findViewById(R.id.adminPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton) // Inicializa el botón de olvido de contraseña

        loginButton.setOnClickListener {
            validateAndLogin()
        }

        forgotPasswordButton.setOnClickListener {
            resetPassword()
        }
    }

    private fun validateAndLogin() {
        val username = adminUsernameEditText.text.toString().trim()
        val password = adminPasswordEditText.text.toString().trim()

        // Validación de campos
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Por favor ingresa un correo", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor ingresa una contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
            return
        }

        // Aquí debes agregar la lógica para autenticar al usuario con Firebase
    }

    private fun resetPassword() {
        val email = adminUsernameEditText.text.toString().trim()

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Por favor ingresa tu correo electrónico", Toast.LENGTH_SHORT).show()
            return
        }

        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Correo de restablecimiento enviado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error al enviar el correo", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
