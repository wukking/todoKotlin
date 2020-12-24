package com.wuyson.huaweiqrcode.util

import android.app.Activity
import com.huawei.hms.hmsscankit.ScanUtil
import com.huawei.hms.ml.scan.HmsScan
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions

class ScanUtils {
    companion object{
        @JvmStatic
        fun scanOptions(): HmsScanAnalyzerOptions {
            //“QRCODE_SCAN_TYPE”和“DATAMATRIX_SCAN_TYPE”表示只扫描QR和Data Matrix的码
            return HmsScanAnalyzerOptions.Creator()
                .setHmsScanTypes(HmsScan.QRCODE_SCAN_TYPE , HmsScan.DATAMATRIX_SCAN_TYPE)
                .create()
        }

        @JvmStatic
        fun startScan(activity: Activity,requestCode:Int,scanOptions:HmsScanAnalyzerOptions){
            ScanUtil.startScan(activity, requestCode, scanOptions)
        }

        @JvmStatic
        fun startScan(activity: Activity,requestCode:Int){
            ScanUtil.startScan(activity, requestCode, scanOptions())
        }
    }
}