package com.joseph.projekakhir.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joseph.projekakhir.databinding.ActivityAddPlannerBinding
import com.joseph.projekakhir.databinding.ActivityDetailPemasukanPengeluaranBinding

class DetailPemasukanPengeluaranActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityDetailPemasukanPengeluaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityDetailPemasukanPengeluaranBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
    }
}