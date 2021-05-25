package com.wuyson.common.helper.google

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.reflect.KProperty

/**
 * 代理设置ContentView
 */
class ContentViewBindingDelegate<in R : AppCompatActivity, out T : ViewDataBinding>(
        @LayoutRes private val layoutRes: Int) {

    private var binding: T? = null

    operator fun getValue(activity: R, property: KProperty<*>): T {
        if (binding == null) {
            binding = DataBindingUtil.setContentView<T>(activity, layoutRes).apply {
                lifecycleOwner = activity
            }
        }
        return binding!!
    }
}

fun <R : AppCompatActivity, T : ViewDataBinding> contentView(
        @LayoutRes layoutRes: Int
): ContentViewBindingDelegate<R, T> = ContentViewBindingDelegate<R, T>(layoutRes)