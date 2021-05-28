package com.wuyson.dokit

import android.app.Application
import com.didichuxing.doraemonkit.DoraemonKit
import com.didichuxing.doraemonkit.kit.AbstractKit

class DokitUtils {

    companion object{

        fun init(application: Application, listKits: MutableList<AbstractKit>, pId:String){
            DoraemonKit.install(application,listKits,pId)
        }
    }
}