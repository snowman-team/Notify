package com.xueqiu.notify.builder

import android.app.PendingIntent
import android.graphics.Bitmap
import android.widget.RemoteViews
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes

class CustomNotifyBuilder(id: Int, channelID: String) : BaseNotifyBuilder(id, channelID, TYPE_CUSTOM), ICustomNotifyBuilder {

    var customView: RemoteViews? = null
        private set

    var bigCustomView: RemoteViews? = null
        private set

    override fun withCustomView(remoteViews: RemoteViews): CustomNotifyBuilder {
        customView = remoteViews
        return this
    }

    override fun withBigCustomView(remoteViews: RemoteViews): CustomNotifyBuilder {
        bigCustomView = remoteViews
        return this
    }

    override fun withSmallIcon(@DrawableRes smallIcon: Int): CustomNotifyBuilder {
        this.smallIcon = smallIcon
        return this
    }

    override fun withLargeIcon(largeIcon: Bitmap): CustomNotifyBuilder {
        this.largeIcon = largeIcon
        return this
    }

    override fun withTitle(title: String): CustomNotifyBuilder {
        this.title = title
        return this
    }

    override fun withContent(content: String): CustomNotifyBuilder {
        this.content = content
        return this
    }

    override fun withPrimaryColor(@ColorInt color: Int): CustomNotifyBuilder {
        this.primaryColor = color
        return this
    }

    override fun enableAutoCancel(boolean: Boolean): CustomNotifyBuilder {
        this.autoCancel = boolean
        return this
    }

    override fun withContentAction(action: PendingIntent): CustomNotifyBuilder {
        contentAction = action
        return this
    }

    override fun withExtraAction(icon: Int?, text: String?, action: PendingIntent): CustomNotifyBuilder {
        extraActionIcon = icon
        extraActionText = text
        extraActionIntent = action
        return this
    }


}