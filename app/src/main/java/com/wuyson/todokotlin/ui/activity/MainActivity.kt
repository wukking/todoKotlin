package com.wuyson.todokotlin.ui.activity

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.huawei.hms.hmsscankit.ScanUtil
import com.huawei.hms.ml.scan.HmsScan
import com.wuyson.common.base.BaseActivity
import com.wuyson.huaweiqrcode.ui.MyQrScanActivity
import com.wuyson.huaweiqrcode.util.ScanUtils
import com.wuyson.todokotlin.R
import splitties.activities.start
import splitties.alertdialog.appcompat.*
import splitties.toast.toast

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

    fun init() {
//        ActivityCompat.requestPermissions(this, mPermission, CAMERA_REQ_CODE)

        alertDialog{
            title = "标题啊"
            message = "这是一个弹窗"
            R.drawable.ic_launcher_background
            setCancelable(false)
            okButton { start<ViewPager2Activity>() }
            cancelButton {  }
        }.onShow {
            toast("哈哈")
        }.show()
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
        startActivity(Intent(this@MainActivity, ViewPager2Activity::class.java))
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