package com.virtualsoft.core.designpatterns.observer

interface IObservable<T : INotification> {

    val observerList: MutableList<IObserver<T>>

    fun <E : T> notify(notification: E) {
        observerList.forEach { observer ->
            observer.update(notification)
        }
    }
}