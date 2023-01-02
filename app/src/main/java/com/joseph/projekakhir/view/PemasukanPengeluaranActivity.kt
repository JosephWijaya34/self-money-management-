package com.joseph.projekakhir.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joseph.projekakhir.databinding.ActivityPemasukanPengeluaranBinding

class PemasukanPengeluaranActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityPemasukanPengeluaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityPemasukanPengeluaranBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
    }
}