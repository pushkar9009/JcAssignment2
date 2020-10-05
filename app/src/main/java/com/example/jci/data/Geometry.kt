package com.example.jci.data

import com.squareup.moshi.Json

data class Geometry(@Json(name = "viewport")
                    val viewport: Viewport,
                    @Json(name = "location")
                    val location: Location)