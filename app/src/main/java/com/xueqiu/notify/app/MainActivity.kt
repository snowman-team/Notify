package com.xueqiu.notify.app

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xueqiu.notify.NotifyChannel
import com.xueqiu.notify.NotifyManager
import com.xueqiu.notify.NotifyOptions
import com.xueqiu.notify.builder.DefaultNotifyBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val channel = NotifyChannel("test", "test channel")
                .withDesc("just for test")
                .withVibrate(false)
                .withLights(true)
                .withSound(true)
                .withPriority(NotifyChannel.PRIORITY_HIGH)
        val options = NotifyOptions().withChannel(channel)

        NotifyManager.init(this, options)

        val notifyIntent = Intent(this, this::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val notifyPendingIntent = PendingIntent.getActivity(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = DefaultNotifyBuilder(1001, "test")
                .withContent("just a test notification")
                .withTitle("Notify")
                .withSmallIcon(R.mipmap.ic_launcher_round)
                .withContentAction(notifyPendingIntent)

        NotifyManager.notifyMessage(this, builder)
    }
}
