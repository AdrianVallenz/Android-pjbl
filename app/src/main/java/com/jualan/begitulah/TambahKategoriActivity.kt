package com.example.dashboard

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class TambahKategoriActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var edtNamaKategori: EditText
    private lateinit var btnSimpanKategori: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_kategori)

        db = FirebaseFirestore.getInstance()

        edtNamaKategori = findViewById(R.id.edtNamaKategori)
        btnSimpanKategori = findViewById(R.id.btnSimpanKategori)

        btnSimpanKategori.setOnClickListener {
            val namaKategori = edtNamaKategori.text.toString().trim()

            if (namaKategori.isEmpty()) {
                Toast.makeText(this, "Nama kategori wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val kategori = hashMapOf(
                "nama" to namaKategori,
                "createdAt" to System.currentTimeMillis()
            )

            db.collection("categories")
                .add(kategori)
                .addOnSuccessListener {
                    Toast.makeText(this, "Kategori berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    edtNamaKategori.text.clear()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Gagal menambahkan kategori", Toast.LENGTH_SHORT).show()
                }
        }
    }
}