package com.wuyson.todokotlin

import com.wuyson.common.base.BaseApplication
import com.wuyson.todokotlin.property.AppConfigs
import com.wuyson.upush.UMengUtils

class App :BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        initPush()
    }

    fun initPush(){
        UMengUtils.init(this,AppConfigs.UPUSH_APP_SECRET)

    }
}