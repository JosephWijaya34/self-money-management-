package com.joseph.projekakhir.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.layout.ParentDataModifier
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.PlannerCardBinding
import com.joseph.projekakhir.model.Data
import com.joseph.projekakhir.model.Plan
import com.joseph.projekakhir.view.AddPlannerActivity
import com.joseph.projekakhir.view.MainActivity
import com.joseph.projekakhir.view.PlannerFragment
import com.joseph.projekakhir.viewmodel.PlannerViewModel
import java.text.NumberFormat
import java.util.*


class PlanAdapter(private val dataSet: List<Data>, private val mcontext: PlannerFragment) :
    RecyclerView.Adapter<PlanAdapter.ViewHolder>() {

    @RequiresApi(Build.VERSION_CODES.N)

    fun Any.convertRupiah(): String {
        val localId = Locale("in", "ID")
        // make space between currency and number
        val formatter = NumberFormat.getCurrencyInstance(localId)
        val strFormat = formatter.format(this)
        return strFormat
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = PlannerCardBinding.bind(view)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.planner_card, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

//        show data
        viewHolder.binding.idPlannerRVTextView.text = dataSet[position].id.toString()
        viewHolder.binding.plannerItemRVTextView.text = dataSet[position].name
        viewHolder.binding.dateItemRVTextView.text = dataSet[position].time.toString()
        viewHolder.binding.pricePlannerRVTextView.text = dataSet[position].price.convertRupiah()

//        Glide.with(viewHolder.itemView.context)
//                .load(dataSet[position].w)
//                .into(viewHolder.binding.plannerItemRVImageView)

//        edit planner
        viewHolder.binding.editPlannerImageView.setOnClickListener{
            val myIntent = Intent(viewHolder.itemView.context, AddPlannerActivity::class.java).putExtra("status", "edit")
            myIntent.putExtra("id", dataSet[position].id.toString())
            myIntent.putExtra("name", dataSet[position].name)
            myIntent.putExtra("price", dataSet[position].price.toString())
            myIntent.putExtra("time", dataSet[position].time.toString())
            viewHolder.itemView.context.startActivity(myIntent)
        }

//        delete planner
        viewHolder.binding.deletePlannerImageView.setOnClickListener {
            val alertDialog = AlertDialog.Builder(it.context)
            alertDialog.apply {
                setTitle("Konfirmasi")
                setMessage("Apakah anda yakin untuk menghapus Plan ini?")
                setNegativeButton("Tidak", { dialogInterface, i -> dialogInterface.dismiss() })
                setPositiveButton("Iya", { dialogInterface, i -> dialogInterface.dismiss()
                    mcontext.deletePlan(dataSet[position].id)})
                alertDialog.show()
            }

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
