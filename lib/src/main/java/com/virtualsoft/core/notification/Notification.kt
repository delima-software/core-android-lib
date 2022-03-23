package com.virtualsoft.core.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.virtualsoft.core.designpatterns.builder.IBuilder
import com.virtualsoft.core.utils.AppUtils.checkVersionCompatibility
import com.virtualsoft.core.utils.GeneratorUtils.generateUUID
import kotlin.random.Random

class Notification(override val notificationId: Int,
                   override val notificationChannel: NotificationChannel? = null) : INotification {

    private var notificationBuilder: NotificationCompat.Builder? = null

    class Builder(notificationId: Int = Random.nextInt(),
                  notificationChannel: NotificationChannel? = null) : IBuilder<INotification> {

        override val building = Notification(notificationId, notificationChannel)

        fun setSmallIcon(res: Int): Builder {
            building.notificationBuilder?.setSmallIcon(res)
            return this
        }

        fun setContentTitle(title: String): Builder {
            building.notificationBuilder?.setContentTitle(title)
            return this
        }

        fun setContentText(text: String): Builder {
            building.notificationBuilder?.setContentText(text)
            return this
        }

        fun setPriority(priority: Int): Builder {
            building.notificationBuilder?.priority = priority
            return this
        }

        fun setCategory(category: String): Builder {
            building.notificationBuilder?.setCategory(category)
            return this
        }

        fun setStyle(style: NotificationCompat.Style): Builder {
            building.notificationBuilder?.setStyle(style)
            return this
        }

        fun setAutoCancel(autoCancel: Boolean): Builder {
            building.notificationBuilder?.setAutoCancel(autoCancel)
            return this
        }

        fun setTimeoutAfter(duration: Long): Builder {
            building.notificationBuilder?.setTimeoutAfter(duration)
            return this
        }

        fun setContentIntent(pendingIntent: PendingIntent): Builder {
            building.notificationBuilder?.setContentIntent(pendingIntent)
            return this
        }

        fun initialize(context: Context): Builder {
            createNotificationChannel(context)
            building.notificationBuilder = NotificationCompat.Builder(context, building.notificationChannel?.channelId?: generateUUID())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
            return this
        }

        private fun createNotificationChannel(context: Context) {
            if (checkVersionCompatibility(Build.VERSION_CODES.O)) {
                building.notificationChannel?.let {
                    val channelId = it.channelId
                    val name = it.name
                    val description = it.description
                    val importance = it.importance
                    val channel = android.app.NotificationChannel(channelId, name, importance).apply {
                        this.description = description
                    }
                    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.createNotificationChannel(channel)
                }
            }
        }
    }

    override fun show(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            notificationBuilder?.build()?.let {
                notify(notificationId, it)
            }
        }
    }

    override fun remove(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            cancel(notificationId)
        }
    }

    override fun removeAll(context: Context) {
        with(NotificationManagerCompat.from(context)) {
            cancelAll()
        }
    }
}