package com.wuyson.upush

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.umeng.commonsdk.UMConfigure
import com.umeng.message.*
import com.umeng.message.entity.UMessage


class UMengUtils {

    companion object {
        /*@JvmField
        var messageHandler: UmengMessageHandler = object : UmengMessageHandler() {
            override fun getNotification(context: Context, msg: UMessage): Notification {
            *//*    for ((key1, value1) in msg.extra) {
                    val key = key1!!
                    val value = value1!!
                }*//*
                return when (msg.builder_id) {
                    1 -> {
                        val builder: Notification.Builder = Notification.Builder(context)
                        val myNotificationView = RemoteViews(
                            context.packageName,
                            R.layout.notification_view
                        )
                        myNotificationView.setTextViewText(R.id.notification_title, msg.title)
                        myNotificationView.setTextViewText(R.id.notification_text, msg.text)
                        myNotificationView.setImageViewBitmap(
                            R.id.notification_large_icon,
                            getLargeIcon(context, msg)
                        )
                        myNotificationView.setImageViewResource(
                            R.id.notification_small_icon,
                            getSmallIconId(context, msg)
                        )
                        builder.setContent(myNotificationView)
                            .setSmallIcon(getSmallIconId(context, msg))
                            .setTicker(msg.ticker)
                            .setAutoCancel(true)
                        builder.getNotification()
                    }
                    else ->                 //默认为0，若填写的builder_id并不存在，也使用默认。
                        super.getNotification(context, msg)
                }
            }
        }*/

        //启动Activity，需为Intent添加Flag：Intent.FLAG_ACTIVITY_NEW_TASK，否则无法启动Activity
        var notificationClickHandler: UmengNotificationClickHandler =
            object : UmengNotificationClickHandler() {
                override fun dealWithCustomAction(context: Context, msg: UMessage) {
                    //透传消息通过 msg.custom 获取
                    Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show()
                }

                override fun openUrl(context: Context?, msg: UMessage?) {
                    super.openUrl(context, msg)
                }

                override fun openActivity(context: Context?, msg: UMessage?) {
                    super.openActivity(context, msg)
                }

                override fun launchApp(context: Context?, msg: UMessage?) {
                    super.launchApp(context, msg)
                }
            }

        @JvmStatic
        fun init(context: Context, secret: String) {
            // 参数一：当前上下文context；
            // 参数二：应用申请的Appkey（需替换）；X
            // 参数三：渠道名称；X
            // 参数四：设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机；
            // 参数五：Push推送业务的secret 填充Umeng Message Secret对应信息（需替换）

            //设置LOG开关，默认为false
            UMConfigure.setLogEnabled(BuildConfig.DEBUG)
            UMConfigure.init(context, UMConfigure.DEVICE_TYPE_PHONE, secret)

            //注册推送服务，每次调用register方法都会回调该接口
            val mPushAgent = PushAgent.getInstance(context)
            //如果您的应用在前台，您可以设置不显示通知栏消息(register之前)
            mPushAgent.setNotificaitonOnForeground(false);
            //通知栏样式
//            mPushAgent.messageHandler = messageHandler
            //通知栏点击
            mPushAgent.notificationClickHandler = notificationClickHandler
            //0~10,为0时，表示不合并通知
            mPushAgent.displayNotificationNumber = 3
            mPushAgent.notificationChannelName = "消息中心"
            //服务端控制声音
            mPushAgent.notificationPlaySound = MsgConstant.NOTIFICATION_PLAY_SERVER
            //客户端允许呼吸灯点亮
            mPushAgent.notificationPlayLights = MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE
            //客户端禁止振动
            mPushAgent.notificationPlayVibrate = MsgConstant.NOTIFICATION_PLAY_SDK_DISABLE
            //通知免打扰模式
            mPushAgent.setNoDisturbMode(23, 0, 7, 0)
            //默认情况下，同一台设备在1分钟内收到同一个应用的多条通知时，
            // 不会重复提醒，同时在通知栏里新的通知会替换掉旧的通知。可以通过如下方法来设置冷却时间
            mPushAgent.muteDurationSeconds = 10

            mPushAgent.register(object : IUmengRegisterCallback {
                override fun onSuccess(deviceToken: String?) {
                    //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                    Log.i("UPUSH", "注册成功：deviceToken：-------->  " + deviceToken);
                }

                override fun onFailure(p0: String?, p1: String?) {
                    Log.e("UPUSH", "onFailure: " + p0 + "---" + p1)
                }
            })
        }

        fun closePush(context: Context){
            PushAgent.getInstance(context).disable(object : IUmengCallback {
                override fun onSuccess() {}
                override fun onFailure(s: String, s1: String) {}
            })
        }

        fun openPush(context: Context){
            PushAgent.getInstance(context).enable(object : IUmengCallback {
                override fun onSuccess() {}
                override fun onFailure(s: String, s1: String) {}
            })
        }
    }
}