package com.axellinoanggoro.binar_e_commerce.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.axellinoanggoro.binar_e_commerce.databinding.ItemCartBinding
import com.axellinoanggoro.binar_e_commerce.databinding.ItemSliderBinding
import com.axellinoanggoro.binar_e_commerce.model.DataProduct
import com.axellinoanggoro.binar_e_commerce.model.GetCartItem
import com.bumptech.glide.Glide

class CartAdapter(private val listCart : List<GetCartItem>, private val listener : OnItemClickListener) : RecyclerView.Adapter<CartAdapter.ViewHolder>(){
    interface OnItemClickListener{
        fun onItemClick(data : DataProduct)
        fun onDeleteItemClick(cartId : String)
    }

    inner class ViewHolder(var binding: ItemCartBinding) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var cartId: String
        fun bindCart(itemCart : GetCartItem){
            with(itemView){
                binding.apply {
                    binding.nameCart.text = itemCart.name
                    binding.priceCart.text = itemCart.price
                    binding.dscCart.text = itemCart.description
                    Glide.with(itemView).load(itemCart.productImage).into(binding.imgCart)
                    binding.deleteCart.setOnClickListener{
                        listener.onDeleteItemClick(cartId)
                    }
                }
            }
        }
        fun setCartId(cartId: String) {
            this.cartId = cartId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val view = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.bindCart(listCart[position])
        holder.itemView.setOnClickListener {
            val image = listCart[position].productImage
            val name = listCart[position].name
            val price = listCart[position].price
            val detailCart = DataProduct(name, price, image)

            listener.onItemClick(detailCart)
        }
        holder.setCartId(listCart[position].idCart)
    }

    override fun getItemCount(): Int {
        return listCart.size
    }
}