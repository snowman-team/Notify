package com.xueqiu.notify

interface INotifyChannel {

    fun withDesc(desc: String): INotifyChannel

    fun withPriority(priority: Int): INotifyChannel

    fun withLights(boolean: Boolean): INotifyChannel

    fun withVibrate(boolean: Boolean): INotifyChannel

    fun withSound(boolean: Boolean): INotifyChannel

}