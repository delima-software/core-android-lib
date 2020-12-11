package com.virtualsoft.core.utils

import android.content.Context
import com.virtualsoft.core.R
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val dateFormatString = "MMM d, yyyy"

    fun setDateToMidNight(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun setDateTime(date: Date, hour: Int? = null, minute: Int? = null, second: Int? = null, millisecond: Int? = null): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar.set(Calendar.HOUR_OF_DAY, hour?: 0)
        calendar.set(Calendar.MINUTE, minute?: 0)
        calendar.set(Calendar.SECOND, second?: 0)
        calendar.set(Calendar.MILLISECOND, millisecond?: 0)
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

    fun today(): Date {
        return setDateToMidNight(currentDate())
    }

    fun yesterday(): Date {
        val calendar = Calendar.getInstance()
        calendar.time = today()
        calendar.add(Calendar.DATE, -1)
        return setDateToMidNight(calendar.time)
    }

    fun tomorrow(): Date {
        val calendar = Calendar.getInstance()
        calendar.time = today()
        calendar.add(Calendar.DATE, 1)
        return setDateToMidNight(calendar.time)
    }

    fun Context.readableDateText(date: Date): String {
        if (isToday(date))
            return this.resources.getString(R.string.default_date_today)
        if (isTomorrow(date))
            return this.resources.getString(R.string.default_date_tomorrow)
        if (isYesterday(date))
            return this.resources.getString(R.string.default_date_yesterday)
        return dateText(date, dateFormatString)
    }

    fun Context.readableDateText(timeInMillis: Long): String {
        val date = dateInstance(timeInMillis)
        return this.readableDateText(date)
    }

    fun Context.readableDateText(year: Int, month: Int, dayOfMonth: Int): String {
        val date = dateInstance(year, month, dayOfMonth)
        return this.readableDateText(date)
    }

    fun Context.resolveDateString(dateString: String): String? {
        if (dateString == this.resources.getString(R.string.default_date_none))
            return null
        if (dateString == this.resources.getString(R.string.default_date_today))
            return dateText(today(), dateFormatString)
        if (dateString == this.resources.getString(R.string.default_date_yesterday))
            return dateText(yesterday(), dateFormatString)
        if (dateString == this.resources.getString(R.string.default_date_tomorrow))
            return dateText(tomorrow(), dateFormatString)
        return dateString
    }

    fun getCurrentDayOfWeek(): Int {
        val calendar = Calendar.getInstance()
        return calendar.get(Calendar.DAY_OF_WEEK)
    }

    fun getDayOfWeek(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar.get(Calendar.DAY_OF_WEEK)
    }

    fun Date.isSameDay(date: Date): Boolean {
        val calendar1 = Calendar.getInstance()
        val calendar2 = Calendar.getInstance()
        calendar1.time = this
        calendar2.time = date
        return calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
                && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
    }

    fun Date.getHourMinute(): String {
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        return dateFormat.format(this)
    }
}