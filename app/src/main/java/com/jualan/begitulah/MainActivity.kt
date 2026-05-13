package com.jualan.begitulah

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var cardAkun: CardView
    private lateinit var cardProduct: CardView
    private lateinit var cardTambahKategori: CardView
    private lateinit var cardPegawai: CardView
    private lateinit var cardCabang: CardView
    private lateinit var cardPrinter: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardAkun = findViewById(R.id.cardAkun)
        cardKategori = findViewById(R.id.cardKategori)
        cardTambahKategori = findViewById(R.id.cardTambahKategori)
        cardPegawai = findViewById(R.id.cardPegawai)
        cardCabang = findViewById(R.id.cardCabang)
        cardPrinter = findViewById(R.id.cardPrinter)

        cardAkun.setOnClickListener {
            startActivity(Intent(this, AkunActivity::class.java))
        }

        cardKategori.setOnClickListener {
            startActivity(Intent(this, KategoriActivity::class.java))
        }

        cardTambahKategori.setOnClickListener {
            startActivity(Intent(this, TambahKategoriActivity::class.java))
        }

        cardPegawai.setOnClickListener {
            startActivity(Intent(this, PegawaiActivity::class.java))
        }

        cardCabang.setOnClickListener {
            startActivity(Intent(this, CabangActivity::class.java))
        }

        cardPrinter.setOnClickListener {
            startActivity(Intent(this, PrinterActivity::class.java))
        }
        cardProduct.setOnClickListener {
            startActivity(Intent(this, KategoriActivity::class.java))
        }

    }


