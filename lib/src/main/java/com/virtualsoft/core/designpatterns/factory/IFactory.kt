package com.virtualsoft.core.designpatterns.factory

interface IFactory<T: IProduct> {

    fun produce(): T
}