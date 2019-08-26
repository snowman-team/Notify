package com.xueqiu.notify

import android.app.NotificationManager
import android.os.Build
import androidx.annotation.IntDef
import androidx.core.app.NotificationCompat

class NotifyChannel(val id: String, val name: String) : INotifyChannel {

    companion object {
        const val PRIORITY_MIN = 1
        const val PRIORITY_LOW = 2
        const val PRIORITY_DEFAULT = 3
        const val PRIORITY_HIGH = 4
        const val PRIORITY_MAX = 5
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(PRIORITY_MIN, PRIORITY_LOW, PRIORITY_DEFAULT, PRIORITY_HIGH, PRIORITY_MAX)
    annotation class Priority

    var desc: String? = null

    var priority: Int? = null

    var enableLights: Boolean = true

    var enableVibrate: Boolean = true

    var enableSound: Boolean = true

    override fun withDesc(desc: String): NotifyChannel {
        this.desc = desc
        return this
    }

    override fun withPriority(priority: Int): NotifyChannel {
        this.priority = priority
        return this
    }

    override fun withLights(boolean: Boolean): NotifyChannel {
        enableLights = boolean
        return this
    }

    override fun withVibrate(boolean: Boolean): NotifyChannel {
        enableVibrate = boolean
        return this
    }

    override fun withSound(boolean: Boolean): NotifyChannel {
        enableSound = boolean
        return this
    }

    // in case they are different :(
    internal fun getRealPriority(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            when (priority) {
                PRIORITY_MIN -> NotificationManager.IMPORTANCE_MIN
                PRIORITY_LOW -> NotificationManager.IMPORTANCE_LOW
                PRIORITY_DEFAULT -> NotificationManager.IMPORTANCE_DEFAULT
                PRIORITY_HIGH -> NotificationManager.IMPORTANCE_HIGH
                PRIORITY_MAX -> NotificationManager.IMPORTANCE_MAX
                else -> NotificationManager.IMPORTANCE_DEFAULT
            }
        } else {
            when (priority) {
                PRIORITY_MIN -> NotificationCompat.PRIORITY_MIN
                PRIORITY_LOW -> NotificationCompat.PRIORITY_LOW
                PRIORITY_DEFAULT -> NotificationCompat.PRIORITY_DEFAULT
                PRIORITY_HIGH -> NotificationCompat.PRIORITY_HIGH
                PRIORITY_MAX -> NotificationCompat.PRIORITY_MAX
                else -> NotificationCompat.PRIORITY_DEFAULT
            }
        }
    }

}