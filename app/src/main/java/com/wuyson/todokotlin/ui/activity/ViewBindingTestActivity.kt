package com.wuyson.todokotlin.ui.activity

import android.os.Bundle
import androidx.core.content.ContextCompat
import com.wuyson.common.basebinding.BaseActivity
import com.wuyson.todokotlin.R
import com.wuyson.todokotlin.databinding.ActivityViewBindingTestBinding

class ViewBindingTestActivity : BaseActivity<ActivityViewBindingTestBinding>() {
    override fun setupContentViewRes(): Int = R.layout.activity_view_binding_test

    override fun initView(savedInstanceState: Bundle?) {
        binding.tvShow.setTextColor(ContextCompat.getColor(this,R.color.pink_100))
    }


}