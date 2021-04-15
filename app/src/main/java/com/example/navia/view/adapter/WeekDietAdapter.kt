package com.example.navia.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.navia.R
import com.example.navia.data.remote.modal.Monday

class WeekDietAdapter(private val context: Context,var weekDataList: MutableList<Monday>,val myAdapterListener: WeekDietAdapter.ClickHandle): RecyclerView.Adapter<WeekDietAdapter.DataVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataVH {
        return DataVH(LayoutInflater.from(context).inflate(R.layout.week_diet_items, parent, false))
    }

    override fun getItemCount(): Int {
       return weekDataList.size
    }
    override fun onBindViewHolder(holder: DataVH, position: Int) {
        var day=weekDataList.get(position).dayName
        holder.weekDaytext.setText(day)
        holder.foodText.setText(weekDataList.get(position).food)
        holder.mealTimeText.setText(weekDataList.get(position).meal_time)

        holder.card_view.setOnClickListener {
            myAdapterListener.onItemClick(position,weekDataList)
        }
    }
    inner class DataVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
      lateinit var weekDaytext:TextView
      lateinit var foodText:TextView
      lateinit var mealTimeText:TextView
      lateinit var card_view:CardView
        init {
            weekDaytext=itemView.findViewById(R.id.weekDaytext)
            foodText=itemView.findViewById(R.id.foodText)
            mealTimeText=itemView.findViewById(R.id.mealTimeText)
            card_view=itemView.findViewById(R.id.card_view)
        }
    }

    interface ClickHandle {
        fun onItemClick(
            position: Int,
            weekDataList: MutableList<Monday>
        )

}}