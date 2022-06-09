package com.picnic.masgiphy.datasource.network.model

import com.google.gson.annotations.SerializedName

data class Images(

    @SerializedName("fixed_width_small_still")
    val preview: Gif,
    @SerializedName("downsized")
    val animated: Gif

)

data class Gif(
    val url: String
)