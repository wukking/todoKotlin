package com.wuyson.common.util

import android.content.Context
import android.content.pm.PackageManager

/**
 * 整个应用的工具类
 */
class AppUtils{
    companion object{
        /**
         * 获取meta-data标签的数据
         */
        @JvmStatic
        fun getMetaData(context:Context,key:String,defaultValue:String=""):String{
            val appInfo = context.packageManager.getApplicationInfo(
                context.packageName,
                PackageManager.GET_META_DATA
            )

            return  appInfo.metaData.getString(key,defaultValue)
        }
    }
}