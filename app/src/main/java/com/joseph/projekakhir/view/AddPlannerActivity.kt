package com.joseph.projekakhir.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joseph.projekakhir.databinding.ActivityAddPlannerBinding

class AddPlannerActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityAddPlannerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityAddPlannerBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
    }
}