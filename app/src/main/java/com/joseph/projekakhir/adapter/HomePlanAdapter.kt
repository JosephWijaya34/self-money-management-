package com.joseph.projekakhir.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.Planner2CardBinding
import com.joseph.projekakhir.databinding.PlannerCardBinding
import com.joseph.projekakhir.model.Data
import com.joseph.projekakhir.view.PlannerFragment


class HomePlanAdapter(private val dataSet: List<Data>) :
    RecyclerView.Adapter<HomePlanAdapter.ViewHolder>()  {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = Planner2CardBinding.bind(view)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view=LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.planner2_card, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.binding.nameRV2Planner.text = dataSet[position].name
        viewHolder.binding.dayRV2Planner.text = dataSet[position].time.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount()=dataSet.size

}

