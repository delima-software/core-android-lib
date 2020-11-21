package com.virtualsoft.core.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.virtualsoft.core.designpatterns.builder.IBuilder
import com.virtualsoft.core.utils.AppUtils.checkVersionCompatibility
import kotlin.random.Random

class Notification(override val context: Context,
                   override val notificationChannel: NotificationChannel,
                   override val notificationId: Int) : INotification {

    private var notificationBuilder = NotificationCompat.Builder(context, notificationChannel.channelId)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)

    class Builder(private val context: Context,
                  notificationChannel: NotificationChannel,
                  notificationId: Int = Random.nextInt()) : IBuilder<INotification> {

        override val building = Notification(context, notificationChannel, notificationId)

        init {
            createNotificationChannel()
        }

        fun setSmallIcon(res: Int): Builder {
            building.notificationBuilder.setSmallIcon(res)
            return this
        }

        fun setContentTitle(title: String): Builder {
            building.notificationBuilder.setContentTitle(title)
            return this
        }

        fun setContentText(text: String): Builder {
            building.notificationBuilder.setContentText(text)
            return this
        }

        fun setPriority(priority: Int): Builder {
            building.notificationBuilder.priority = priority
            return this
        }

        fun setCategory(category: String): Builder {
            building.notificationBuilder.setCategory(category)
            return this
        }

        fun setStyle(style: NotificationCompat.Style): Builder {
            building.notificationBuilder.setStyle(style)
            return this
        }

        fun setAutoCancel(autoCancel: Boolean): Builder {
            building.notificationBuilder.setAutoCancel(autoCancel)
            return this
        }

        fun setTimeoutAfter(duration: Long): Builder {
            building.notificationBuilder.setTimeoutAfter(duration)
            return this
        }

        fun setContentIntent(pendingIntent: PendingIntent): Builder {
            building.notificationBuilder.setContentIntent(pendingIntent)
            return this
        }

        private fun createNotificationChannel() {
            if (checkVersionCompatibility(Build.VERSION_CODES.O)) {
                val channelId = building.notificationChannel.channelId
                val name = building.notificationChannel.name
                val description = building.notificationChannel.description
                val importance = building.notificationChannel.importance
                val channel = android.app.NotificationChannel(channelId, name, importance).apply {
                    this.description = description
                }
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    override fun show() {
        with(NotificationManagerCompat.from(context)) {
            notify(notificationId, notificationBuilder.build())
        }
    }

    override fun remove() {
        with(NotificationManagerCompat.from(context)) {
            cancel(notificationId)
        }
    }

    override fun removeAll() {
        with(NotificationManagerCompat.from(context)) {
            cancelAll()
        }
    }
}