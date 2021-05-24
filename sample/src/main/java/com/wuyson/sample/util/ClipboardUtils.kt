package com.wuyson.sample.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent

class ClipboardUtils {

    companion object{
        @JvmStatic
        fun getInstance(context: Context):ClipboardManager{
            return context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        }

        fun copyText(context: Context,label:String,text:String){
            val newPlainText = ClipData.newPlainText(label, text)
            getInstance(context).setPrimaryClip(newPlainText)
        }

        fun copyIntent(context: Context,label: String,intent: Intent){
            getInstance(context).setPrimaryClip(ClipData.newIntent(label,intent))
        }

        fun copyUri(){
            throw Exception("参考文档：https://developer.android.com/guide/topics/text/copy-paste")
        }


    }
}