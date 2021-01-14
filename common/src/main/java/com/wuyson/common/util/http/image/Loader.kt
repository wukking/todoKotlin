package com.wuyson.common.util.http.image

import android.widget.ImageView

interface Loader {

    fun prepare()

    fun load(path:String,view: ImageView)

    fun finish()
}