package com.example.habit_trawcker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.habit_trawcker.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val authRepo = AuthRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.LoginButton.setOnClickListener {
            val email = binding.EmailText.text.toString()
            val password = binding.TextPassword.text.toString()
            login(email, password)
        }

        binding.SignUpPart.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, RegisterFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    fun login(email: String, password: String){
        lifecycleScope.launch {
            val result = authRepo.login(email, password)
            result.onSuccess { user ->
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }.onFailure { error ->
                Toast.makeText(this@LoginActivity, error.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
