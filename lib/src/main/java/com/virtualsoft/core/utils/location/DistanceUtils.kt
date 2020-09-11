package com.virtualsoft.core.utils.location

import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.sin

object DistanceUtils {

    fun kmToMiles(km: Double): Double {
        return km * 0.621371
    }

    fun distanceInKm(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val theta = lon1 - lon2
        var dist = (sin(deg2rad(lat1))
                * sin(deg2rad(lat2))
                + (cos(deg2rad(lat1))
                * cos(deg2rad(lat2))
                * cos(deg2rad(theta))))
        dist = acos(dist)
        dist = rad2deg(dist)
        dist *= (60 * 1.1515)
        return BigDecimal(dist).setScale(2, RoundingMode.HALF_EVEN).toDouble()
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }
}