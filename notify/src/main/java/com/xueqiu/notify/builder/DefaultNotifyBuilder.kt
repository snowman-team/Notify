package com.xueqiu.notify.builder

import android.app.PendingIntent
import android.graphics.Bitmap
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

open class DefaultNotifyBuilder(id: Int, channelID: String) : BaseNotifyBuilder(id, channelID, TYPE_DEFAULT), IDefaultNotifyBuilder {

    override fun withSmallIcon(@DrawableRes smallIcon: Int): DefaultNotifyBuilder {
        this.smallIcon = smallIcon
        return this
    }

    override fun withLargeIcon(largeIcon: Bitmap): DefaultNotifyBuilder {
        this.largeIcon = largeIcon
        return this
    }

    override fun withTitle(title: String): DefaultNotifyBuilder {
        this.title = title
        return this
    }

    override fun withContent(content: String): DefaultNotifyBuilder {
        this.content = content
        return this
    }

    override fun withPrimaryColor(@ColorInt color: Int): DefaultNotifyBuilder {
        this.primaryColor = color
        return this
    }

    override fun enableAutoCancel(boolean: Boolean): DefaultNotifyBuilder {
        this.autoCancel = boolean
        return this
    }

    override fun withContentAction(action: PendingIntent): DefaultNotifyBuilder {
        contentAction = action
        return this
    }

    override fun withExtraAction(icon: Int?, text: String?, action: PendingIntent): DefaultNotifyBuilder {
        extraActionIcon = icon
        extraActionText = text
        extraActionIntent = action
        return this
    }

}