package com.joseph.projekakhir.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.ConverterCardBinding
import com.joseph.projekakhir.model.Currency

class ConverterAdapter(private val dataSet: Currency ):
    RecyclerView.Adapter<ConverterAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ConverterCardBinding.bind(view)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view=LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.converter_card, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        for (item in dataSet.rates.toString()){
            viewHolder.binding.currencyRVTextView.text = item.toString()
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.hashCode()

}
