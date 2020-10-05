package com.example.jci.util

internal object DistanceCalculator {

    fun distance(
        lat1: Double,
        lat2: Double, lon1: Double,
        lon2: Double
    ): Double {
        var lat1 = lat1
        var lat2 = lat2
        var lon1 = lon1
        var lon2 = lon2

        lon1 = Math.toRadians(lon1)
        lon2 = Math.toRadians(lon2)
        lat1 = Math.toRadians(lat1)
        lat2 = Math.toRadians(lat2)

        val dlon = lon2 - lon1
        val dlat = lat2 - lat1
        val a = Math.pow(Math.sin(dlat / 2), 2.0) + (Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2.0))

        val c = 2 * Math.asin(Math.sqrt(a))

        val r = 6371.0

        return r * c
    }

}
