package com.xueqiu.notify

import android.content.Context
import com.xueqiu.notify.builder.BaseNotifyBuilder

interface INotifyManager {

    fun init(context: Context, options: NotifyOptions)

    fun notifyMessage(context: Context, notifyBuilder: BaseNotifyBuilder)

    fun getChannelByID(id: String): NotifyChannel?

    fun removeNotify(notifyID: Int)

}