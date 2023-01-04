package com.joseph.projekakhir.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joseph.projekakhir.databinding.ActivityChangePasswordBinding
import com.joseph.projekakhir.databinding.ActivityMainBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityChangePasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

//        back button
        viewBind.backCPButton.setOnClickListener {
            finish()
        }
    }
}