package com.wuyson.todokotlin

import com.didichuxing.doraemonkit.kit.AbstractKit
import com.wuyson.common.base.BaseApplication
import com.wuyson.dokit.DokitUtils
import com.wuyson.dokit.kit.EnvSwitchKit
import com.wuyson.todokotlin.constant.Property
import com.wuyson.todokotlin.property.AppConfigs
import com.wuyson.upush.UMengUtils

class App : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        initPush()
        initDokit()
    }

    private fun initPush() {
        UMengUtils.init(this, AppConfigs.UPUSH_APP_SECRET)
    }

    private fun initDokit() {
        val kits = mutableListOf<AbstractKit>()
        kits.add(EnvSwitchKit())
        DokitUtils.init(this, kits, Property.DoKIT_PRODUCT_ID)
    }

}