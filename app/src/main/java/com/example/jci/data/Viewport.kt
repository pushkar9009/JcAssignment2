package com.example.jci.data

import com.squareup.moshi.Json

data class Viewport(@Json(name = "southwest")
                    val southwest: Southwest,
                    @Json(name = "northeast")
                    val northeast: Northeast)