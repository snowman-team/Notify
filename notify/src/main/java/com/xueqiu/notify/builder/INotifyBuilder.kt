package com.xueqiu.notify.builder

import android.app.PendingIntent
import android.graphics.Bitmap
import android.widget.RemoteViews

interface IBaseNotifyBuilder {

    fun withSmallIcon(smallIcon: Int): IBaseNotifyBuilder

    fun withLargeIcon(largeIcon: Bitmap): IBaseNotifyBuilder

    fun withTitle(title: String): IBaseNotifyBuilder

    fun withContent(content: String): IBaseNotifyBuilder

    fun withPrimaryColor(color: Int): IBaseNotifyBuilder

    fun enableAutoCancel(boolean: Boolean): IBaseNotifyBuilder

    fun withContentAction(action: PendingIntent): IBaseNotifyBuilder

    fun withExtraAction(icon: Int?, text: String?, action: PendingIntent): IBaseNotifyBuilder

}

interface IDefaultNotifyBuilder

interface ICustomNotifyBuilder {

    fun withCustomView(remoteViews: RemoteViews): ICustomNotifyBuilder

    fun withBigCustomView(remoteViews: RemoteViews): ICustomNotifyBuilder
}

interface ILargeTextNotifyBuilder {
    fun withLargeText(text: String): ILargeTextNotifyBuilder
}

interface IBigImageNotifyBuilder {

    fun withBigImage(bigImage: Bitmap): IBigImageNotifyBuilder
}

interface IProgressNotifyBuilder {

    fun enableIndeterminate(boolean: Boolean): IProgressNotifyBuilder

    fun withProgressPercent(percent: Float): IProgressNotifyBuilder

}