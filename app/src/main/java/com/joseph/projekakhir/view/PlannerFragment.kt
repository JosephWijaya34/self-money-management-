package com.joseph.projekakhir.view

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.projekakhir.R
import com.joseph.projekakhir.adapter.PlanAdapter
import com.joseph.projekakhir.databinding.FragmentPlannerBinding
import com.joseph.projekakhir.viewmodel.PlannerViewModel
import dagger.hilt.android.AndroidEntryPoint
/**
 * A simple [Fragment] subclass.
 * Use the [PlannerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class PlannerFragment : Fragment() {

    private lateinit var binding: FragmentPlannerBinding
    private lateinit var adapterplaner : PlanAdapter
    private lateinit var viewModel : PlannerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentPlannerBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(PlannerViewModel::class.java)
        viewModel.getPlan()

        viewModel.plan.observe(viewLifecycleOwner, Observer{response->
            binding.PlannerRV.layoutManager = GridLayoutManager(context, 2)
            adapterplaner = PlanAdapter(response.data)
            binding.PlannerRV.adapter = adapterplaner
        })

//        add plan
        binding.addPlannerPageTextView.setOnClickListener {
            val myIntent = Intent(context, AddPlannerActivity::class.java).putExtra("login_id", 0)
            startActivity(myIntent)
        }

        return binding.root
    }

}