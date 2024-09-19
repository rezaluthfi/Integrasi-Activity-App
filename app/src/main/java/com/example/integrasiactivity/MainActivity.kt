package com.example.integrasiactivity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.integrasiactivity.databinding.ActivityMainBinding
import com.example.integrasiactivity.databinding.SpinnerItemBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            // Setup Spinner untuk Gender
            val genderList = listOf("Laki-laki", "Perempuan")

            // Membuat adapter dengan layout custom spinner_item.xml
            val adapter = object : ArrayAdapter<String>(this@MainActivity, R.layout.spinner_item, genderList) {
                override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                    val bindingItem = convertView?.let {
                        SpinnerItemBinding.bind(it)
                    } ?: SpinnerItemBinding.inflate(layoutInflater, parent, false)

                    // Atur teks dan ikon
                    bindingItem.text.text = genderList[position]
                    bindingItem.icon.setImageResource(
                        if (genderList[position] == "Laki-laki") R.drawable.baseline_boy_24 else R.drawable.baseline_girl_24
                    )

                    return bindingItem.root
                }

                override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                    return getView(position, convertView, parent)
                }
            }

            // Set adapter ke Spinner
            spinnerGender.adapter = adapter

            // Setup DatePicker untuk Tanggal Lahir
            etDob.setOnClickListener {
                showDatePicker()
            }

            // Ketika tombol daftar diklik
            btnRegister.setOnClickListener {
                val fields = listOf(
                    etFullname.text,
                    etEmail.text,
                    etPhone.text,
                    etDob.text,
                    etPassword.text
                )

                // Cek apakah semua field sudah diisi
                if (fields.any { it.isEmpty() } || spinnerGender.selectedItem.toString().isEmpty()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Mohon isi semua data terlebih dahulu",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val intent = Intent(this@MainActivity, LoginActivity::class.java).apply {
                        putExtra("fullname", etFullname.text.toString())
                        putExtra("email", etEmail.text.toString())
                        putExtra("phone", etPhone.text.toString())
                        putExtra("gender", spinnerGender.selectedItem.toString())
                        putExtra("birthday", etDob.text.toString())
                    }
                    startActivity(intent)
                    Toast.makeText(
                        this@MainActivity,
                        "Berhasil melakukan register",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    // Menampilkan DatePicker untuk memilih tanggal lahir
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.etDob.setText(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }
}
