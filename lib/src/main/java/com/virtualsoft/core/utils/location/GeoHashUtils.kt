package com.virtualsoft.core.utils.location

import com.fonfon.kgeohash.GeoHash

object GeoHashUtils {

    fun getGeoHash(latitude: Double, longitude: Double): String {
        return GeoHash(latitude, longitude).toString()
    }

    fun getGeoHashRange(latitude: Double, longitude: Double, miles: Double): Pair<String, String> {
        val latitudeDegreesPerMile = 0.0144927536231884
        val longitudeDegreesPerMile = 0.0181818181818182

        val lowerLatitude = latitude - (latitudeDegreesPerMile * miles)
        val lowerLongitude = longitude - (longitudeDegreesPerMile * miles)

        val higherLatitude = latitude + (latitudeDegreesPerMile * miles)
        val higherLongitude = longitude + (longitudeDegreesPerMile * miles)

        val lowerHash = GeoHash(lowerLatitude, lowerLongitude).toString()
        val higherHash = GeoHash(higherLatitude, higherLongitude).toString()

        return Pair(lowerHash, higherHash)
    }
}