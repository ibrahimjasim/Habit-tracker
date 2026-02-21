package com.example.habit_trawcker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn = findViewById<MaterialButton>(R.id.btnLogin)
        
        loginBtn.setOnClickListener {
            // Gå till vanelistan
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Stäng Login-skärmen
            finish()
        }
    }
}
