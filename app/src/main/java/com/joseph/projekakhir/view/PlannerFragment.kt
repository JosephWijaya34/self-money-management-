package com.joseph.projekakhir.view

import android.app.Application
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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1="param1"
private const val ARG_PARAM2="param2"

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


        return binding.root
    }


}