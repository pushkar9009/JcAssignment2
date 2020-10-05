package com.example.jci.data

import com.squareup.moshi.Json

data class OpeningHours(@Json(name = "open_now")
                        val openNow: Boolean = false)