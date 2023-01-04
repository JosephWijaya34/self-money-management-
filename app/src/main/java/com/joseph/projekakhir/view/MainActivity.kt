package com.joseph.projekakhir.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.ActivityMainBinding
import com.joseph.projekakhir.model.UpdatePlanner
import com.joseph.projekakhir.viewmodel.PlannerViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityMainBinding
//    private lateinit var viewModel : PlannerViewModel

    companion object{
        var login_id = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        login_id = intent.getIntExtra("login_id", 0)

        fragmentlistener()

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
}