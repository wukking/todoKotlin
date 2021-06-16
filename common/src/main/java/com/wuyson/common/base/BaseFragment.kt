package com.wuyson.common.base

import android.content.Context
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import com.wuyson.common.compat.LogcatCompat

/**
 * fragment基类
 * @author Wuyson
 * @date 2020/12/15
 */
open class BaseFragment : Fragment() {
    private val TAG = javaClass.simpleName
    protected lateinit var mHandler: BaseHandler
    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogcatCompat.i(TAG, "onCreate")
        mContext = requireContext()
        if (handlerManager()) {
            mHandler = BaseHandler(requireActivity())
            mHandler.setHandlerCallback {
                onHandleMsg(it)
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        LogcatCompat.i(TAG, "OnDestroy")
    }

    /**
     * 是否开启BaseHandler来回调消息
     */
    open fun handlerManager():Boolean = true

    /**
     * handler消息回调
     */
    open fun onHandleMsg(msg: Message){

    }
}