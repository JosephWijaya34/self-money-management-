package com.joseph.projekakhir.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.FragmentHomeBinding
import com.joseph.projekakhir.view.MainActivity.Companion.login_id
import com.joseph.projekakhir.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1="param1"
private const val ARG_PARAM2="param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
  private lateinit var binding: FragmentHomeBinding
  private lateinit var viewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater)
        Toast.makeText(context, "Id : "+login_id, Toast.LENGTH_SHORT).show()
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        viewModel.getUserbyId(login_id)
        viewModel.user.observe(viewLifecycleOwner, Observer{ response ->
            Toast.makeText(context, "Username = "+response.username, Toast.LENGTH_SHORT).show()
            binding.username.text=response.username

//            binding.textView30.text=response.email
        })
        // Inflate the layout for this fragment
        return binding.root
    }


}