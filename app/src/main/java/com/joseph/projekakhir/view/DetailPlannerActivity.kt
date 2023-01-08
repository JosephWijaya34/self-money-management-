package com.joseph.projekakhir.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.joseph.projekakhir.databinding.ActivityDetailPlannerBinding
import com.joseph.projekakhir.model.UpdatePlanner
import com.joseph.projekakhir.viewmodel.PlannerViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class DetailPlannerActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityDetailPlannerBinding
    private lateinit var viewModel: PlannerViewModel
    var planID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityDetailPlannerBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        //back button
        viewBind.backButtonfromdetailPlanner.setOnClickListener {
            finish()
        }
        // delete Button
        viewBind.deletePlannerButton.setOnClickListener {
            deletePlanner()
        }
        // edit Button
        viewBind.ubahPlannerButton.setOnClickListener {
            updatePlanner()
        }
        readData()
    }

    private fun readData(){
        val id = intent.getStringExtra("id").toString()
        val name = intent.getStringExtra("name").toString()
        val price = intent.getStringExtra("price").toString()
        val time = intent.getStringExtra("time").toString()

        viewBind.namaPlannerTextView.text = name
        viewBind.priceDetailPlannerTextView.text = price
        viewBind.waktuPlannerTextView.text = time
    }

    private fun updatePlanner(){
        val id = intent.getStringExtra("id").toString()
        val name = intent.getStringExtra("name").toString()
        val price = intent.getStringExtra("price").toString()
        val time = intent.getStringExtra("time").toString()

        val intent=Intent(this,AddPlannerActivity::class.java)
        intent.putExtra("status", "edit")
        intent.putExtra("id",id)
        intent.putExtra("name", name)
        intent.putExtra("price",price)
        intent.putExtra("time",time)
        startActivity(intent)
    }

    private fun deletePlanner(){
        planID = intent.getStringExtra("id").toString()
        if (planID != ""){
            viewModel=ViewModelProvider(this)[PlannerViewModel::class.java]
            viewModel.deletePlan(planID).enqueue(object : Callback<UpdatePlanner> {
                override fun onResponse(call: Call<UpdatePlanner>, response: Response<UpdatePlanner>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@DetailPlannerActivity, "Berhasil Menghapus", Toast.LENGTH_SHORT).show()
                        val myIntent = Intent(this@DetailPlannerActivity, MainActivity::class.java).apply {
                            putExtra("login_id", MainActivity.login_id)
                            putExtra("moveToRVPlanner","Planner")
                        }
                        startActivity(myIntent)
                    } else {
                        Toast.makeText(this@DetailPlannerActivity, "Gagal Menghapus", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(
                    call: Call<UpdatePlanner>,
                    t: Throwable
                ) {
                    Toast.makeText(this@DetailPlannerActivity, "Gagal Menghapus", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}