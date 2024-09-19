package com.example.integrasiactivity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.integrasiactivity.databinding.ActivityProfileBinding
import com.example.integrasiactivity.databinding.ViewDialogLogOutBinding

class ProfileActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProfileBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        with(binding) {
            // Ambil data dari LoginActivity
            val username = intent.getStringExtra("fullname")
            val fullname = intent.getStringExtra("fullname")
            val email = intent.getStringExtra("email")
            val phone = intent.getStringExtra("phone")
            val gender = intent.getStringExtra("gender")
            val birthday = intent.getStringExtra("birthday")

            // Tampilkan data di TextView
            tvUsername.text = username
            tvFullname.text = fullname
            tvEmail.text = email
            tvPhone.text = phone
            tvGender.text = gender
            tvBirthday.text = birthday

            // Sign Out back to LoginActivity
            llSignOut.setOnClickListener {
                // Membuat dialog menggunakan ViewBinding
                val dialogBinding = ViewDialogLogOutBinding.inflate(layoutInflater)
                val dialog = Dialog(this@ProfileActivity)

                // Set content view dari dialog dengan menggunakan binding
                dialog.setContentView(dialogBinding.root)

                // Menangani klik pada btnOk (keluar dari ProfileActivity)
                dialogBinding.btnOk.setOnClickListener {
                    // Intent untuk kembali ke LoginActivity
                    val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()  // Menutup ProfileActivity
                }

                // Menangani klik pada btnCancel (tutup dialog)
                dialogBinding.btnCancel.setOnClickListener {
                    dialog.dismiss()  // Menutup dialog
                }

                // Menampilkan dialog
                dialog.show()

                // Mengatur ukuran dialog agar sesuai dengan layar atau ukuran tertentu
                dialog.window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,  // Lebar dialog: sesuai layar
                    ViewGroup.LayoutParams.WRAP_CONTENT   // Tinggi dialog: mengikuti konten
                    //make dialog rounded

                )
            }

        }

    }
}