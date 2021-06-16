package com.wuyson.todokotlin.ui.activity

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wuyson.common.base.BaseActivity
import com.wuyson.common.base.BaseCompatActivity
import com.wuyson.todokotlin.R
import com.wuyson.todokotlin.databinding.ActivityMainBinding

/**
 *
 * todo 设备控制
 *
 * @author: Wuyson
 * @date: 2020/12/30
 */

class MainActivity : BaseCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun setRootView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun initActivity(savedInstanceState: Bundle?) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
//        val navController = findNavController(R.id.nav_host_fragment)
        binding.bnvTab.setupWithNavController(navController)
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