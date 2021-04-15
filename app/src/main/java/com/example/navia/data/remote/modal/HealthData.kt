package com.example.navia.data.remote.modal

data class HealthData (var diet_duration:Int,var week_diet_data:WeekDietData)

class WeekDietData (var monday:List<Monday>,var wednesday:List<Wednesday>,var thursday:List<Thursday>)

class Monday (var food:String,var meal_time:String,var dayName:String)
class Wednesday(var food:String,var meal_time:String,var dayName:String)
class Thursday (var food:String,var meal_time:String,var dayName:String)





