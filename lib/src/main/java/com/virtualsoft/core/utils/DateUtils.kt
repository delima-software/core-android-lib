package com.virtualsoft.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun setDateToMidNight(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun currentDate(): Date {
        return Calendar.getInstance().time
    }

    fun dateInstance(timeInMillis: Long): Date {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeInMillis
        return calendar.time
    }

    fun dateInstance(year: Int, month: Int, dayOfMonth: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        return calendar.time
    }

    fun timeInMillis(date: Date): Long {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.timeInMillis
    }

    fun isToday(date: Date): Boolean {
        val midNightToday =
            setDateToMidNight(
                currentDate()
            )
        val midNightDate =
            setDateToMidNight(date)
        return midNightDate.compareTo(midNightToday) == 0
    }

    fun isTomorrow(date: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, 1)
        val midNightTomorrow =
            setDateToMidNight(calendar.time)
        val midNightDate =
            setDateToMidNight(date)
        return midNightDate.compareTo(midNightTomorrow) == 0
    }

    fun isYesterday(date: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        val midNightYesterday =
            setDateToMidNight(calendar.time)
        val midNightDate =
            setDateToMidNight(date)
        return midNightDate.compareTo(midNightYesterday) == 0
    }

    fun isBeforeToday(date: Date): Boolean {
        val midNightDate =
            setDateToMidNight(date)
        val midNightToday =
            setDateToMidNight(
                currentDate()
            )
        return midNightDate < midNightToday
    }

    fun Date.isBeforeDate(date: Date): Boolean {
        val midNightThis =
            setDateToMidNight(this)
        val midNightDate =
            setDateToMidNight(date)
        return midNightThis < midNightDate
    }

    fun Date.isBeforeDateTime(date: Date): Boolean {
        return this.before(date)
    }

    fun dateText(date: Date, format: String): String {
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(date)
    }

    fun dateText(year: Int, month: Int, dayOfMonth: Int, format: String): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        val date = calendar.time
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        return dateFormat.format(date)
    }

    fun getLastDateOfMonth(): Date {
        val calendar = Calendar.getInstance()
        val lastDay = calendar.getActualMaximum(Calendar.DATE)
        calendar.set(Calendar.DATE, lastDay)
        return calendar.time
    }
}