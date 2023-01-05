package com.joseph.projekakhir.adapter

import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.joseph.projekakhir.R
import com.joseph.projekakhir.databinding.CardUangBinding
import com.joseph.projekakhir.databinding.PlannerCardBinding
import com.joseph.projekakhir.model.DataMoney
import com.joseph.projekakhir.view.DetailPemasukanPengeluaranActivity
import com.joseph.projekakhir.view.MainActivity
import java.text.NumberFormat
import java.util.*


class MoneyAdapter(private val dataSet: List<DataMoney>) :
    RecyclerView.Adapter<MoneyAdapter.ViewHolder>() {
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
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //        show data
        viewHolder.binding.idUangCard.text = dataSet[position].id.toString()
        viewHolder.binding.kategoriCard.text = dataSet[position].note
        viewHolder.binding.nominalUangCard.text=dataSet[position].total_money.convertRupiah()
        // move to detail activity
        viewHolder.binding.cardUangCardView.setOnClickListener {
            val myIntent = Intent(viewHolder.itemView.context, DetailPemasukanPengeluaranActivity::class.java).apply {
                putExtra("user_id",MainActivity.login_id)
                putExtra("id", dataSet[position].id.toString())
                putExtra("note", dataSet[position].note)
                putExtra("total_money", dataSet[position].total_money.toString())
                putExtra("statusPPDetail",dataSet[position].status)
            }
            viewHolder.itemView.context.startActivity(myIntent)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount()=dataSet.size

}

