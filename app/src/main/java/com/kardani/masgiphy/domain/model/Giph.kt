package com.kardani.masgiphy.domain.model

data class Giph(
    val id: String,
    val url: String,
    val title: String,
    val previewUrl: String,
    val animatedUrl: String,
    val favorite: Boolean,
)