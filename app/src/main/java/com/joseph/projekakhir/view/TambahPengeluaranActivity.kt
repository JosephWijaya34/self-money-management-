package com.joseph.projekakhir.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joseph.projekakhir.databinding.ActivityTambahPengeluaranBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TambahPengeluaranActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityTambahPengeluaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityTambahPengeluaranBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
//        back button
        viewBind.backButton2.setOnClickListener {
            finish()
        }
    }
}