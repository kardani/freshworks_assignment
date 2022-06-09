package com.kardani.masgiphy.ui.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kardani.masgiphy.databinding.GiphyListItemBinding

class GiphsAdapter constructor(private val clickListener: ClickListener?) : ListAdapter<GiphUI, GiphsAdapter.ViewHolder>(
    DiffCallBack()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position) ?: return

        holder.bind(item, clickListener)

    }

    class ViewHolder(private val binding: GiphyListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(model: GiphUI, clickListener: ClickListener?){

            Glide.with(this.itemView)
                .load(model.animatedUrl)
                .into(binding.gifImg)

            binding.favoriteImage.setOnClickListener {
                clickListener?.onFavoriteClicked(model)
            }

            binding.executePendingBindings()

        }

        companion object{

            fun from(parent: ViewGroup) : ViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = GiphyListItemBinding.inflate(inflater, parent, false)
                return ViewHolder(binding)
            }

        }

    }

    private class DiffCallBack : DiffUtil.ItemCallback<GiphUI>()
    {
        override fun areItemsTheSame(oldItem: GiphUI, newItem: GiphUI): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GiphUI, newItem: GiphUI): Boolean {
            return oldItem == newItem
        }
    }

    interface ClickListener{
        fun onFavoriteClicked(item: GiphUI)
    }
}