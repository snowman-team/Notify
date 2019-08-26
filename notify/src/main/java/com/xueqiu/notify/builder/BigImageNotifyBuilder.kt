package com.xueqiu.notify.builder

import android.app.PendingIntent
import android.graphics.Bitmap
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes


class BigImageNotifyBuilder(id: Int, channelID: String) : BaseNotifyBuilder(id, channelID, TYPE_BIG_IMAGE), IBigImageNotifyBuilder {

    var bigImage: Bitmap? = null

    override fun withBigImage(bigImage: Bitmap): BigImageNotifyBuilder {
        this.bigImage = bigImage
        return this
    }

    override fun withSmallIcon(@DrawableRes smallIcon: Int): BigImageNotifyBuilder {
        this.smallIcon = smallIcon
        return this
    }

    override fun withLargeIcon(largeIcon: Bitmap): BigImageNotifyBuilder {
        this.largeIcon = largeIcon
        return this
    }

    override fun withTitle(title: String): BigImageNotifyBuilder {
        this.title = title
        return this
    }

    override fun withContent(content: String): BigImageNotifyBuilder {
        this.content = content
        return this
    }

    override fun withPrimaryColor(@ColorInt color: Int): BigImageNotifyBuilder {
        this.primaryColor = color
        return this
    }

    override fun enableAutoCancel(boolean: Boolean): BigImageNotifyBuilder {
        this.autoCancel = boolean
        return this
    }

    override fun withContentAction(action: PendingIntent): BigImageNotifyBuilder {
        contentAction = action
        return this
    }

    override fun withExtraAction(icon: Int?, text: String?, action: PendingIntent): BigImageNotifyBuilder {
        extraActionIcon = icon
        extraActionText = text
        extraActionIntent = action
        return this
    }


}