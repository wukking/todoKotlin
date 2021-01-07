package com.wuyson.common.base

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.os.Message
import java.lang.ref.WeakReference

class BaseHandler(val activity: Activity, looper: Looper) : Handler(looper) {

    constructor(activity: Activity) : this(activity, Looper.getMainLooper())

    private var mWeakActivity: WeakReference<Activity> = WeakReference(activity)
    private lateinit var mCallback: (msg: Message) -> Unit

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        val activity = mWeakActivity.get()
        activity?.let {
            mCallback.invoke(msg)
        }
    }

    internal fun setHandlerCallback(callback: (Message) -> Unit) {
        mCallback = callback
    }
}