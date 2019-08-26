package com.xueqiu.notify.builder

import android.app.PendingIntent
import android.graphics.Bitmap
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

class LargeTextNotifyBuilder(id: Int, channelID: String) : BaseNotifyBuilder(id, channelID, TYPE_LARGE_TEXT), ILargeTextNotifyBuilder {

    var largeText: String? = null
        private set

    override fun withLargeText(text: String): LargeTextNotifyBuilder {
        largeText = text
        return this
    }

    override fun withSmallIcon(@DrawableRes smallIcon: Int): LargeTextNotifyBuilder {
        this.smallIcon = smallIcon
        return this
    }

    override fun withLargeIcon(largeIcon: Bitmap): LargeTextNotifyBuilder {
        this.largeIcon = largeIcon
        return this
    }

    override fun withTitle(title: String): LargeTextNotifyBuilder {
        this.title = title
        return this
    }

    override fun withContent(content: String): LargeTextNotifyBuilder {
        this.content = content
        return this
    }

    override fun withPrimaryColor(@ColorInt color: Int): LargeTextNotifyBuilder {
        this.primaryColor = color
        return this
    }

    override fun enableAutoCancel(boolean: Boolean): LargeTextNotifyBuilder {
        this.autoCancel = boolean
        return this
    }

    override fun withContentAction(action: PendingIntent): LargeTextNotifyBuilder {
        contentAction = action
        return this
    }

    override fun withExtraAction(icon: Int?, text: String?, action: PendingIntent): LargeTextNotifyBuilder {
        extraActionIcon = icon
        extraActionText = text
        extraActionIntent = action
        return this
    }


}