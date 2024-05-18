package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attraction
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.databinding.AttractionItemBinding


class AttractionsAdapter(
    private var attractions: MutableList<Attraction>,
) : RecyclerView.Adapter<AttractionsAdapter.AttractionViewHolder>() {
    private var onItemClickLister: OnItemClickLister? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val attractionItemBinding =
            AttractionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttractionViewHolder(attractionItemBinding)
    }

    override fun getItemCount(): Int = attractions.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        with(holder) {
            bind(attractions[position])
            itemView.setOnClickListener {
                onItemClickLister?.onItemClick(attractions[position])
            }
        }
    }

    fun setOnClickListener(listener: OnItemClickLister) {
        this.onItemClickLister = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun loadMoreData(newAttractions: List<Attraction>) {
        attractions.addAll(newAttractions)
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        attractions.clear()
        notifyDataSetChanged()
    }

    inner class AttractionViewHolder(private val binding: AttractionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(attraction: Attraction) {
            binding.apply {
                tvAttractionName.text = attraction.name
                tvAttractionAddress.text = attraction.address
                if (attraction.images.isNotEmpty()) {
                    Glide.with(itemView)
                        .load(attraction.images.first().src)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .centerCrop()
                        .sizeMultiplier(0.5f)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(ivAttractionPicture)
                }
            }
        }
    }

    interface OnItemClickLister {
        fun onItemClick(attraction: Attraction)
    }
}