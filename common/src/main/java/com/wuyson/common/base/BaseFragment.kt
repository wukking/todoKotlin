package com.wuyson.common.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.wuyson.common.util.LogcatCompat

/**
 * fragment基类
 * @author Wuyson
 * @date 2020/12/15
 */
open class BaseFragment : Fragment() {
    private val TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogcatCompat.i(TAG, "onCreate")
    }


    override fun onDestroy() {
        super.onDestroy()
        LogcatCompat.i(TAG, "OnDestroy")
    }
}