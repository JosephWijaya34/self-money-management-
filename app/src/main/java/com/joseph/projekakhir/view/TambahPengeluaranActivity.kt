package com.joseph.projekakhir.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.joseph.projekakhir.databinding.ActivityTambahPengeluaranBinding
import com.joseph.projekakhir.model.AddPemasukan
import com.joseph.projekakhir.viewmodel.UangViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class TambahPengeluaranActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityTambahPengeluaranBinding
    private lateinit var viewModel: UangViewModel
    val userID=MainActivity.login_id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityTambahPengeluaranBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
//        back button
        viewBind.backButton2.setOnClickListener {
            finish()
        }
//        check edit or create
        try {
            if (intent.getStringExtra("DetailPP") == "editPemasukan") {
                editPengeluaran()
            } else {
                tambahPengeluaran()
            }
        } catch (e: Exception) {
        }

    }

    fun editPengeluaran() {
        //        show edit data
        viewBind.titlePengeluaranTextView.text="Edit Pengeluaran"
        viewBind.nominalPengeluaranTextInputEditLayout.setText(
            intent.getStringExtra("total_money").toString()
        )
        viewBind.catatanPengeluaranTextInputEditLayout.setText(
            intent.getStringExtra("note").toString()
        )
        //        edit data
        viewBind.simpanPengeluaranButton.setOnClickListener {
            val id_money=intent.getStringExtra("id").toString()
            val total_money=viewBind.nominalPengeluaranTextInputEditLayout.text.toString()
            val note=viewBind.catatanPengeluaranTextInputEditLayout.text.toString()
            val status="Pengeluaran"
            viewModel=ViewModelProvider(this)[UangViewModel::class.java]
            viewModel.updatePemasukan(id_money, total_money, note, status).enqueue(object : Callback<AddPemasukan> {
                override fun onResponse(call: Call<AddPemasukan>, response: Response<AddPemasukan>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@TambahPengeluaranActivity, "Berhasil Menambahkan", Toast.LENGTH_SHORT).show()
                        val intent=Intent(this@TambahPengeluaranActivity, MainActivity::class.java).apply {
                            putExtra("login_id", MainActivity.login_id)
                            putExtra("moveTORVpp", "Pengeluaran")
                        }
                        startActivity(intent)
                        finish()
                    }
                }

                override fun onFailure(call: Call<AddPemasukan>, t: Throwable) {
                    Toast.makeText(this@TambahPengeluaranActivity, "Gagal Menambahkan", Toast.LENGTH_SHORT).show()
                }
            })

        }
    }
        private fun tambahPengeluaran() {
            viewBind.simpanPengeluaranButton.setOnClickListener {
                val id_user=userID.toString()
                val total_money=viewBind.nominalPengeluaranTextInputEditLayout.text.toString()
                val note=viewBind.catatanPengeluaranTextInputEditLayout.text.toString()
                val status="Pengeluaran"
                viewModel=ViewModelProvider(this)[UangViewModel::class.java]
                viewModel.addPemasukan(id_user, total_money, note, status).enqueue(object : Callback<AddPemasukan> {
                        override fun onResponse(call: Call<AddPemasukan>, response: Response<AddPemasukan>) {
                            if (response.isSuccessful) {
                                Toast.makeText(this@TambahPengeluaranActivity, "Berhasil Menambahkan", Toast.LENGTH_SHORT).show()
                                val intent=Intent(this@TambahPengeluaranActivity, MainActivity::class.java).apply {
                                    putExtra("login_id", MainActivity.login_id)
                                    putExtra("moveTORVpp", "Pengeluaran")
                                }
                                startActivity(intent)
                                finish()
                            }
                        }

                        override fun onFailure(call: Call<AddPemasukan>, t: Throwable) {
                            Toast.makeText(this@TambahPengeluaranActivity, "Gagal Menambahkan", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
        }

}