package com.wuyson.aidllib.pool.sub

import com.wuyson.aidllib.IOperator

class OperatorSubImpl : IOperator.Stub() {
    override fun addOperator(aInt: Int, bInt: Int): Int {
        return aInt+ bInt
    }
}