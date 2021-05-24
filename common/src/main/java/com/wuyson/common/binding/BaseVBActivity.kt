package com.wuyson.common.binding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

open class BaseVBActivity<T:ViewBinding> : AppCompatActivity() {
    //这里暂时除了反射无解
    val binding:T? by contentView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding?.root)
    }

}