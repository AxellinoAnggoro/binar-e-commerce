package com.axellinoanggoro.binar_e_commerce.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_e_commerce.databinding.ItemNewsBinding
import com.axellinoanggoro.binar_e_commerce.model.DataNews
import com.axellinoanggoro.binar_e_commerce.model.GetNewsUpdateItem
import com.axellinoanggoro.binar_e_commerce.view.ui.HomeActivity
import com.bumptech.glide.Glide

class NewsAdapter(
    private val listNews: List<GetNewsUpdateItem>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(data: DataNews)
    }

    inner class ViewHolder(var binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindNews(itemNews: GetNewsUpdateItem) {
            with(itemView) {
                binding.apply {
                    binding.newsTitle.text = itemNews.title
                    Glide.with(itemView).load(itemNews.newsImage).into(binding.newsPhoto)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.bindNews(listNews[position])
        holder.itemView.setOnClickListener {
            val image = listNews[position].newsImage
            val title = listNews[position].title
            val desc = listNews[position].content
            val detailNews = DataNews(image, title, desc)

            listener.onItemClick(detailNews)
        }
    }

    override fun getItemCount(): Int {
        return listNews.size
    }
}