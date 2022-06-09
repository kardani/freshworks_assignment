package com.kardani.masgiphy.ui.model

import androidx.recyclerview.widget.DiffUtil
import com.kardani.masgiphy.domain.model.Giph

data class GiphUI(
    val id: String,
    val url: String,
    val title: String,
    val previewUrl: String,
    val animatedUrl: String,
    val favorite: Boolean
){

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<GiphUI>()
            {
                override fun areItemsTheSame(oldItem: GiphUI, newItem: GiphUI): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: GiphUI, newItem: GiphUI): Boolean {
                    return oldItem == newItem
                }
            }

    }

}

fun Giph.mapToView() : GiphUI {
    return GiphUI(
        id = this.id,
        url = this.url,
        title = this.title,
        previewUrl = this.previewUrl,
        animatedUrl = this.animatedUrl,
        favorite = this.favorite,
    )
}

fun List<Giph>.mapToView() : List<GiphUI>  = this.map { it.mapToView() }