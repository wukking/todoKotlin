package com.wuyson.todokotlin.ui.activity

import android.os.Bundle
import com.wuyson.common.base.BaseActivity
import com.wuyson.todokotlin.R

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}