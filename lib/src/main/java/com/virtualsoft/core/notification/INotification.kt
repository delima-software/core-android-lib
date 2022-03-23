package com.virtualsoft.core.notification

import android.content.Context
import com.virtualsoft.core.designpatterns.builder.IBuild

interface INotification : IBuild {

    val notificationId: Int
    val notificationChannel: NotificationChannel?

    fun show(context: Context)

    fun remove(context: Context)

    fun removeAll(context: Context)
}