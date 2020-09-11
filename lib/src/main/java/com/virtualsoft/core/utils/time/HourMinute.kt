package com.virtualsoft.core.utils.time

import java.util.*

class HourMinute(var hour: Int = 0, var minute: Int = 0) {

    companion object {

        private fun String.toTimeInt(): Int {
            if (this.first() == '0')
                this.drop(1)
            return this.toInt()
        }

        fun createHourMinute(hourMinute: String): HourMinute? {
            if (isHourMinuteFormatted(hourMinute)) {
                val hour = hourMinute.substring(0, 2).toIntOrNull()
                val minute = hourMinute.substring(3, 5).toIntOrNull()
                if (hour != null && minute != null)
                    return HourMinute(hour, minute)
            }
            return null
        }

        fun getCurrentHourMinute(): HourMinute {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            return HourMinute(hour, minute)
        }

        fun formatHourMinute(hour: Int, minute: Int): String {
            return HourMinute(hour, minute).toString()
        }

        fun isHourMinuteFormatted(hourMinute: String): Boolean {
            return hourMinute.length == 5 && hourMinute[2] == ':'
        }

        fun String.isEqualHourMinute(hourMinute: String): Boolean {
            if (!isHourMinuteFormatted(this) || !isHourMinuteFormatted(hourMinute))
                return false
            val firstHour = this.substring(0, 2).toTimeInt()
            val firstMinute = this.substring(3, 5).toTimeInt()
            val secondHour = hourMinute.substring(0, 2).toTimeInt()
            val secondMinute = hourMinute.substring(3, 5).toTimeInt()
            return (firstHour == secondHour) && (firstMinute == secondMinute)
        }

        fun String.isBeforeHourMinute(hourMinute: String): Boolean {
            if (!isHourMinuteFormatted(this) || !isHourMinuteFormatted(hourMinute))
                return false
            val firstHour = this.substring(0, 2).toTimeInt()
            val firstMinute = this.substring(3, 5).toTimeInt()
            val secondHour = hourMinute.substring(0, 2).toTimeInt()
            val secondMinute = hourMinute.substring(3, 5).toTimeInt()
            return (firstHour < secondHour) || (firstHour == secondHour && firstMinute < secondMinute)
        }

        fun String.isBeforeOrEqualHourMinute(hourMinute: String): Boolean {
            if (!isHourMinuteFormatted(this) || !isHourMinuteFormatted(hourMinute))
                return false
            val firstHour = this.substring(0, 2).toTimeInt()
            val firstMinute = this.substring(3, 5).toTimeInt()
            val secondHour = hourMinute.substring(0, 2).toTimeInt()
            val secondMinute = hourMinute.substring(3, 5).toTimeInt()
            return (firstHour < secondHour) || (firstHour == secondHour && firstMinute <= secondMinute)
        }

        fun String.isAfterHourMinute(hourMinute: String): Boolean {
            if (!isHourMinuteFormatted(this) || !isHourMinuteFormatted(hourMinute))
                return false
            val firstHour = this.substring(0, 2).toTimeInt()
            val firstMinute = this.substring(3, 5).toTimeInt()
            val secondHour = hourMinute.substring(0, 2).toTimeInt()
            val secondMinute = hourMinute.substring(3, 5).toTimeInt()
            return (firstHour > secondHour) || (firstHour == secondHour && firstMinute > secondMinute)
        }

        fun String.isAfterOrEqualHourMinute(hourMinute: String): Boolean {
            if (!isHourMinuteFormatted(this) || !isHourMinuteFormatted(hourMinute))
                return false
            val firstHour = this.substring(0, 2).toTimeInt()
            val firstMinute = this.substring(3, 5).toTimeInt()
            val secondHour = hourMinute.substring(0, 2).toTimeInt()
            val secondMinute = hourMinute.substring(3, 5).toTimeInt()
            return (firstHour > secondHour) || (firstHour == secondHour && firstMinute >= secondMinute)
        }

        fun String.isBetweenHourMinute(startHourMinute: String, endHourMinute: String): Boolean {
            return this.isAfterHourMinute(startHourMinute) && this.isBeforeHourMinute(endHourMinute)
        }

        fun String.isBetweenIncludedHourMinute(startHourMinute: String, endHourMinute: String): Boolean {
            return this.isAfterOrEqualHourMinute(startHourMinute) && this.isBeforeOrEqualHourMinute(endHourMinute)
        }

        fun getDurationBetween(start: HourMinute, end: HourMinute): HourMinute? {
            if (start.isAfterHourMinute(end))
                return null
            if (start.isEqualHourMinute(end))
                return HourMinute(0, 0)
            if (start.hour == end.hour)
                return HourMinute(0, end.minute - start.minute)
            if (start.minute > end.minute) {
                val hours = end.hour - start.hour - 1
                val minutes = (60 - start.minute) + (end.minute)
                return HourMinute(hours, minutes)
            }
            val hours = end.hour - start.hour
            val minutes = end.minute - start.minute
            return HourMinute(hours, minutes)
        }
    }

    override fun toString(): String {
        var formattedHour = hour.toString()
        var formattedMinute = minute.toString()
        if (hour < 10)
            formattedHour = "0$formattedHour"
        if (minute < 10)
            formattedMinute = "0$formattedMinute"
        return "$formattedHour:$formattedMinute"
    }

    fun createCopy(): HourMinute {
        return HourMinute(this.hour, this.minute)
    }

    fun isEqualHourMinute(hourMinute: HourMinute): Boolean {
        return (this.hour == hourMinute.hour) && (this.minute == hourMinute.minute)
    }

    fun isBeforeHourMinute(hourMinute: HourMinute): Boolean {
        return (this.hour < hourMinute.hour) || (this.hour == hourMinute.hour && this.minute < hourMinute.minute)
    }

    fun isBeforeOrEqualHourMinute(hourMinute: HourMinute): Boolean {
        return (this.hour < hourMinute.hour) || (this.hour == hourMinute.hour && this.minute <= hourMinute.minute)
    }

    fun isAfterHourMinute(hourMinute: HourMinute): Boolean {
        return (this.hour > hourMinute.hour) || (this.hour == hourMinute.hour && this.minute > hourMinute.minute)
    }

    fun isAfterOrEqualHourMinute(hourMinute: HourMinute): Boolean {
        return (this.hour > hourMinute.hour) || (this.hour == hourMinute.hour && this.minute >= hourMinute.minute)
    }

    fun isBetweenHourMinute(start: HourMinute, end: HourMinute): Boolean {
        return this.isAfterHourMinute(start) && this.isBeforeHourMinute(end)
    }

    fun isBetweenIncludedHourMinute(start: HourMinute, end: HourMinute): Boolean {
        return this.isAfterOrEqualHourMinute(start) && this.isBeforeOrEqualHourMinute(end)
    }

    fun increment(hourMinute: HourMinute) {
        var hours = this.hour + hourMinute.hour
        var minutes = this.minute + hourMinute.minute
        if (minutes >= 60) {
            hours += 1
            minutes -= 60
        }
        this.hour = hours
        this.minute = minutes
    }
}