package com.xueqiu.notify

class NotifyOptions : INotifyOptions {

    var channels: MutableList<NotifyChannel> = ArrayList()

    override fun withChannel(notifyChannel: NotifyChannel): NotifyOptions {
        var hasItem = false
        channels.forEach {
            if (it.id == notifyChannel.id) {
                hasItem = true
                return@forEach
            }
        }
        if (!hasItem) {
            channels.add(notifyChannel)
        }
        return this
    }
}