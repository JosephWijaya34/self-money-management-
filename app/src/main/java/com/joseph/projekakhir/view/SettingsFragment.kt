package com.joseph.projekakhir.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.FragmentSettingsBinding
import com.joseph.projekakhir.view.MainActivity.Companion.login_id
import com.joseph.projekakhir.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private lateinit var viewBind : FragmentSettingsBinding
    private lateinit var viewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBind = FragmentSettingsBinding.inflate(layoutInflater)

        //    show data user
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        viewModel.getUserbyId(login_id)
        viewModel.user.observe(viewLifecycleOwner, Observer{ response ->
            viewBind.namaUserSettingsTextView.text=response.username
            viewBind.emailProfileTextView.text=response.email
        })

//        edit user yang lagi login
        viewBind.editUserCardView.setOnClickListener {
            viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
            viewModel.getUserbyId(login_id)
            viewModel.user.observe(viewLifecycleOwner, Observer{ response ->
                val intent = Intent(context,RegisterActivity::class.java).apply {
                    putExtra("editUser","editUser")
                    putExtra("id", login_id)
                    putExtra("email", response.email)
                }
                startActivity(intent)
            })
        }

//        masuk change password
        viewBind.changepasswordSettingsCardView.setOnClickListener {
            val intent = Intent(activity, ChangePasswordActivity::class.java).apply {
                putExtra("id", login_id)
            }
            startActivity(intent)
        }

        listener()
        return viewBind.root
    }

    fun listener(){
        viewBind.logoutSettingsCardView.setOnClickListener {
            login_id = 0
            val myIntent = Intent(context, LoginActivity::class.java)
            startActivity(myIntent)
        }
    }

}