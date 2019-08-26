package com.xueqiu.notify.builder

import android.app.PendingIntent
import android.graphics.Bitmap
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

class ProgressNotifyBuilder(id: Int, channelID: String) : BaseNotifyBuilder(id, channelID, TYPE_PROGRESS),
        IProgressNotifyBuilder {

    var progress = 0
        private set

    val maxProgress = 100

    var enableIndeterminate = false
        private set

    override fun withProgressPercent(percent: Float): ProgressNotifyBuilder {
        progress = (percent * maxProgress).toInt()
        return this
    }

    override fun enableIndeterminate(boolean: Boolean): ProgressNotifyBuilder {
        enableIndeterminate = boolean
        return this
    }

    override fun withSmallIcon(@DrawableRes smallIcon: Int): ProgressNotifyBuilder {
        this.smallIcon = smallIcon
        return this
    }

    override fun withLargeIcon(largeIcon: Bitmap): ProgressNotifyBuilder {
        this.largeIcon = largeIcon
        return this
    }

    override fun withTitle(title: String): ProgressNotifyBuilder {
        this.title = title
        return this
    }

    override fun withContent(content: String): ProgressNotifyBuilder {
        this.content = content
        return this
    }

    override fun withPrimaryColor(@ColorInt color: Int): ProgressNotifyBuilder {
        this.primaryColor = color
        return this
    }

    override fun enableAutoCancel(boolean: Boolean): ProgressNotifyBuilder {
        this.autoCancel = boolean
        return this
    }

    override fun withContentAction(action: PendingIntent): ProgressNotifyBuilder {
        contentAction = action
        return this
    }

    override fun withExtraAction(icon: Int?, text: String?, action: PendingIntent): ProgressNotifyBuilder {
        extraActionIcon = icon
        extraActionText = text
        extraActionIntent = action
        return this
    }

}