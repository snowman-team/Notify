package com.xueqiu.notify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.xueqiu.notify.builder.*

object NotifyManager : INotifyManager {

    private val mNotifyChannels: MutableList<NotifyChannel> = ArrayList()
    private var mNotificationManager: NotificationManager? = null

    override fun init(context: Context, options: NotifyOptions) {
        mNotifyChannels.clear()
        mNotifyChannels.addAll(options.channels)

        mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotifyChannels.forEach {
            initChannel(it)
        }
    }

    private fun initChannel(channel: NotifyChannel) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mNotificationManager?.createNotificationChannel(
                    NotificationChannel(channel.id, channel.name, channel.getRealPriority()).apply {
                        description = channel.desc
                        enableLights(channel.enableLights)
                        enableVibration(channel.enableVibrate)
                        if (!channel.enableSound) {
                            setSound(null, null)
                        }
                    })
        }
    }

    override fun notifyMessage(context: Context, notifyBuilder: BaseNotifyBuilder) {
        if (null == mNotificationManager) throw IllegalStateException("Notify manager have not init")
        when (notifyBuilder.type) {
            BaseNotifyBuilder.TYPE_DEFAULT -> showDefaultNotification(context, notifyBuilder)
            BaseNotifyBuilder.TYPE_CUSTOM -> showCustomNotification(context, notifyBuilder)
            BaseNotifyBuilder.TYPE_BIG_IMAGE -> showBigImageNotification(context, notifyBuilder)
            BaseNotifyBuilder.TYPE_LARGE_TEXT -> showLargeTextNotification(context, notifyBuilder)
            BaseNotifyBuilder.TYPE_PROGRESS -> showProgressNotification(context, notifyBuilder)
        }
    }

    override fun getChannelByID(id: String): NotifyChannel? = mNotifyChannels.find { it.id == id }

    override fun removeNotify(notifyID: Int) {
        mNotificationManager?.cancel(notifyID)
    }

    private fun getBaseBuilder(context: Context, notifyBuilder: BaseNotifyBuilder): NotificationCompat.Builder {
        val channel = getChannelByID(notifyBuilder.channelID)
                ?: throw IllegalArgumentException("The channel of notify have not register")
        val notificationBuilder = NotificationCompat.Builder(context, channel.id)
        notifyBuilder.smallIcon?.let {
            notificationBuilder.setSmallIcon(it)
        }
        notifyBuilder.largeIcon?.let {
            notificationBuilder.setLargeIcon(it)
        }
        notifyBuilder.title?.let {
            notificationBuilder.setContentTitle(it)
        }
        notifyBuilder.content?.let {
            notificationBuilder.setContentText(it)
        }
        notifyBuilder.primaryColor?.let {
            notificationBuilder.setColor(it)
        }
        notifyBuilder.contentAction?.let {
            notificationBuilder.setContentIntent(it)
        }
        notificationBuilder.setAutoCancel(notifyBuilder.autoCancel)

        notifyBuilder.extraActionIntent?.let {
            notificationBuilder.addAction(notifyBuilder.extraActionIcon
                    ?: 0, notifyBuilder.extraActionText ?: "", it)
        }
        var defaults = 0
        if (channel.enableSound) {
            defaults = defaults or NotificationCompat.DEFAULT_SOUND
        }
        if (channel.enableVibrate) {
            defaults = defaults or NotificationCompat.DEFAULT_VIBRATE
        }
        if (channel.enableLights) {
            defaults = defaults or NotificationCompat.DEFAULT_LIGHTS
        }
        notificationBuilder.setDefaults(defaults)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            notificationBuilder.priority = channel.getRealPriority()
        }
        return notificationBuilder
    }

    private fun showDefaultNotification(context: Context, notifyBuilder: BaseNotifyBuilder) {
        val builder = if (notifyBuilder is DefaultNotifyBuilder) notifyBuilder else return
        val notification = getBaseBuilder(context, notifyBuilder).build()
        mNotificationManager?.notify(builder.id, notification)
    }

    private fun showCustomNotification(context: Context, notifyBuilder: BaseNotifyBuilder) {
        val builder = if (notifyBuilder is CustomNotifyBuilder) notifyBuilder else return
        val notificationBuilder = getBaseBuilder(context, notifyBuilder)
        builder.customView?.let {
            notificationBuilder.setCustomContentView(it)
        }
        builder.bigCustomView?.let {
            notificationBuilder.setCustomBigContentView(it)
        }
        val notification = notificationBuilder.build()
        mNotificationManager?.notify(builder.id, notification)
    }

    private fun showBigImageNotification(context: Context, notifyBuilder: BaseNotifyBuilder) {
        val builder = if (notifyBuilder is BigImageNotifyBuilder) notifyBuilder else return
        val notificationBuilder = getBaseBuilder(context, notifyBuilder)
        builder.bigImage?.let {
            notificationBuilder.setStyle(
                    NotificationCompat.BigPictureStyle()
                            .bigPicture(it)
                            .bigLargeIcon(null)
            )
        }
        val notification = notificationBuilder.build()
        mNotificationManager?.notify(builder.id, notification)
    }

    private fun showLargeTextNotification(context: Context, notifyBuilder: BaseNotifyBuilder) {
        val builder = if (notifyBuilder is LargeTextNotifyBuilder) notifyBuilder else return
        val notificationBuilder = getBaseBuilder(context, notifyBuilder)
        builder.largeText?.let {
            notificationBuilder.setStyle(
                    NotificationCompat.BigTextStyle()
                            .bigText(it)
            )
        }
        val notification = notificationBuilder.build()
        mNotificationManager?.notify(builder.id, notification)
    }

    private fun showProgressNotification(context: Context, notifyBuilder: BaseNotifyBuilder) {
        val builder = if (notifyBuilder is ProgressNotifyBuilder) notifyBuilder else return
        val notificationBuilder = getBaseBuilder(context, notifyBuilder)
        if (builder.enableIndeterminate) {
            notificationBuilder.setProgress(0, 0, true)
        } else {
            notificationBuilder.setProgress(builder.maxProgress, builder.progress, false)
        }
        val notification = notificationBuilder.build()
        mNotificationManager?.notify(builder.id, notification)
    }

}