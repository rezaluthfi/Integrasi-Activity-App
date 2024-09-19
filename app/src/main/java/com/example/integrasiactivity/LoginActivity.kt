package com.example.integrasiactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.integrasiactivity.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        with(binding) {
            btnLogin.setOnClickListener {
                // Cek apakah username dan password sudah diisi
                if (etEmail.text.toString().isEmpty() || etPassword.text.toString().isEmpty()) {
                    Toast.makeText(
                        this@LoginActivity,
                        "Mohon isi email dan password",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // Ambil data dari MainActivity
                    val fullname = intent.getStringExtra("fullname")
                    val email = intent.getStringExtra("email")
                    val phone = intent.getStringExtra("phone")
                    val gender = intent.getStringExtra("gender")
                    val birthday = intent.getStringExtra("birthday")

                    // Redirect ke ProfileActivity dengan membawa data
                    val intent = Intent(this@LoginActivity, ProfileActivity::class.java).apply {
                        putExtra("fullname", fullname)
                        putExtra("email", email)
                        putExtra("phone", phone)
                        putExtra("gender", gender)
                        putExtra("birthday", birthday)
                    }
                    startActivity(intent)
                    Toast.makeText(
                        this@LoginActivity,
                        "Selamat datang $fullname",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Clear username dan password
                    etEmail.text.clear()
                    etPassword.text.clear()
                }
            }
        }

    }
}