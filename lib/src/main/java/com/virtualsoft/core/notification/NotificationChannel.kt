package com.virtualsoft.core.notification

data class NotificationChannel(val channelId: String,
                               val name: String,
                               val description: String,
                               val importance: Int)