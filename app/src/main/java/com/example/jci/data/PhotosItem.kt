package com.example.jci.data

import com.squareup.moshi.Json

data class PhotosItem(@Json(name = "photo_reference")
                      val photoReference: String = "",
                      @Json(name = "width")
                      val width: Int = 0,
                      @Json(name = "html_attributions")
                      val htmlAttributions: List<String>?,
                      @Json(name = "height")
                      val height: Int = 0)