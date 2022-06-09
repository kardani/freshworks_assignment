package com.kardani.masgiphy.datasource.network.model

import com.kardani.masgiphy.domain.model.Giph
import com.picnic.masgiphy.datasource.network.model.Images

data class GiphNetwork(
    val id: String,
    val url: String,
    val title: String,
    val images: Images,
)

fun GiphNetwork.mapToDomain() : Giph {
    return Giph(
        id = this.id,
        url = this.url,
        title = this.title,
        previewUrl = images.preview.url,
        animatedUrl = images.animated.url,
        favorite = false,
     )
}

fun List<GiphNetwork>.mapToDomain() : List<Giph>  = this.map { it.mapToDomain() }