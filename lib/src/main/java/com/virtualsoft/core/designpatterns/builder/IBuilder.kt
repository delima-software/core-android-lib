package com.virtualsoft.core.designpatterns.builder

interface IBuilder<T: IBuild> {

    val building: T

    fun build(): T = building
}