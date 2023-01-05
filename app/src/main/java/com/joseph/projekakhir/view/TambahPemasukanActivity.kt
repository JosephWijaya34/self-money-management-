package com.joseph.projekakhir.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.joseph.projekakhir.databinding.ActivityTambahPemasukanBinding
import com.joseph.projekakhir.model.AddPemasukan
import com.joseph.projekakhir.viewmodel.UangViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class TambahPemasukanActivity : AppCompatActivity() {

    private lateinit var viewBind : ActivityTambahPemasukanBinding
    private lateinit var viewModel: UangViewModel
    val userID = MainActivity.login_id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityTambahPemasukanBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
//        back button
        viewBind.backPemasukanButton.setOnClickListener {
            finish()
        }
//        check edit or create
        try {
            if (intent.getStringExtra("DetailPP") == "editPemasukan") {
                editPemasukan()
            } else {
                tambahPemasukan()
            }
        } catch (e: Exception) {
        }

    }

    fun editPemasukan(){
//        show edit data
        viewBind.headLinePemasukanTextView.text="Edit Pemasukan"
        viewBind.nominalPemasukanTextInputEditText.setText(intent.getStringExtra("total_money").toString())
        viewBind.catatanPemasukanTextInputEditText.setText(intent.getStringExtra("note").toString())
//        edit data
        viewBind.simpanPemasukanButton.setOnClickListener {
            val id_money = intent.getStringExtra("id").toString()
            val total_money = viewBind.nominalPemasukanTextInputEditText.text.toString()
            val note = viewBind.catatanPemasukanTextInputEditText.text.toString()
            val status = "Pemasukan"
            viewModel = ViewModelProvider(this)[UangViewModel::class.java]
            viewModel.updatePemasukan(id_money, total_money, note, status).enqueue(object : Callback<AddPemasukan> {
                override fun onResponse(call: Call<AddPemasukan>, response: Response<AddPemasukan>) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@TambahPemasukanActivity, "Berhasil Menambahkan", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@TambahPemasukanActivity, MainActivity::class.java).apply {
                            putExtra("login_id", MainActivity.login_id)
                            putExtra("moveTORVpp", "Pemasukan")
                        }
                        startActivity(intent)
                        finish()
                    }
                }
                override fun onFailure(call: Call<AddPemasukan>, t: Throwable) {
                    Toast.makeText(this@TambahPemasukanActivity, "Gagal Menambahkan", Toast.LENGTH_SHORT).show()
                }
            })
        }

    }

    private fun tambahPemasukan() {
        viewBind.simpanPemasukanButton.setOnClickListener {
            val id_user = userID.toString()
            val total_money = viewBind.nominalPemasukanTextInputEditText.text.toString()
            val note = viewBind.catatanPemasukanTextInputEditText.text.toString()
            val status = "Pemasukan"
            viewModel = ViewModelProvider(this)[UangViewModel::class.java]
            viewModel.addPemasukan(id_user, total_money, note, status)
                .enqueue(object : Callback<AddPemasukan> {
                    override fun onResponse(call: Call<AddPemasukan>, response: Response<AddPemasukan>) {
                        if (response.isSuccessful) {
                            Toast.makeText(this@TambahPemasukanActivity, "Berhasil Menambahkan", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@TambahPemasukanActivity, MainActivity::class.java).apply {
                                putExtra("login_id", MainActivity.login_id)
                                putExtra("moveTORVpp", "Pemasukan")
                            }
                            startActivity(intent)
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<AddPemasukan>, t: Throwable) {
                        Toast.makeText(this@TambahPemasukanActivity, "Gagal Menambahkan", Toast.LENGTH_SHORT).show()
                    }
                })


        }
    }
}