package com.example.jci.data

import com.squareup.moshi.Json

data class ResultsItem(@Json(name = "types")
                       val types: List<String>?,
                       @Json(name = "business_status")
                       val businessStatus: String = "",
                       @Json(name = "icon")
                       val icon: String = "",
                       @Json(name = "rating")
                       val rating: Double = 0.0,
                       @Json(name = "photos")
                       val photos: List<PhotosItem>?,
                       @Json(name = "reference")
                       val reference: String = "",
                       @Json(name = "user_ratings_total")
                       val userRatingsTotal: Int = 0,
                       @Json(name = "price_level")
                       val priceLevel: Int = 0,
                       @Json(name = "scope")
                       val scope: String = "",
                       @Json(name = "name")
                       val name: String = "",
                       @Json(name = "opening_hours")
                       val openingHours: OpeningHours,
                       @Json(name = "geometry")
                       val geometry: Geometry,
                       @Json(name = "vicinity")
                       val vicinity: String = "",
                       @Json(name = "plus_code")
                       val plusCode: PlusCode,
                       @Json(name = "place_id")
                       val placeId: String = "",
                       val distance: Double
                       )
