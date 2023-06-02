package com.axellinoanggoro.binar_e_commerce.view.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_e_commerce.R
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityHomeBinding
import com.axellinoanggoro.binar_e_commerce.model.DataNews
import com.axellinoanggoro.binar_e_commerce.model.DataProduct
import com.axellinoanggoro.binar_e_commerce.view.adapter.HomeAdapter
import com.axellinoanggoro.binar_e_commerce.view.adapter.NewsAdapter
import com.axellinoanggoro.binar_e_commerce.view.adapter.SliderAdapter
import com.axellinoanggoro.binar_e_commerce.viewmodel.HomeViewModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), HomeAdapter.OnItemClickListener, NewsAdapter.OnItemClickListener {


    lateinit var binding: ActivityHomeBinding
    val imageList = ArrayList<SlideModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.account -> {
                    startActivity(Intent(this,ProfileActivity::class.java))
                    true
                }
                R.id.history -> {
                    // Logika untuk item 2
                    startActivity(Intent(this,HistoryActivity::class.java))
                    true
                }
                R.id.favorite -> {
                    // Logika untuk item 3
                    startActivity(Intent(this,FavoriteActivity::class.java))
                    true
                }
                else -> false
            }
        }

//        binding.bottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
//        imageList.add(SlideModel(R.drawable.test1))
//        imageList.add(SlideModel(R.drawable.test2))
//        imageList.add(SlideModel(R.drawable.test3))
//        binding.imgSlider.setImageList(imageList, ScaleTypes.FIT)

        val viewModelSlider = ViewModelProvider(this)[HomeViewModel::class.java]
        val viewModelProduct = ViewModelProvider(this)[HomeViewModel::class.java]
        val viewModelNews = ViewModelProvider(this)[HomeViewModel::class.java]

        viewModelSlider.getSliders()
        viewModelProduct.setProduct()
        viewModelNews.getNews()

        viewModelSlider.liveDataSlider.observe(this){
            if (it != null) {
                binding.imgSlider.adapter = SliderAdapter(it)
            }
        }

        viewModelProduct._dataProduct.observe(this) {
            if (it != null) {
                binding.rvProduct.layoutManager =
                    LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding.rvProduct.adapter = HomeAdapter(it, this@HomeActivity)
            }
        }
        viewModelNews.liveDataNews.observe(this){
            if (it != null){
                binding.rvNews.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
                binding.rvNews.adapter = NewsAdapter(it, this)
            }
        }

    }

    override fun onItemClick(data: DataProduct) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("data_product", data)
        startActivity(intent)
    }

    override fun onItemClick(data: DataNews) {
        val intent = Intent(this, DetailNewsActivity::class.java)
        intent.putExtra("data_news", data)
        startActivity(intent)
    }

//    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.home -> {
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.favorite -> {
//                val intent = Intent(this, FavoriteActivity::class.java)
//                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.cart -> {
//                val intent = Intent(this, CartActivity::class.java)
//                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.account ->{
//                val intent = Intent(this,ProfileActivity::class.java)
//                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }
}
