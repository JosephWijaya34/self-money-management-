package com.joseph.projekakhir.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        showLogin()
    }

    private fun showLogin() {
        Handler(Looper.getMainLooper()).postDelayed({
            val die=Intent(this, IntroActivity::class.java)
            die.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(die)
        }, 3000)
    }
}


