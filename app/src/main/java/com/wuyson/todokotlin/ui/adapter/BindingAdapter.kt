package com.wuyson.todokotlin.ui.adapter

import android.view.View
import android.widget.AdapterView
import androidx.databinding.BindingAdapter
import com.wuyson.todokotlin.entity.local.PageEntity

class BindingAdapter {

    @BindingAdapter("onItemClickListener")
    fun onItemClickListener(view:View,data: PageEntity){

    }
}