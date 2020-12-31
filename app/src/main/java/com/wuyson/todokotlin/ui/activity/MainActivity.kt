package com.wuyson.todokotlin.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.service.controls.DeviceTypes
import android.service.controls.templates.ControlTemplate
import android.service.controls.templates.ToggleTemplate
import android.telephony.TelephonyManager
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import com.huawei.hms.hmsscankit.ScanUtil
import com.huawei.hms.ml.scan.HmsScan
import com.wuyson.common.base.BaseActivity
import com.wuyson.huaweiqrcode.ui.MyQrScanActivity
import com.wuyson.huaweiqrcode.util.ScanUtils
import com.wuyson.todokotlin.R
import com.wuyson.todokotlin.component.MyControlService
import splitties.activities.start
import splitties.alertdialog.appcompat.*
import splitties.toast.toast
/**
 *
 * todo 设备控制
 *
 * @author: Wuyson
 * @date: 2020/12/30
 */

class MainActivity : BaseActivity() {
    private lateinit var context: Context
    private val mPermission =
        arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
    private val CAMERA_REQ_CODE = 100
    private val REQUEST_CODE_SCAN_ONE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        init()
    }

    private fun init() {
//        var intent = Intent(this,MyControlService::class.java)
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            startForegroundService(intent)
//        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            bionic()
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun bionic(){
        val mainExecutor = context.mainExecutor
        val bionic:BiometricPrompt = BiometricPrompt(this,mainExecutor,object :BiometricPrompt.AuthenticationCallback(){
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                toast("验证错误")
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                toast("验证成功")
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                toast("验证失败")
            }
        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setAllowedAuthenticators(DEVICE_CREDENTIAL)
             .setNegativeButtonText("Use account password")
            .setConfirmationRequired(true)
//        or DEVICE_CREDENTIAL
            .setAllowedAuthenticators(BIOMETRIC_STRONG)
            .build()

        bionic.authenticate(promptInfo)
    }

    private fun is5G(){
//        TelephonyManager
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_REQ_CODE && grantResults.size == 2
            && grantResults[0] == PackageManager.PERMISSION_GRANTED
            && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
            //调用扫码接口，构建扫码能力，需您实现
            ScanUtils.startScan(this,REQUEST_CODE_SCAN_ONE)
        }
    }

    fun openViewPager2(view: View) {
        alertDialog{
            title = "提示"
            message = "确认打开ViewPager2实现的页面？"
            R.mipmap.ic_launcher
            setCancelable(false)
            okButton { start<ViewPager2Activity>() }
            cancelButton {  }
        }.onShow {
            toast("展示弹窗")
        }.show()
    }

    fun openHuaweiScan(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(mPermission,CAMERA_REQ_CODE)
        }
    }

    fun openHuaweiScanCustom(view: View) {
        start<MyQrScanActivity>()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK || data == null) {
            return
        }

        if (requestCode == REQUEST_CODE_SCAN_ONE) {
            //导入图片扫描返回结果
            var obj = data.getParcelableExtra(ScanUtil.RESULT) as HmsScan?
            if (obj != null) {
                //展示解码结果
                Toast.makeText(this, obj.getOriginalValue(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}