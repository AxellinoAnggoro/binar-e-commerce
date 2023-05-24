package com.axellinoanggoro.binar_e_commerce.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_e_commerce.R
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityHomeBinding
import com.axellinoanggoro.binar_e_commerce.view.adapter.HomeAdapter
import com.axellinoanggoro.binar_e_commerce.viewmodel.HomeViewModel
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {


    lateinit var binding : ActivityHomeBinding
    val imageList = ArrayList<SlideModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.bottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        imageList.add(SlideModel(R.drawable.test1))
        imageList.add(SlideModel(R.drawable.test2))
        imageList.add(SlideModel(R.drawable.test3))
        binding.imgSlider.setImageList(imageList, ScaleTypes.FIT)

        val viewModelProduct = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModelProduct.setProduct()
        viewModelProduct._dataProduct.observe(this){
            if(it != null){
                binding.rvProduct.layoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
                binding.rvProduct.adapter = HomeAdapter(it,this@HomeActivity)
            }
        }


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