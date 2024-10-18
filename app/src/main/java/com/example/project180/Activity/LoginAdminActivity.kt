package com.example.project180.Activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project180.R
import com.google.firebase.auth.FirebaseAuth

class LoginAdminActivity : AppCompatActivity() {

    private lateinit var adminUsernameEditText: EditText
    private lateinit var adminPasswordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var forgotPasswordButton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginadmin)

        auth = FirebaseAuth.getInstance()

        adminUsernameEditText = findViewById(R.id.adminCorreoEditText)
        adminPasswordEditText = findViewById(R.id.adminPasswordEditText)
        loginButton = findViewById(R.id.loginButton)
        forgotPasswordButton = findViewById(R.id.forgotPasswordButton)

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

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Por favor ingresa un correo", Toast.LENGTH_SHORT).show()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(username).matches()) {
            Toast.makeText(this, "Por favor ingresa un correo válido", Toast.LENGTH_SHORT).show()
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

        // Lógica de inicio de sesión con Firebase
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión exitoso, redirigir a la pantalla del administrador
                    val intent = Intent(this, CrudActivity::class.java)
                    startActivity(intent)
                    finish() // Termina la actividad de inicio de sesión para que no regrese aquí con el botón atrás
                } else {
                    // Si el inicio de sesión falla, muestra un mensaje de error
                    Toast.makeText(this, "Error de autenticación", Toast.LENGTH_SHORT).show()
                }
            }
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
