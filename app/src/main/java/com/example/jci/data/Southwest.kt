package com.example.jci.data

import com.squareup.moshi.Json

data class Southwest(@Json(name = "lng")
                     val lng: Double = 0.0,
                     @Json(name = "lat")
                     val lat: Double = 0.0)