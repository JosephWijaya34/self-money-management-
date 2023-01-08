package com.joseph.projekakhir.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.joseph.projekakhir.adapter.PlanAdapter
import com.joseph.projekakhir.databinding.ActivityAddPlannerBinding
import com.joseph.projekakhir.model.AddPlanner
import com.joseph.projekakhir.model.UpdatePlanner
import com.joseph.projekakhir.viewmodel.PlannerViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class AddPlannerActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityAddPlannerBinding
    private lateinit var viewModelPlan: PlannerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityAddPlannerBinding.inflate(layoutInflater)
        setContentView(viewBind.root)

        try {
            if (intent.getStringExtra("status") == "edit") {
                editPlanner()
            } else {
                tambahPlanner()
            }
        } catch (e: Exception) {
        }

//        kembali ke menu
        viewBind.backAddPageButton.setOnClickListener {
            finish()
        }
    }

    private fun tambahPlanner() {
        viewBind.simpanPlannerBaruButton.setOnClickListener {
            val id_user=MainActivity.login_id.toString()
            val name=viewBind.namaBarangTextInputEditText.text.toString()
            val price=viewBind.hargaTextInputEditText.text.toString().trim()
            val time=viewBind.waktuPlanBarangTextInputEditText.text.toString().trim()
            viewModelPlan=
                ViewModelProvider(this)[PlannerViewModel::class.java]//untuk mengakses viewmodel
            viewModelPlan.addPlaner(id_user, name, price, time)
                .enqueue(object : Callback<AddPlanner> {
                    override fun onResponse(call: Call<AddPlanner>, response: Response<AddPlanner>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@AddPlannerActivity, "Berhasil Menambahkan", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@AddPlannerActivity, MainActivity::class.java).apply {
                                putExtra("login_id", MainActivity.login_id)
                                putExtra("moveToRVPlanner","Planner")
                            }
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@AddPlannerActivity,
                                "Gagal Menambahkan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<AddPlanner>, t: Throwable) {
                        Toast.makeText(
                            this@AddPlannerActivity,
                            "Gagal Menambahkan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }
    }

    private fun editPlanner() {
        viewBind.headlinePlannerTextView.text="Edit Planner"
//        show edit data
        viewBind.namaBarangTextInputEditText.setText(intent.getStringExtra("name"))
        viewBind.hargaTextInputEditText.setText(intent.getStringExtra("price"))
        viewBind.waktuPlanBarangTextInputEditText.setText(intent.getStringExtra("time"))
//        edit planner
        viewBind.simpanPlannerBaruButton.setOnClickListener {
            val id=intent.getStringExtra("id").toString()
            val name=viewBind.namaBarangTextInputEditText.text.toString().trim()
            val price=viewBind.hargaTextInputEditText.text.toString().trim()
            val time=viewBind.waktuPlanBarangTextInputEditText.text.toString().trim()
            viewModelPlan=
                ViewModelProvider(this)[PlannerViewModel::class.java]//untuk mengakses viewmodel
            viewModelPlan.updatePlan(id, name, price, time)
                .enqueue(object : Callback<UpdatePlanner> {
                    override fun onResponse(
                        call: Call<UpdatePlanner>,
                        response: Response<UpdatePlanner>
                    ) {
                        if (response.isSuccessful) {
                            Toast.makeText(
                                this@AddPlannerActivity,
                                "Berhasil Mengedit Planner",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@AddPlannerActivity, MainActivity::class.java).apply {
                                putExtra("login_id", MainActivity.login_id)
                                putExtra("moveToRVPlanner","Planner")
                            }
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@AddPlannerActivity,
                                "Gagal Mengedit Planner",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<UpdatePlanner>, t: Throwable) {
                        Toast.makeText(
                            this@AddPlannerActivity,
                            "Gagal Mengedit Planner",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                })
        }
    }
}
