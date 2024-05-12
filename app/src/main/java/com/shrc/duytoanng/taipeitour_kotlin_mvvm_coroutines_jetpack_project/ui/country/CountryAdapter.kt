package com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.ui.country

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.data.model.Country
import com.shrc.duytoanng.taipeitour_kotlin_mvvm_coroutines_jetpack_project.databinding.ViewCountryItemBinding

class CountryAdapter(private val countries: List<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    private var onItemClickLister: OnItemClickLister? = null
    inner class CountryViewHolder(private val binding: ViewCountryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.tvCountry.text = country.name
            Glide.with(itemView).load(country.src).centerCrop().into(binding.ivCountry)
        }
    }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
       val binding = ViewCountryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        with(holder) {
            bind(countries[position])
            itemView.setOnClickListener {
                onItemClickLister?.onItemClick(countries[position])
            }
        }
    }

    fun setOnClickListener(listener: OnItemClickLister) {
        this.onItemClickLister = listener
    }

    interface OnItemClickLister {
        fun onItemClick(country: Country)
    }
}