package com.joseph.projekakhir.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityIntroBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

//        login button
        viewBind.introMasukButton.setOnClickListener {
            val myIntent = Intent(this, LoginActivity::class.java)
            startActivity(myIntent)
            finish()
        }

//        register button
        viewBind.introBikinAkunTextView.setOnClickListener {
            val myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)
            finish()
        }
        viewBind.introBikinAkun2TextView.setOnClickListener {
            val myIntent = Intent(this, RegisterActivity::class.java)
            startActivity(myIntent)
            finish()
        }
    }
}