package com.joseph.projekakhir.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joseph.projekakhir.databinding.ActivityTambahPemasukanBinding

class TambahPemasukanActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityTambahPemasukanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityTambahPemasukanBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
    }
}