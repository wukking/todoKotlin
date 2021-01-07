package com.wuyson.common.base

import android.content.Context
import android.os.Bundle
import android.os.Message
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity基类
 * @author Wuyson
 * @date 2020/12/15
 */
abstract class BaseActivity : AppCompatActivity() {
    protected lateinit var mHandler: BaseHandler
    protected lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        doBeforeSetView()
        setContentView(layoutResId())
        doAfterSetContentView()

        initActivity(savedInstanceState)
    }

    open fun doBeforeSetView() {
        setStatusBar()
    }

    /**
     * 设置状态栏
     */
    open fun setStatusBar() {

    }

    open fun doAfterSetContentView() {
//        PushAgent.getInstance(context).onAppStart()

        if (handlerManager()) {
            mHandler = BaseHandler(this)
            mHandler.setHandlerCallback {
                onHandleMsg(it)
            }
        }
    }

    @LayoutRes
    abstract fun layoutResId(): Int

    abstract fun initActivity(savedInstanceState: Bundle?)

    /**
     * 是否开启BaseHandler来回调消息
     */
    open fun handlerManager():Boolean = true

    /**
     * handler消息回调
     */
    open fun onHandleMsg(msg:Message){

    }


}