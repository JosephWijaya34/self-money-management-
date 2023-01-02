package com.joseph.projekakhir.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joseph.projekakhir.databinding.ActivityPemasukanPengeluaranRvactivityBinding

class PemasukanPengeluaranRVActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityPemasukanPengeluaranRvactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityPemasukanPengeluaranRvactivityBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
    }
}