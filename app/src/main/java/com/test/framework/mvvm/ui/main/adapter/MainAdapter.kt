package com.test.framework.mvvm.ui.main.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.framework.mvvm.data.model.Photo
import com.test.framework.mvvm.databinding.ItemLayoutBinding
import com.test.framework.mvvm.utils.RecylerViewOnClick

class MainAdapter(private val onClick: RecylerViewOnClick) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(photo: Photo) {
            with(binding) {
                title.text = "TITLE: ${photo.id}"
                photoId.text = "ID: ${photo.id}"
                url.text = "URL: ${photo.url}"
                Picasso.get()
                    .load(photo.thumbnailUrl)
                    .into(imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val photo = differ.currentList[position]
        holder.bind(photo)
        holder.binding.container.setOnClickListener {
            onClick.onClick(differ.currentList[holder.bindingAdapterPosition])
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return (oldItem.id == newItem.id && oldItem.albumId == newItem.albumId && oldItem.title == newItem.title)
        }

    }
    val differ = AsyncListDiffer(this, differCallback)
}