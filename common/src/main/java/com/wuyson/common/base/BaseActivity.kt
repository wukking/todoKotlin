package com.wuyson.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Activity基类
 * @author Wuyson
 * @date 2020/12/15
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


}