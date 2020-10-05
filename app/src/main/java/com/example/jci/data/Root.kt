package com.example.jci.data

import com.squareup.moshi.Json

data class Root(@Json(name = "next_page_token")
                val nextPageToken: String = "",
                @Json(name = "results")
                val results: List<ResultsItem>,
                @Json(name = "status")
                val status: String = "")