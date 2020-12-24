package com.wuyson.huaweiqrcode.ui

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.huawei.hms.hmsscankit.OnResultCallback
import com.huawei.hms.hmsscankit.RemoteView
import com.huawei.hms.ml.scan.HmsScan
import com.wuyson.huaweiqrcode.R
import kotlinx.android.synthetic.main.activity_my_qr_scan.*

/**
 * 我的扫码页面
 *
 * @author: Wuyson
 * @date: 2020/12/24
 */

class MyQrScanActivity : AppCompatActivity() {
    private lateinit var frameLayout: FrameLayout
    private lateinit var remoteView: RemoteView
    private var mScreenWidth = 0
    private var mScreenHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_qr_scan)

        //绑定相机预览布局
        frameLayout = findViewById(R.id.rim)
        //设置扫码识别区域，您可以按照需求调整参数
        val dm = getResources().getDisplayMetrics()
        val density = dm.density
        mScreenWidth = getResources().getDisplayMetrics().widthPixels
        mScreenHeight = getResources().getDisplayMetrics().heightPixels
        //当前demo扫码框的宽高是300dp
        val SCAN_FRAME_SIZE = 300
        val scanFrameSize = (SCAN_FRAME_SIZE * density).toInt()
        val rect = Rect()
        rect.left = (mScreenWidth / 2 - scanFrameSize / 2).toInt()
        rect.right = (mScreenWidth / 2 + scanFrameSize / 2).toInt()
        rect.top = (mScreenHeight / 2 - scanFrameSize / 2).toInt()
        rect.bottom = (mScreenHeight / 2 + scanFrameSize / 2).toInt()
        //初始化RemoteView，并通过如下方法设置参数:setContext()（必选）传入context、setBoundingBox()设置扫描区域、setFormat()设置识别码制式，设置完毕调用build()方法完成创建。通过setContinuouslyScan（可选）方法设置非连续扫码模式。
        remoteView = RemoteView.Builder()
            .setContext(this)
            .setBoundingBox(rect)
            .setContinuouslyScan(false)//连续扫码模式
//            .enableReturnBitmap()设置扫码成功后返回原图,可以通过HmsScan对象的getOriginalBitmap获取结果
            .setFormat(HmsScan.QRCODE_SCAN_TYPE, HmsScan.DATAMATRIX_SCAN_TYPE)
            .build()
        //将自定义view加载到activity
        remoteView.onCreate(savedInstanceState)
        val params = FrameLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        frameLayout.addView(remoteView,params)

        //识别结果回调事件订阅
        remoteView.setOnResultCallback(OnResultCallback {
            //获取到扫码结果HmsScan
            Toast.makeText(this, it[0].getOriginalValue(), Toast.LENGTH_SHORT).show()
        })

        //光线暗时，会回调该接口，用于控制显示闪光灯开关
        remoteView.setOnLightVisibleCallback {
            if (it) {
                btn_flash?.setVisibility(View.VISIBLE)
            }
        }


        //remoteView.pauseContinuouslyScan();
        //remoteView.resumeContinuouslyScan()
    }

    override fun onStart() {
        super.onStart()
        //侦听activity的onStart
        remoteView.onStart()
    }

    override fun onResume() {
        super.onResume()
        //侦听activity的onResume
        remoteView.onResume()
    }

    override fun onPause() {
        super.onPause()
        //侦听activity的onPause
        remoteView.onPause()
    }

    override fun onStop() {
        super.onStop()
        //侦听activity的onStop
        remoteView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        //侦听activity的onDestroy
        remoteView.onDestroy()
    }
}