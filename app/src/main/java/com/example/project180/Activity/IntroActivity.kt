package com.example.project180.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project180.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Cuando se haga clic en el botón startBtn, redirige a la pantalla de catálogo
        binding.startBtn.setOnClickListener {
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
        }

        // Cuando se haga clic en el texto "Iniciar Sesión" (textView3), redirige al LoginAdminActivity
        binding.textView3.setOnClickListener {
            startActivity(Intent(this@IntroActivity, LoginAdminActivity::class.java))
        }
    }
}
