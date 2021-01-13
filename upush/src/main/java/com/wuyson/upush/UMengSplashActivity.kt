package com.wuyson.upush

import com.umeng.message.inapp.InAppMessageManager
import com.umeng.message.inapp.UmengSplashMessageActivity


class UMengSplashActivity : UmengSplashMessageActivity() {
    override fun onCustomPretreatment(): Boolean {
        val mInAppMessageManager = InAppMessageManager.getInstance(this)
        //设置应用内消息为Debug模式
        mInAppMessageManager.setInAppMsgDebugMode(/*BuildConfig.DEBUG*/false)
        //参数为Activity的完整包路径，下面仅是示例代码，请按实际需求填写
        mInAppMessageManager.setMainActivityPath("com.wuyson.todokotlin.ui.activity.MainActivity")
        return super.onCustomPretreatment()
    }
}