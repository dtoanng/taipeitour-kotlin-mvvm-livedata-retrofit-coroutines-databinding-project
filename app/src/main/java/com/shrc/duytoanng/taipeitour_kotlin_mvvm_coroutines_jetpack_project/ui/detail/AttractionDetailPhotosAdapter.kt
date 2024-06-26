package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Image
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.databinding.ViewAttractionsImageItemBinding

class AttractionDetailPhotosAdapter(private var photos: List<Image>) :
    RecyclerView.Adapter<AttractionDetailPhotosAdapter.AttractionImageHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionImageHolder {
        val binding = ViewAttractionsImageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AttractionImageHolder(binding)
    }

    override fun getItemCount(): Int = photos.size

    override fun getItemId(position: Int) = position.toLong()

    override fun onBindViewHolder(holder: AttractionImageHolder, position: Int) {
        holder.bind(photos[position].src)
    }

    inner class AttractionImageHolder(private val binding: ViewAttractionsImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: String) {
            Glide.with(itemView)
                .load(photo)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .sizeMultiplier(0.6f)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.images)
        }
    }
}