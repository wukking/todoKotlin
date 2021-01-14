package com.wuyson.common.util.http.image

import android.widget.ImageView

class ImageLoader {

    companion object{
        var imageLoader:Loader? = null

        fun init(loader:Loader){
            if (imageLoader == null){
                imageLoader = loader
            }
        }

        fun loadImage(path:String,view:ImageView){
            imageLoader?.load(path,view)
        }
    }
}