package com.example.demoproject.view.baseview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract  class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContectView())
        initActivity()
    }

    abstract fun initActivity()

    abstract fun getContectView(): Int
}