package com.virtualsoft.core.utils

class ComparatorUtil<T>(private val function: (T?, T?) -> Int) : Comparator<T> {

    override fun compare(o1: T, o2: T): Int {
        return function(o1, o2)
    }
}