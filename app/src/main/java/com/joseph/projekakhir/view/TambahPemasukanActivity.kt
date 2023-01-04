package com.joseph.projekakhir.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joseph.projekakhir.databinding.ActivityTambahPemasukanBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TambahPemasukanActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityTambahPemasukanBinding
    val userID = intent.getStringExtra("id")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityTambahPemasukanBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
//        back button
        viewBind.backPemasukanButton.setOnClickListener {
            finish()
        }

        tambahPemasukan()
    }

    private fun tambahPemasukan() {
        viewBind.simpanPemasukanButton.setOnClickListener {
            val id_user = userID.toString()
            val total_money = viewBind.nominalPemasukanTextInputEditText.text.toString()
            val note = viewBind.catatanPemasukanTextInputEditText.text.toString()
            val status = "Pemasukan"

//            val pemasukan = Pemasukan(total_money, note, status)

        }
    }
}