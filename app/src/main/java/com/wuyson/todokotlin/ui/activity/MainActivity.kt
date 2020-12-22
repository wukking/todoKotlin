package com.wuyson.todokotlin.ui.activity

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.Person
import com.wuyson.common.base.BaseActivity
import com.wuyson.todokotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    lateinit var context:Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        init()
    }

    fun init(){
        tv_open_bubble.setOnClickListener {

        }

        tv_send_bubble.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                sendBubble()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sendBubble(){
        // Create bubble intent
        val target = Intent(context, BubbleActivity::class.java)
        val bubbleIntent = PendingIntent.getActivity(context, 0, target, 0 /* flags */)

        // Create bubble metadata
        val bubbleData = Notification.BubbleMetadata.Builder()
                .setDesiredHeight(600)
                .setIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                .setIntent(bubbleIntent)
                .build()

        // Create notification
        val chatBot = Person.Builder()
                .setBot(true)
                .setName("BubbleBot")
                .setImportant(true)
                .build()

        val builder = Notification.Builder(context, "CHANNEL_ID")
                .setContentIntent(bubbleIntent)
                .setSmallIcon(Icon.createWithResource(context, R.mipmap.ic_launcher))
                .setBubbleMetadata(bubbleData)
                .addPerson(chatBot.uri)

        builder.build()
        notifi
    }
}