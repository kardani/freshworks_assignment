package com.kardani.masgiphy.datasource.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kardani.masgiphy.domain.model.Giph

@Entity(tableName = "giph_entity")
data class GiphEntity(
    @PrimaryKey val id: String,
    val url: String,
    val title: String,
    val previewUrl: String,
    val animatedUrl: String,
)

fun GiphEntity.mapToDomain() : Giph {
    return Giph(
        id = id,
        url = url,
        title = title,
        previewUrl = previewUrl,
        animatedUrl = animatedUrl,
        favorite = true
    )
}

fun List<GiphEntity>.mapToDomain() : List<Giph> = this.map { it.mapToDomain() }

fun Giph.mapToEntity() : GiphEntity{
    return GiphEntity(
        id = id,
        url = url,
        title = title,
        previewUrl = previewUrl,
        animatedUrl = animatedUrl,
    )
}

fun List<Giph>.mapToEntity() : List<GiphEntity> = this.map { it.mapToEntity() }