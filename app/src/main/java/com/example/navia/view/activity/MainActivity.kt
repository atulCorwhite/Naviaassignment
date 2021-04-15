package com.example.navia.view.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject.view.baseview.BaseActivity
import com.example.navia.R
import com.example.navia.broadCast.AlarmBroadCast
import com.example.navia.data.remote.modal.HealthData
import com.example.navia.data.remote.modal.Monday
import com.example.navia.data.remote.modal.WeekDietData
import com.example.navia.view.adapter.WeekDietAdapter
import com.example.navia.viewModal.HealthDataViewModal
import java.util.*


class MainActivity : BaseActivity() {
    lateinit var recyclerView:RecyclerView
    lateinit var helthDataViewModal:HealthDataViewModal
    lateinit var adapter: WeekDietAdapter
    override fun initActivity() {
        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)
        helthDataViewModal = ViewModelProviders.of(this).get(HealthDataViewModal::class.java);
        helthDataViewModal.init()
        helthDataViewModal.getData()?.observe(this, Observer<HealthData> {  data->
            var mondayDataList =data.week_diet_data.monday
            var thursdayDataList =data.week_diet_data.thursday
            var wednesdayDataList =data.week_diet_data.wednesday

           // var weekDataList = mutableListOf<WeekDietData>()

            val arrayList =mutableListOf<Monday>()
            for (i in 0 until mondayDataList.size ){
                arrayList.add(Monday(data.week_diet_data.monday[i].food,data.week_diet_data.monday[i].meal_time,"Monday"))
            }
            for (i in 0 until thursdayDataList.size ){
                arrayList.add(Monday(data.week_diet_data.thursday[i].food,data.week_diet_data.thursday[i].meal_time,"Thursday"))
            }
            for (i in 0 until wednesdayDataList.size ){
                arrayList.add(Monday(data.week_diet_data.wednesday[i].food,data.week_diet_data.wednesday[i].meal_time,"Wednesday"))

            }
            if (!TextUtils.isEmpty(data.toString())){
                adapter = WeekDietAdapter(this,arrayList,object :WeekDietAdapter.ClickHandle{
                    override fun onItemClick(position: Int, weekDataList: MutableList<Monday>) {

                        val customCalendar = Calendar.getInstance()
                        var mealTime=weekDataList.get(position).meal_time
                        startAlertAtParticularTime(weekDataList.get(position))
                    }

                })
                recyclerView.adapter = adapter
            }


        })

    }

    private fun startAlertAtParticularTime(get: Monday) {
        var dateTime = get.meal_time.split(":")
        val hour = dateTime[0].toInt()
        val minute = dateTime[1].toInt()
        Log.e("houre",hour.toString()+"minut"+minute.toString())


        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = hour
        calendar[Calendar.MINUTE] =minute
        var fiveMinsBefore = calendar.timeInMillis - 300000

        intent = Intent(this, AlarmBroadCast::class.java)
        intent.putExtra("foodName",get.food)


        var pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        var  alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP, fiveMinsBefore,
            AlarmManager.INTERVAL_HOUR, pendingIntent
        )

        Toast.makeText(
            this, "Alarm will vibrate at time specified",
            Toast.LENGTH_SHORT
        ).show()

    }

    override fun getContectView(): Int {
        return R.layout.activity_main
    }
}