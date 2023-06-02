package com.axellinoanggoro.binar_e_commerce.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_e_commerce.databinding.ItemCartBinding
import com.axellinoanggoro.binar_e_commerce.databinding.ItemFavoriteBinding
import com.axellinoanggoro.binar_e_commerce.model.DataProduct
import com.axellinoanggoro.binar_e_commerce.model.GetFavouriteItem
import com.bumptech.glide.Glide

class FavAdapter(private val listFav : List<GetFavouriteItem>, private val listener : OnItemClickListener) : RecyclerView.Adapter<FavAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(data : DataProduct)
        fun onDeleteItemClick(favId : String)
    }

    inner class ViewHolder(var binding : ItemFavoriteBinding) : RecyclerView.ViewHolder(binding.root){
        private lateinit var favId: String

        fun bindFav(itemFav : GetFavouriteItem){
            with(itemView){
                binding.apply {
                    binding.nameFav.text = itemFav.name
                    binding.dscFav.text = itemFav.description
                    binding.priceFav.text = itemFav.price.toString()
                    Glide.with(itemView).load(itemFav.productImage).into(binding.imgFav)
                }
            }
        }

        fun setFavId(favId: String) {
            this.favId = favId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavAdapter.ViewHolder {
        val view = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavAdapter.ViewHolder, position: Int) {
        holder.bindFav(listFav[position])
        holder.itemView.setOnClickListener {
            val image = listFav[position].productImage
            val name = listFav[position].name
            val price = listFav[position].price
            val detailFav = DataProduct(name, price.toString(), image)

            listener.onItemClick(detailFav)
        }
        holder.setFavId(listFav[position].idFav)
    }

    override fun getItemCount(): Int {
        return listFav.size
    }
}