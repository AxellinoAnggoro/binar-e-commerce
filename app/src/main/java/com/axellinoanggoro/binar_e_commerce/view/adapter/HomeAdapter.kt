package com.axellinoanggoro.binar_e_commerce.view.adapter

import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.axellinoanggoro.binar_e_commerce.databinding.ItemProductBinding
import com.axellinoanggoro.binar_e_commerce.model.DataProduct
import com.axellinoanggoro.binar_e_commerce.model.GetProductsItem
import com.axellinoanggoro.binar_e_commerce.view.ui.DetailActivity
import com.axellinoanggoro.binar_e_commerce.view.ui.HomeActivity
import com.bumptech.glide.Glide

class HomeAdapter(
    private val listProduct: List<GetProductsItem>,
    private val listener : OnItemClickListener
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(data: DataProduct)
    }

    inner class ViewHolder(var binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindProduct(itemProduct: GetProductsItem) {
            with(itemView) {
                binding.apply {
                    binding.name.text = itemProduct.name
                    Glide.with(itemView).load(itemProduct.productImage).into(binding.photo)

//                    cvProduct.setOnClickListener {
//                        val bundle = Bundle().apply {
//                            putParcelable("ID", itemProduct)
//                        }
//                        val intent = Intent(context, DetailActivity::class.java)
//                        intent.putExtras(bundle)
//                        context.startActivity(intent)
//                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.ViewHolder {
        val view = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listProduct.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindProduct(listProduct[position])
        holder.itemView.setOnClickListener{
            val image = listProduct[position].productImage
            val name = listProduct[position].name
            val price = listProduct[position].price
            val detailProduct = DataProduct(name, price, image)

            listener.onItemClick(detailProduct)
        }
    }


}