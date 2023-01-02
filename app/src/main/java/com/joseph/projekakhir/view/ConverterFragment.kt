package com.joseph.projekakhir.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.joseph.projekakhir.adapter.ConverterAdapter
import com.joseph.projekakhir.databinding.FragmentConverterBinding
import com.joseph.projekakhir.model.Currency
import com.joseph.projekakhir.model.Rates
import com.joseph.projekakhir.viewmodel.ConverterViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1="param1"
private const val ARG_PARAM2="param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConverterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ConverterFragment : Fragment() {

    private lateinit var binding: FragmentConverterBinding
    private lateinit var viewModel: ConverterViewModel
    private lateinit var adapter: ConverterAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentConverterBinding.inflate(inflater, container, false)
//        viewModel = ViewModelProvider(this).get(ConverterViewModel::class.java)
//        viewModel.getRate()
//
//        viewModel.rate.observe(viewLifecycleOwner, Observer{response->
//            binding.moneyConverterRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            adapter = ConverterAdapter(response.rates as Currency)
//            binding.moneyConverterRV.adapter = adapter
//        })
        return binding.root
    }


}