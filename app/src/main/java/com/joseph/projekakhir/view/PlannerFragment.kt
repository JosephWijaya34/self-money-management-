package com.joseph.projekakhir.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.joseph.projekakhir.adapter.PlanAdapter
import com.joseph.projekakhir.databinding.FragmentPlannerBinding
import com.joseph.projekakhir.model.AddPlanner
import com.joseph.projekakhir.model.Data
import com.joseph.projekakhir.model.UpdatePlanner
import com.joseph.projekakhir.viewmodel.PlannerViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [PlannerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PlannerFragment : Fragment() {

    private lateinit var binding: FragmentPlannerBinding
    private lateinit var adapterplaner: PlanAdapter
    private lateinit var viewModel: PlannerViewModel
    private lateinit var listData: List<Data>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentPlannerBinding.inflate(inflater, container, false)
        viewModel=ViewModelProvider(this).get(PlannerViewModel::class.java)
        viewModel.getPlan()
        viewModel.plan.observe(viewLifecycleOwner, Observer { response ->
            binding.PlannerRV.layoutManager=GridLayoutManager(context, 2)
            if (response.data != null) {
                listData=response.data
                adapterplaner=PlanAdapter(listData, this)
                binding.PlannerRV.adapter=adapterplaner
                Log.e("data3434", response.data.toString())
            } else {
                Toast.makeText(context, "Data Kosong", Toast.LENGTH_SHORT).show()
            }
        })

//        add plan
        binding.addPlannerPageTextView.setOnClickListener {
            val myIntent=Intent(context, AddPlannerActivity::class.java).putExtra("login_id", 0)
            startActivity(myIntent)
        }
        return binding.root
    }

    fun updateAdapter() {
        viewModel=ViewModelProvider(this).get(PlannerViewModel::class.java)
        viewModel.getPlan()
        viewModel.plan.observe(viewLifecycleOwner, Observer { response ->
            listData=response.data
            if (listData.isEmpty()) {
                binding.PlannerRV.visibility=View.INVISIBLE
            } else {
                adapterplaner.notifyDataSetChanged()
            }

        })
    }

    //    delete plan
    public fun deletePlan(planID: Int) {
        viewModel=ViewModelProvider(this)[PlannerViewModel::class.java]
        viewModel.deletePlan(planID.toString()).enqueue(object : Callback<UpdatePlanner> {
            override fun onResponse(call: Call<UpdatePlanner>, response: Response<UpdatePlanner>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Berhasil Menghapus", Toast.LENGTH_SHORT).show()
                    updateAdapter()
                } else {
                    Toast.makeText(context, "Gagal Menghapus", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(
                call: retrofit2.Call<com.joseph.projekakhir.model.UpdatePlanner>,
                t: Throwable
            ) {
                Toast.makeText(context, "Gagal Menghapus", Toast.LENGTH_SHORT).show()
            }
        })
    }

}