package com.wuyson.dokit.kit

import android.content.Context
import android.widget.Toast
import com.didichuxing.doraemonkit.kit.AbstractKit
import com.wuyson.dokit.R


class EnvSwitchKit: AbstractKit(){
    override val icon: Int
        get() = R.drawable.icon_env_switcher
    override val name: Int
        get() = R.string.env_switch

    override fun onAppInit(context: Context?) {

    }

    override fun onClick(context: Context?) {
        Toast.makeText(context,"环境切换点击",Toast.LENGTH_SHORT).show()
    }
}