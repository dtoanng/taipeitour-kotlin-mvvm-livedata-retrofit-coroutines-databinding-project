package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Attraction
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.databinding.AttractionItemBinding

class AttractionsAdapter(private var attractions: MutableList<Attraction>, private val context: Context?) :
    RecyclerView.Adapter<AttractionsAdapter.AttractionViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val attractionItemBinding = AttractionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AttractionViewHolder(attractionItemBinding)
    }

    override fun getItemCount(): Int = attractions.size

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        with(holder) {
            this.attractionName.text = attractions[position].name
            this.attractionAddress.text = attractions[position].address
            context ?: return
            if (attractions[position].images.isNotEmpty())
                Glide.with(context).load(attractions[position].images[0]).centerCrop().into(this.attractionPicture)
        }
    }

    inner class AttractionViewHolder(private val mBinding: AttractionItemBinding) : RecyclerView.ViewHolder(mBinding.root) {
        val attractionName = mBinding.tvAttractionName
        val attractionPicture = mBinding.ivAttractionPicture
        val attractionAddress = mBinding.tvAttractionAddress
    }
}