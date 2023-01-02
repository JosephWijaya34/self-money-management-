package com.joseph.projekakhir.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.PlannerCardBinding
import com.joseph.projekakhir.model.Data
import com.joseph.projekakhir.model.Plan

class PlanAdapter(private val dataSet: List<Data>) :
        RecyclerView.Adapter<PlanAdapter.ViewHolder>() {

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
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.binding.idPlannerRVTextView.text = dataSet[position].id.toString()
        viewHolder.binding.plannerItemRVTextView.text = dataSet[position].name
        viewHolder.binding.dateItemRVTextView.text = dataSet[position].time.toString()
        viewHolder.binding.pricePlannerRVTextView.text = dataSet[position].price.toString()
//        Glide.with(viewHolder.itemView.context)
//                .load(dataSet[position].w)
//                .into(viewHolder.binding.plannerItemRVImageView)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
