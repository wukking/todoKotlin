package com.wuyson.todokotlin.ui.activity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.wuyson.common.base.BaseActivity
import com.wuyson.todokotlin.R
import com.wuyson.todokotlin.databinding.ActivityMainBinding

/**
 *
 * todo 设备控制
 *
 * @author: Wuyson
 * @date: 2020/12/30
 */

class MainActivity : BaseActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun layoutResId(): Int = R.layout.activity_main

    override fun initActivity(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        init()
    }

    private fun init() {
//        var intent = Intent(this,MyControlService::class.java)
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            startForegroundService(intent)
//        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//            bionic()
//        }
/*        InAppMessageManager.getInstance(this).showCardMessage(this,"main",object :IUmengInAppMsgCloseCallback{
            override fun onClose() {
                toast("关闭插屏消息")
            }
        })*/
    }
}