package com.joseph.projekakhir.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.CardUangBinding
import com.joseph.projekakhir.databinding.PlannerCardBinding
import com.joseph.projekakhir.model.DataMoney


class MoneyAdapter(private val dataSet: List<DataMoney>) :
    RecyclerView.Adapter<MoneyAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = CardUangBinding.bind(view)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_uang, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        show data
        viewHolder.binding.idUangCard.text = dataSet[position].id.toString()
        viewHolder.binding.kategoriCard.text = dataSet[position].note
        viewHolder.binding.nominalUangCard.text=dataSet[position].total_money.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount()=dataSet.size

}

