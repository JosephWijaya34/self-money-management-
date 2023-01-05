package com.joseph.projekakhir.view

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.projekakhir.adapter.HomePlanAdapter
import com.joseph.projekakhir.databinding.FragmentHomeBinding
import com.joseph.projekakhir.model.Data
import com.joseph.projekakhir.view.MainActivity.Companion.login_id
import com.joseph.projekakhir.viewmodel.PlannerViewModel
import com.joseph.projekakhir.viewmodel.UangViewModel
import com.joseph.projekakhir.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: UsersViewModel
    private lateinit var viewModelUang: UangViewModel
    private lateinit var viewModelPlan: PlannerViewModel
    private lateinit var adapterplaner: HomePlanAdapter
    private lateinit var listData: List<Data>

    @RequiresApi(Build.VERSION_CODES.N)

    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        // make space between currency and number
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater)
        viewModel=ViewModelProvider(this).get(UsersViewModel::class.java)
        viewModel.getUserbyId(login_id)
        viewModel.user.observe(viewLifecycleOwner, Observer { response ->
            binding.username.text=response.username
        })

        showUang()
        welcome()
        redirectPP()
        showDataPlan()
        // Inflate the layout for this fragment
        return binding.root
    }

    fun showDataPlan(){
        viewModelPlan=ViewModelProvider(this).get(PlannerViewModel::class.java)
        viewModelPlan.getPlan()
        viewModelPlan.plan.observe(viewLifecycleOwner, Observer { response ->
            binding.plannerRVHomeFragment.layoutManager=
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            if (response.data != null) {
                listData=response.data
                adapterplaner=HomePlanAdapter(listData)
                binding.plannerRVHomeFragment.adapter=adapterplaner
                binding.addgambarPlanImageView.visibility=View.GONE
            } else {
                binding.addgambarPlanImageView.visibility=View.VISIBLE
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun showUang() {
        //        show total pemasukan
        viewModelUang=ViewModelProvider(this).get(UangViewModel::class.java)
        viewModelUang.getSemuaPemasukan(login_id)
        viewModelUang.SemuaPemasukan.observe(viewLifecycleOwner, Observer { response ->
            if (response.data.total_money != 0) {
                binding.showtotalPemasukanHomeTextView.text=response.data.total_money.convertRupiah()
            } else {
                binding.showtotalPemasukanHomeTextView.text="Rp. 0"
            }
        })
        //      show total pengeluaran
        viewModelUang.getSemuaPengeluaran(login_id)
        viewModelUang.SemuaPengeluaran.observe(viewLifecycleOwner, Observer { response ->
            if (response.data.total_money != 0) {
                binding.showtotalPengeluaranHomeTextView.text=response.data.total_money.convertRupiah()
            } else {
                binding.showtotalPemasukanHomeTextView.text="Rp. 0"
            }
        })
//        show total uang
        viewModelUang.getSemuaUang(login_id)
        viewModelUang.semuaUang.observe(viewLifecycleOwner, Observer { response ->
            if (response.data.total_money != 0) {
                binding.jumlahsaldoTotalTextViewHF.text=response.data.total_money.convertRupiah()
            } else {
                binding.jumlahsaldoTotalTextViewHF.text="Rp. 0"
            }
        })
    }

    fun redirectPP() {
//       intent ke Recycler View dengan status berbeda
        binding.pemasukanHomeButton.setOnClickListener {
            val myIntent=Intent(context, PemasukanPengeluaranActivity::class.java).apply {
                putExtra("id", login_id)
                putExtra("statusPP", "Pemasukan")
            }
            startActivity(myIntent)
        }

        binding.pengeluaranHomeButton.setOnClickListener {
            val myIntent=Intent(context, PemasukanPengeluaranActivity::class.java).apply {
                putExtra("id", login_id)
                putExtra("statusPP", "Pengeluaran")
            }
            startActivity(myIntent)
        }
    }

    fun welcome() {
        //get current local time
        val currentDay=Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val currentMonth=Calendar.getInstance().get(Calendar.MONTH)
        val currentYear=Calendar.getInstance().get(Calendar.YEAR)

        if (currentMonth == 0) {
            binding.tanggal.text=("$currentDay Januari $currentYear")
        } else if (currentMonth == 1) {
            binding.tanggal.text=("$currentDay Februari $currentYear")
        } else if (currentMonth == 2) {
            binding.tanggal.text=("$currentDay Maret $currentYear")
        } else if (currentMonth == 3) {
            binding.tanggal.text=("$currentDay April $currentYear")
        } else if (currentMonth == 4) {
            binding.tanggal.text=("$currentDay Mei $currentYear")
        } else if (currentMonth == 5) {
            binding.tanggal.text=("$currentDay Juni $currentYear")
        } else if (currentMonth == 6) {
            binding.tanggal.text=("$currentDay Juli $currentYear")
        } else if (currentMonth == 7) {
            binding.tanggal.text=("$currentDay Agustus $currentYear")
        } else if (currentMonth == 8) {
            binding.tanggal.text=("$currentDay September $currentYear")
        } else if (currentMonth == 9) {
            binding.tanggal.text=("$currentDay Oktober $currentYear")
        } else if (currentMonth == 10) {
            binding.tanggal.text=("$currentDay November $currentYear")
        } else if (currentMonth == 11) {
            binding.tanggal.text=("$currentDay Desember $currentYear")
        }

    }


}