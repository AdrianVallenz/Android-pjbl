package com.example.dashboard

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class TambahProductActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var edtNamaProduct: EditText
    private lateinit var edtHargaProduct: EditText
    private lateinit var edtStokProduct: EditText
    private lateinit var spinnerKategori: Spinner
    private lateinit var btnSimpanProduct: Button

    private val kategoriNames = mutableListOf<String>()
    private val kategoriIds = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_product)

        db = FirebaseFirestore.getInstance()

        edtNamaProduct = findViewById(R.id.edtNamaProduct)
        edtHargaProduct = findViewById(R.id.edtHargaProduct)
        edtStokProduct = findViewById(R.id.edtStokProduct)
        spinnerKategori = findViewById(R.id.spinnerKategori)
        btnSimpanProduct = findViewById(R.id.btnSimpanProduct)

        loadKategori()

        btnSimpanProduct.setOnClickListener {
            simpanProduct()
        }
    }

    private fun loadKategori() {
        db.collection("categories")
            .get()
            .addOnSuccessListener { result ->
                kategoriNames.clear()
                kategoriIds.clear()

                for (doc in result) {
                    kategoriIds.add(doc.id)
                    kategoriNames.add(doc.getString("nama") ?: "-")
                }

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    kategoriNames
                )

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerKategori.adapter = adapter
            }
    }

    private fun simpanProduct() {
        val nama = edtNamaProduct.text.toString().trim()
        val harga = edtHargaProduct.text.toString().trim()
        val stok = edtStokProduct.text.toString().trim()

        if (nama.isEmpty() || harga.isEmpty() || stok.isEmpty()) {
            Toast.makeText(this, "Semua data wajib diisi", Toast.LENGTH_SHORT).show()
            return
        }

        if (kategoriNames.isEmpty()) {
            Toast.makeText(this, "Kategori belum tersedia", Toast.LENGTH_SHORT).show()
            return
        }

        val indexKategori = spinnerKategori.selectedItemPosition

        val product = hashMapOf(
            "nama" to nama,
            "harga" to harga.toLong(),
            "stok" to stok.toLong(),
            "kategoriId" to kategoriIds[indexKategori],
            "kategoriNama" to kategoriNames[indexKategori],
            "createdAt" to System.currentTimeMillis()
        )

        db.collection("products")
            .add(product)
            .addOnSuccessListener {
                Toast.makeText(this, "Product berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Gagal menambahkan product", Toast.LENGTH_SHORT).show()
            }
    }
}