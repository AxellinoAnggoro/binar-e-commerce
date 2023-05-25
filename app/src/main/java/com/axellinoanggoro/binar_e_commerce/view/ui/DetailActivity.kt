package com.axellinoanggoro.binar_e_commerce.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.axellinoanggoro.binar_e_commerce.R
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityDetailBinding
import com.axellinoanggoro.binar_e_commerce.model.DataProduct
import com.axellinoanggoro.binar_e_commerce.view.adapter.HomeAdapter
import com.axellinoanggoro.binar_e_commerce.viewmodel.DetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<DataProduct>("data_product")
        setDetail(data)
    }

    private fun setDetail(data:DataProduct?){
        val product = data?.product
        val harga = data?.harga
        val img = data?.img

        binding.setProduct.text = product
        binding.setHarga.text = harga
        Glide.with(this).load(img).into(binding.ivDetail)
    }

}