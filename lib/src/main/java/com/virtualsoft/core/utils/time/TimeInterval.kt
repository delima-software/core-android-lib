package com.virtualsoft.core.utils.time

class TimeInterval(val start: HourMinute, val end: HourMinute) {

    fun calculateIntervals(duration: HourMinute): List<TimeInterval> {
        val list = mutableListOf<TimeInterval>()
        val durationBetween = HourMinute.getDurationBetween(start, end)
        if (durationBetween != null && durationBetween.isAfterOrEqualHourMinute(duration)) {
            var firstCopy = start.createCopy()
            var secondCopy = start.createCopy()
            while(firstCopy.isBeforeHourMinute(end)) {
                secondCopy.increment(duration)
                if (secondCopy.isBeforeOrEqualHourMinute(end)) {
                    val interval = TimeInterval(firstCopy, secondCopy)
                    list.add(interval)
                }
                firstCopy = secondCopy.createCopy()
                secondCopy = firstCopy.createCopy()
            }
        }
        return list
    }

    fun hasInterception(interval: TimeInterval): Boolean {
        val firstCondition = this.end.isAfterHourMinute(interval.start) &&
                interval.start.isAfterHourMinute(this.start)
        val secondCondition = interval.end.isAfterHourMinute(this.start) &&
                this.start.isAfterHourMinute(interval.start)
        val thirdCondition = this.start.isAfterOrEqualHourMinute(interval.start) &&
                this.end.isBeforeOrEqualHourMinute(interval.end)
        val fourthCondition = interval.start.isAfterOrEqualHourMinute(this.start) &&
                interval.end.isBeforeOrEqualHourMinute(this.end)
        return firstCondition || secondCondition || thirdCondition || fourthCondition
    }

    fun getDuration(): HourMinute? {
        return HourMinute.getDurationBetween(start, end)
    }
}