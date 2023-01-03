package com.joseph.projekakhir.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.joseph.projekakhir.adapter.PlanAdapter
import com.joseph.projekakhir.databinding.ActivityAddPlannerBinding
import com.joseph.projekakhir.model.addPlanner
import com.joseph.projekakhir.viewmodel.PlannerViewModel
import com.joseph.projekakhir.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class AddPlannerActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityAddPlannerBinding
    private lateinit var viewModelPlan : PlannerViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityAddPlannerBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

//        kembali ke menu
        viewBind.backAddPageButton.setOnClickListener {
            finish()
        }

        tambahPlanner()
    }

    private fun tambahPlanner() {
        viewBind.simpanPlannerBaruButton.setOnClickListener {
            val id_user=intent.getIntExtra("login_id",0).toString().toInt()
            val name=viewBind.namaBarangTextInputEditText.text.toString().trim()
            val price=viewBind.hargaTextInputEditText.text.toString().trim().toInt()
            val time=viewBind.waktuPlanBarangTextInputEditText.text.toString().trim().toInt()
            viewModelPlan=ViewModelProvider(this)[PlannerViewModel::class.java]//untuk mengakses viewmodel
            viewModelPlan.AddPlaner(id_user,name,price,time).enqueue(object : Callback<addPlanner> {
                override fun onResponse(call: Call<addPlanner>, response: Response<addPlanner>) {
                    if (response.isSuccessful){
                        Toast.makeText(this@AddPlannerActivity, "Berhasil Menambahkan", Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(this@AddPlannerActivity, "Gagal Menambahkan", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<addPlanner>, t: Throwable) {
                    Toast.makeText(this@AddPlannerActivity, "Gagal Menambahkan", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}