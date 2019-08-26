package com.xueqiu.notify.builder

import android.app.PendingIntent
import android.graphics.Bitmap
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef

abstract class BaseNotifyBuilder(val id: Int, val channelID: String, @NotifyType val type: Int) :
        IBaseNotifyBuilder {

    companion object {
        const val TYPE_CUSTOM = 0
        const val TYPE_DEFAULT = 1
        const val TYPE_LARGE_TEXT = 2
        const val TYPE_BIG_IMAGE = 3
        const val TYPE_PROGRESS = 4
    }

    @Retention(AnnotationRetention.SOURCE)
    @IntDef(TYPE_CUSTOM, TYPE_DEFAULT, TYPE_LARGE_TEXT, TYPE_BIG_IMAGE, TYPE_PROGRESS)
    annotation class NotifyType

    @DrawableRes
    var smallIcon: Int? = null
        protected set

    var largeIcon: Bitmap? = null
        protected set

    var title: String? = null
        protected set

    var content: String? = null
        protected set

    @ColorInt
    var primaryColor: Int? = null
        protected set

    var autoCancel: Boolean = true
        protected set

    var contentAction: PendingIntent? = null
        protected set

    @DrawableRes
    var extraActionIcon: Int? = null
        protected set

    var extraActionText: String? = null
        protected set

    var extraActionIntent: PendingIntent? = null
        protected set

}