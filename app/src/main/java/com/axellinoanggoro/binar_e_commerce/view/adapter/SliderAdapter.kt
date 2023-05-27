package com.axellinoanggoro.binar_e_commerce.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_e_commerce.databinding.ItemNewsBinding
import com.axellinoanggoro.binar_e_commerce.databinding.ItemSliderBinding
import com.axellinoanggoro.binar_e_commerce.model.GetSlidersItem
import com.bumptech.glide.Glide

class SliderAdapter(val listSlider: List<GetSlidersItem>) : RecyclerView.Adapter<SliderAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemSliderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindSlider(itemSlider : GetSlidersItem){
            with(itemView){
                binding.apply {
                    Glide.with(itemView).load(itemSlider.image).into(binding.imgItem)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderAdapter.ViewHolder {
        val view = ItemSliderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderAdapter.ViewHolder, position: Int) {
        holder.bindSlider(listSlider[position])
    }

    override fun getItemCount(): Int {
        return listSlider.size
    }
}