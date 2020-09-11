package com.virtualsoft.core.designpatterns.observer

interface IObserver<T : INotification> {

    var notification: T?

    fun update(notification: T) {
        this.notification = notification
    }
}