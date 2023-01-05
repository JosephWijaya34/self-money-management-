package com.joseph.projekakhir.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.FragmentAddBinding
import com.joseph.projekakhir.view.MainActivity.Companion.login_id
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class AddFragment : Fragment() {

    private lateinit var viewBind : FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBind = FragmentAddBinding.inflate(layoutInflater)

//        masuk tambah pemasukan
        viewBind.addPemasukanCardView.setOnClickListener{
            val myIntent = Intent(context, TambahPemasukanActivity::class.java).apply {
                putExtra("id", login_id)
            }
            startActivity(myIntent)
//            val fragment = AddPemasukanFragment()
//            val fragmentManager = parentFragmentManager
//            val fragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.fragment_container, fragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
        }

//        masuk tambah pengeluaran
        viewBind.addPengeluaranCardView.setOnClickListener{
            val myIntent = Intent(context, TambahPengeluaranActivity::class.java).putExtra("id", login_id)
            startActivity(myIntent)
        }

//        masuk tambah planner
        viewBind.addPlannerCardView.setOnClickListener{
            val myIntent = Intent(context, AddPlannerActivity::class.java).putExtra("id", login_id)
            startActivity(myIntent)
        }

//        masuk automatic manage
        viewBind.AutomaticManageCardView.setOnClickListener{
            val myIntent = Intent(context, AutomaticManageActivity::class.java)
            startActivity(myIntent)
        }
        return viewBind.root
    }
}