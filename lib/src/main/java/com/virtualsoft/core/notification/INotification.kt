package com.virtualsoft.core.notification

import android.content.Context
import com.virtualsoft.core.designpatterns.builder.IBuild

interface INotification : IBuild {

    val context: Context
    val notificationId: Int
    val notificationChannel: NotificationChannel

    fun show()

    fun remove()

    fun removeAll()
}