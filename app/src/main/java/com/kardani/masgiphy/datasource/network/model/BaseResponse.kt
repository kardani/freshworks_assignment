package com.kardani.masgiphy.datasource.network.model

import com.google.gson.annotations.SerializedName

open class BaseResponse<V>{
    val data: V? = null
    val meta: Meta? = null
}

data class Meta(
    val msg: String,
    val status: Int,
    @SerializedName("response_id")
    val responseId: String,
)