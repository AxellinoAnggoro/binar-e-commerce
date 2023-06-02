package com.axellinoanggoro.binar_e_commerce.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_e_commerce.databinding.ItemCartBinding
import com.axellinoanggoro.binar_e_commerce.databinding.ItemHistoryBinding
import com.axellinoanggoro.binar_e_commerce.model.DataProduct
import com.axellinoanggoro.binar_e_commerce.model.GetTransHistoryItem
import com.bumptech.glide.Glide

class HistoryAdapter(private val listHistory : List<GetTransHistoryItem>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    class ViewHolder(var binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindHistory(itemHistory : GetTransHistoryItem){
            with(itemView){
                binding.apply {
                    binding.nameHistory.text = itemHistory.name
                    binding.amountHistory.text = itemHistory.amount.toString()
                    binding.priceHistory.text = itemHistory.price.toString()
                    binding.dscHistory.text = itemHistory.description
                    Glide.with(itemView).load(itemHistory.picture).into(binding.imgHistory)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.ViewHolder {
        val view = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryAdapter.ViewHolder, position: Int) {
        holder.bindHistory(listHistory[position])
    }

    override fun getItemCount(): Int {
        return listHistory.size
    }
}