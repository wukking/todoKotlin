package com.wuyson.todokotlin.ui.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.huawei.hms.hmsscankit.ScanUtil
import com.huawei.hms.ml.scan.HmsScan
import com.wuyson.common.base.BaseFragment
import com.wuyson.huaweiqrcode.ui.MyQrScanActivity
import com.wuyson.huaweiqrcode.util.ScanUtils
import com.wuyson.todokotlin.R
import com.wuyson.todokotlin.databinding.FragmentHomeBinding
import com.wuyson.todokotlin.entity.local.PageEntity
import com.wuyson.todokotlin.ui.activity.ViewPager2Activity
import com.wuyson.todokotlin.ui.adapter.ItemDecoration
import com.wuyson.todokotlin.ui.adapter.MainRecyclerAdapter
import com.wuyson.todokotlin.ui.adapter.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_home.*
import splitties.alertdialog.appcompat.*
import splitties.fragments.start
import splitties.toast.toast

/**
 * ViewBinding
 */
class HomeFragment : BaseFragment() {
    private var _binding:FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val mPermission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
    private val CAMERA_REQ_CODE = 100
    private val REQUEST_CODE_SCAN_ONE = 101

    private var mDatas:ArrayList<PageEntity> = arrayListOf();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initView()
    }

    override fun onHandleMsg(msg: Message) {
        when (msg.what) {
            1 -> {
                toast(msg.obj.toString())
            }
        }
    }
    private fun initView(){
        initRecyclerView()
    }

    private fun initRecyclerView(){
        mDatas.add(PageEntity(1,"Markdown"))
        mDatas.add(PageEntity(2,"ViewPager2"))
        mDatas.add(PageEntity(3,"打开扫码1"))
        mDatas.add(PageEntity(4,"打开扫码2"))
        mDatas.add(PageEntity(5,"气泡"))
        mDatas.add(PageEntity(6,"发送一条气泡"))
        mDatas.add(PageEntity(7,"发送一条延迟3S的Handler消息"))

        rv_content.apply {
            layoutManager = StaggeredGridLayoutManager(3, RecyclerView.VERTICAL)
            adapter = MainRecyclerAdapter(mDatas).apply {
                setOnItemClickListener(object :OnItemClickListener{
                    override fun onItemClick(entity: PageEntity) {
                        when(entity.id){
                            1 -> markDownClick()
                            2 -> viewPager2Click()
                            3 -> scanCode1Click()
                            4 -> scanCode2Click()
                            5 -> bubbleClick()
                            6 -> sendABubbleMessageClick()
                            7 -> sendADelay3SecondMessageClick()
                        }
                    }
                })
            }
            addItemDecoration(ItemDecoration(context))
        }
    }

    private fun sendADelay3SecondMessageClick() {
        val message = Message()
        message.what = 1
        message.obj = "HuLaLa"
        mHandler.sendMessageDelayed(message, 3000)
    }

    private fun sendABubbleMessageClick() {
        mContext.toast("sendABubbleMessageClick")
    }

    private fun bubbleClick() {
        mContext.toast("bubbleClick")
    }

    private fun scanCode2Click() {
        start<MyQrScanActivity>()
    }

    private fun scanCode1Click() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(mPermission, CAMERA_REQ_CODE)
        }
    }

    private fun viewPager2Click() {
        mContext.alertDialog {
            title = "提示"
            message = "确认打开ViewPager2实现的页面？"
            R.mipmap.ic_launcher
            setCancelable(false)
            okButton { start<ViewPager2Activity>() }
            cancelButton { }
        }.onShow {
            toast("展示弹窗")
        }.show()
    }

    private fun markDownClick() {
        mContext.toast("markDownClick")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
            ScanUtils.startScan(requireActivity(), REQUEST_CODE_SCAN_ONE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != AppCompatActivity.RESULT_OK || data == null) {
            return
        }

        if (requestCode == REQUEST_CODE_SCAN_ONE) {
            //导入图片扫描返回结果
            var obj = data.getParcelableExtra(ScanUtil.RESULT) as HmsScan?
            if (obj != null) {
                //展示解码结果
                Toast.makeText(mContext, obj.getOriginalValue(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun bionic() {
        val mainExecutor = mContext.mainExecutor
        val bionic: BiometricPrompt = BiometricPrompt(this, mainExecutor, object : BiometricPrompt.AuthenticationCallback() {
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
            .setAllowedAuthenticators(BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .setNegativeButtonText("Use account password")
            .setConfirmationRequired(true)
//        or DEVICE_CREDENTIAL
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .build()

        bionic.authenticate(promptInfo)
    }
}