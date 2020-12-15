package com.wuyson.common.util

import android.util.Log

private const val TAG = "LogcatCompat"

/**
 * 日志
 * @author Wuyson
 * @date 2020/12/15
 */
class LogcatCompat {

    companion object {
        @JvmField
        var isDebug: Boolean = true

        @JvmStatic
        fun i(message: String) {
            if (isDebug) {
                Log.i(TAG, message)
            }
        }

        @JvmStatic
        fun i(tag: String, message: String) {
            if (isDebug) {
                Log.i(tag, message)
            }
        }

        @JvmStatic
        fun e(message: String) {
            if (isDebug) {
                Log.e(TAG, message)
            }
        }

        @JvmStatic
        fun e(tag: String, message: String) {
            if (isDebug) {
                Log.e(tag, message)
            }
        }
    }
}