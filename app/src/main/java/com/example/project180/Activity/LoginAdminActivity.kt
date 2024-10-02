package com.example.project180.Activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project180.R // Asegúrate de que este import apunte a tu archivo de recursos

class LoginAdminActivity : AppCompatActivity() {

    private lateinit var adminUsernameEditText: EditText
    private lateinit var adminPasswordEditText: EditText
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginadmin) // Asegúrate de que el nombre del layout sea correcto

        adminUsernameEditText = findViewById(R.id.adminUsernameEditText)
        adminPasswordEditText = findViewById(R.id.adminPasswordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            validateAndLogin()
        }
    }

    private fun validateAndLogin() {
        val username = adminUsernameEditText.text.toString().trim()
        val password = adminPasswordEditText.text.toString().trim()

        // Validación de campos
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Por favor ingresa un usuario", Toast.LENGTH_SHORT).show()
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

        // Si las validaciones son exitosas, redirigir a MainAdminActivity
        val intent = Intent(this, LoginAdminActivity::class.java)
        startActivity(intent)
        finish() // Opcional: finaliza esta actividad si no quieres que el usuario vuelva a ella al presionar atrás
    }
}
