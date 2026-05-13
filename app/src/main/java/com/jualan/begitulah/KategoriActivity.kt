package com.jualan.begitulah

import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class KategoriActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var btnTambahProduct: Button
    private lateinit var layoutListProduct: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori)

        db = FirebaseFirestore.getInstance()

        btnTambahProduct = findViewById(R.id.btnTambahProduct)
        layoutListProduct = findViewById(R.id.layoutListProduct)

        btnTambahProduct.setOnClickListener {
            startActivity(Intent(this, TambahProductActivity::class.java))
        }

        tampilkanProduct()
    }

    override fun onResume() {
        super.onResume()
        tampilkanProduct()
    }

    private fun tampilkanProduct() {
        layoutListProduct.removeAllViews()

        db.collection("products")
            .get()
            .addOnSuccessListener { result ->

                if (result.isEmpty) {
                    val emptyText = TextView(this)
                    emptyText.text = "Belum ada product"
                    emptyText.textSize = 16f
                    emptyText.setTextColor(getColorFromAttr(android.R.attr.textColorSecondary))
                    emptyText.setPadding(24, 24, 24, 24)
                    layoutListProduct.addView(emptyText)
                    return@addOnSuccessListener
                }

                for (document in result.documents) {
                    val nama = document.getString("nama") ?: "-"
                    val kategori = document.getString("kategoriNama") ?: "-"
                    val harga = document.getLong("harga") ?: 0
                    val stok = document.getLong("stok") ?: 0

                    val textView = TextView(this)
                    textView.text = "Nama: $nama\nKategori: $kategori\nHarga: Rp $harga\nStok: $stok"
                    textView.textSize = 16f
                    textView.setTextColor(getColorFromAttr(android.R.attr.textColorPrimary))
                    textView.setPadding(28, 22, 28, 22)

                    layoutListProduct.addView(textView)
                }
            }
    }

    private fun getColorFromAttr(attr: Int): Int {
        val typedValue = TypedValue()
        theme.resolveAttribute(attr, typedValue, true)
        return typedValue.data
    }
}