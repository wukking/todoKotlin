package com.wuyson.common.binding_test

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KProperty

class ContentViewBindingDelegates<in R:AppCompatActivity, out T : ViewBinding>(){
    private var binding:T? = null

    operator fun getValue(activity:R, property: KProperty<*>): T {
        if (binding == null){
            //todo 这个是bug
            binding = activity.layoutInflater as T
        }
        return binding!!
    }

}

fun <T>ViewBinding.inflate(layoutInflater: LayoutInflater):T{
    return this.inflate(layoutInflater)
}

fun <R:AppCompatActivity,T : ViewBinding> contentView()
:ContentViewBindingDelegates<R,T> = ContentViewBindingDelegates()