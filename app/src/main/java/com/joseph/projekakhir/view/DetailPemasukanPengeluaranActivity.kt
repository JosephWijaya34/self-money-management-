package com.joseph.projekakhir.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.joseph.projekakhir.databinding.ActivityDetailPemasukanPengeluaranBinding
import com.joseph.projekakhir.model.AddPemasukan
import com.joseph.projekakhir.view.MainActivity.Companion.login_id
import com.joseph.projekakhir.viewmodel.UangViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class DetailPemasukanPengeluaranActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityDetailPemasukanPengeluaranBinding
    private lateinit var viewModel: UangViewModel
    var userID=login_id

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityDetailPemasukanPengeluaranBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
//        back button
        viewBind.backDetailPPButton.setOnClickListener {
            finish()
        }

//      function
        bacaData()
    }

    fun bacaData() {
        if (intent.getStringExtra("statusPPDetail") == "Pemasukan") {
            viewBind.idTableUangTextView.text=intent.getStringExtra("id").toString()
            viewBind.KategoriDetailPPTextView.text=intent.getStringExtra("statusPPDetail").toString()
            viewBind.hargaDetailPPTextView.text=intent.getStringExtra("total_money").toString()
            viewBind.CatatanPPTextView.text=intent.getStringExtra("note").toString()
//            delete data
            viewBind.deleteDetailPPButton.setOnClickListener {
                deletePemasukan()
            }
            //simpan edit data
            var id = intent.getStringExtra("id").toString()
            var totalMoney = intent.getStringExtra("total_money").toString()
            var noteMoney = intent.getStringExtra("note").toString()
            // edit data
            viewBind.ubahDetailPPButton.setOnClickListener {
                val intent=Intent(this,TambahPemasukanActivity::class.java)
                intent.putExtra("id",id)
                intent.putExtra("total_money", totalMoney)
                intent.putExtra("note",noteMoney)
                intent.putExtra("DetailPP","editPemasukan")
                startActivity(intent)
            }

        } else if (intent.getStringExtra("statusPPDetail") == "Pengeluaran") {
            viewBind.idTableUangTextView.text=intent.getStringExtra("id").toString()
            viewBind.KategoriDetailPPTextView.text=
                intent.getStringExtra("statusPPDetail").toString()
            viewBind.hargaDetailPPTextView.text=intent.getStringExtra("total_money").toString()
            viewBind.CatatanPPTextView.text=intent.getStringExtra("note").toString()
            //            delete data
            viewBind.deleteDetailPPButton.setOnClickListener {
                deletePengeluaran()
            }
            //simpan edit data
            var id = intent.getStringExtra("id").toString()
            var totalMoney = intent.getStringExtra("total_money").toString()
            var noteMoney = intent.getStringExtra("note").toString()
            // edit data
            viewBind.ubahDetailPPButton.setOnClickListener {
                val intent=Intent(this,TambahPengeluaranActivity::class.java)
                intent.putExtra("id",id)
                intent.putExtra("total_money", totalMoney)
                intent.putExtra("note",noteMoney)
                intent.putExtra("DetailPP","editPengeluaran")
                startActivity(intent)
            }

        }else{
            Toast.makeText(this,"Data tidak ditemukan",Toast.LENGTH_SHORT).show()
        }
    }

    fun deletePemasukan() {
        var delete_id=intent.getStringExtra("id").toString()
        //            delete data
        viewModel=ViewModelProvider(this).get(UangViewModel::class.java)
        viewModel.deleteMoney(delete_id).enqueue(object : Callback<AddPemasukan> {
            override fun onResponse(call: Call<AddPemasukan>, response: Response<AddPemasukan>) {
                if (response.isSuccessful) {
                    val myIntent=Intent(
                        this@DetailPemasukanPengeluaranActivity,
                        MainActivity::class.java
                    ).apply {
                        putExtra("login_id", userID)
                        putExtra("moveTORVpp", "Pemasukan")
                    }
                    startActivity(myIntent)
                    finish()
                }
            }

            override fun onFailure(call: Call<AddPemasukan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(
                    this@DetailPemasukanPengeluaranActivity,
                    "Gagal Menghapus",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

    fun deletePengeluaran() {
        var delete_id=intent.getStringExtra("id").toString()
        //            delete data
        viewModel=ViewModelProvider(this).get(UangViewModel::class.java)
        viewModel.deleteMoney(delete_id).enqueue(object : Callback<AddPemasukan> {
            override fun onResponse(call: Call<AddPemasukan>, response: Response<AddPemasukan>) {
                if (response.isSuccessful) {
                    val myIntent=Intent(
                        this@DetailPemasukanPengeluaranActivity,
                        MainActivity::class.java
                    ).apply {
                        putExtra("login_id", userID)
                        putExtra("moveTORVpp", "Pengeluaran")
                    }
                    startActivity(myIntent)
                    finish()
                }
            }

            override fun onFailure(call: Call<AddPemasukan>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(
                    this@DetailPemasukanPengeluaranActivity,
                    "Gagal Menghapus",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}