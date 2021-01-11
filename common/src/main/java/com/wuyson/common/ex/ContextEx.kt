package com.wuyson.common.ex

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import splitties.toast.toast

class ContextEx {

    /**
     * 复制文字到剪贴板
     * @param label 标识，没有就传空
     * @param data 复制的文本
     */
    fun Context.copy(label: String?, data: String?) {
        val cm: ClipboardManager =
            this.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val mClipData = ClipData.newPlainText(label, data)
        cm.setPrimaryClip(mClipData)
        toast("已经复制到粘贴板")
    }
}