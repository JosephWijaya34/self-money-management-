package com.joseph.projekakhir.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.projekakhir.adapter.MoneyAdapter
import com.joseph.projekakhir.databinding.ActivityPemasukanPengeluaranBinding
import com.joseph.projekakhir.model.DataMoney
import com.joseph.projekakhir.viewmodel.UangViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PemasukanPengeluaranActivity : AppCompatActivity() {

    private lateinit var viewBind: ActivityPemasukanPengeluaranBinding
    private lateinit var viewModel: UangViewModel
    private lateinit var adapterMoney: MoneyAdapter
    private lateinit var listData: List<DataMoney>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind=ActivityPemasukanPengeluaranBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
//        back button
        viewBind.backPPButton.setOnClickListener {
            finish()
        }

        pemasukanORpengeluaran()
    }

    fun pemasukanORpengeluaran() {
//        lihat recycler view pemasukan dan pengeluaran
                try {
//            pemasukan
                    if (intent.getStringExtra("statusPP") == "Pemasukan") {
                        viewBind.titlePemasukanPengeluaranTextView.text="Pemasukan"
                        viewModel=ViewModelProvider(this).get(UangViewModel::class.java)
                        viewModel.getMoneyPemasukan()
                        viewModel.moneyPemasukan.observe(this, { response ->
                            viewBind.pemasukanpengeluarandetailRV.layoutManager=
                                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                            if (response.data != null) {
                                listData=response.data
                                adapterMoney=MoneyAdapter(listData)
                                viewBind.pemasukanpengeluarandetailRV.adapter=adapterMoney
                            } else {
                                Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show()
                            }
                        })
                        viewBind.AddPPFAB.setOnClickListener {
                            val myIntent=
                                Intent(this, TambahPemasukanActivity::class.java).apply {
                                    putExtra("id", MainActivity.login_id)
                                }
                            startActivity(myIntent)
                        }
//                pengeluaran
                    } else if (intent.getStringExtra("statusPP") == "Pengeluaran") {
                        viewBind.titlePemasukanPengeluaranTextView.text="Pengeluaran"
                        viewModel=ViewModelProvider(this).get(UangViewModel::class.java)
                        viewModel.getMoneyPengeluaran()
                        viewModel.moneyPengeluaran.observe(this, { response ->
                            viewBind.pemasukanpengeluarandetailRV.layoutManager=
                                LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                            if (response.data != null) {
                                listData=response.data
                                adapterMoney=MoneyAdapter(listData)
                                viewBind.pemasukanpengeluarandetailRV.adapter=adapterMoney
                            } else {
                                Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show()
                            }
                        })
                        viewBind.AddPPFAB.setOnClickListener {
                            val myIntent=
                                Intent(this, TambahPengeluaranActivity::class.java).apply {
                                    putExtra("id", MainActivity.login_id)
                                }
                            startActivity(myIntent)
                        }
                    } else {
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                        finish()
                    }
                } catch (e: Exception) {
                }
    }

}