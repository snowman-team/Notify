Snowball Android Notification Library <br> [ ![Download](https://api.bintray.com/packages/aquarids/maven/notify/images/download.svg?version=0.1.0) ](https://bintray.com/aquarids/maven/notify/0.1.0/link)
============

Make it easier to use notification with different styles in different Android versions.

## Installation

```groovy
dependencies {
    // add dependency, please replace x.y.z to the latest version
    implementation "com.xueqiu.notify:notify:x.y.z"
}
```

## Usage

Firstly, initialize NotifyManager in the suitable place.
```kotlin
 val channel = NotifyChannel(channelId, channelName)
        .withDesc(channelDesc)
        .withVibrate(false)
        .withLights(true)
        .withSound(true)
        .withPriority(NotifyChannel.PRIORITY_HIGH)
val options = NotifyOptions().withChannel(channel)

NotifyManager.init(this, options)
```

Create the notify builder and use it.
```kotlin

val builder = DefaultNotifyBuilder(notifyId, channelId)
        .withTitle(title)
        .withContent(content)
        .withSmallIcon(iconRes)
        .withContentAction(pendingIntent)

NotifyManager.notifyMessage(this, builder)
```

You can choose one of these builders.

- DefaultNotifyBuilder
- BigImageNotifyBuilder
- LargeTextNotifyBuilder
- ProgressNotifyBuilder
- CustomNotifyBuilder