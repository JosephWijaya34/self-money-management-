package com.joseph.projekakhir.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityMainBinding
//    private lateinit var viewModel : PlannerViewModel

    companion object {
        var login_id=0
        var login = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        login_id=intent.getIntExtra("login_id", 0)
        val sharedPreference = getSharedPreferences("id", MODE_PRIVATE)
        val editor = sharedPreference.edit()
        if (login){
            if (login_id !=0){
                editor.putInt("id", login_id)
                editor.apply()
            }
        }else{
            editor.putInt("id", 0)
            editor.apply()
            login_id = sharedPreference.getInt("id", 0)
        }

        if (sharedPreference.getInt("id", 0) != 0){
            login_id = sharedPreference.getInt("id", 0)
        }

        fragmentlistener()
        checkPutExtra()

    }

    private fun fragmentlistener() {
        replaceFragment(HomeFragment())
        viewBind.bottomNavMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.plannerFragment -> replaceFragment(PlannerFragment())
                R.id.addFragment -> replaceFragment(AddFragment())
                R.id.converterFragment -> replaceFragment(ConverterFragment())
                R.id.settingsFragment -> replaceFragment(SettingsFragment())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {

        val fragmentManager=supportFragmentManager
        val fragmentTransaction=fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content_FrameLayout, fragment)
        fragmentTransaction.commit()

    }

    fun checkPutExtra() {
        if (intent.getStringExtra("moveTORVpp") == "Pemasukan") {
            val myIntent=Intent(this, PemasukanPengeluaranActivity::class.java).apply {
                putExtra("id", login_id)
                putExtra("statusPP", "Pemasukan")
            }
            startActivity(myIntent)
        } else if (intent.getStringExtra("moveTORVpp") == "Pengeluaran") {
            val myIntent=Intent(this, PemasukanPengeluaranActivity::class.java).apply {
                putExtra("id", login_id)
                putExtra("statusPP", "Pengeluaran")
            }
            startActivity(myIntent)
        }else if (intent.getStringExtra("editUser") == "editUser"){
            replaceFragment(SettingsFragment())
        }
    }
}