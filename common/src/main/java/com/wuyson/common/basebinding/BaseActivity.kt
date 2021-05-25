package com.wuyson.common.basebinding

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.wuyson.common.helper.google.contentView

abstract class BaseActivity<T:ViewBinding> : AppCompatActivity() {
    val binding :T by contentView(setupContentViewRes())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {  }
        initView(savedInstanceState)
    }

    @LayoutRes
    abstract fun setupContentViewRes(): Int

    abstract fun initView(savedInstanceState: Bundle?)
}