package com.wuyson.aidllib.pool.sub

import com.wuyson.aidllib.IEncrypt

class EncryptSubImpl : IEncrypt.Stub() {
    override fun encrypt(content: String?): String {
        return "encrypted $content"
    }
}