package com.axellinoanggoro.binar_e_commerce.view.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_e_commerce.R
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityFavoriteBinding
import com.axellinoanggoro.binar_e_commerce.model.DataProduct
import com.axellinoanggoro.binar_e_commerce.model.GetCartItem
import com.axellinoanggoro.binar_e_commerce.model.GetFavouriteItem
import com.axellinoanggoro.binar_e_commerce.view.adapter.CartAdapter
import com.axellinoanggoro.binar_e_commerce.view.adapter.FavAdapter
import com.axellinoanggoro.binar_e_commerce.viewmodel.CartViewModel
import com.axellinoanggoro.binar_e_commerce.viewmodel.FavViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity(), FavAdapter.OnItemClickListener {

    lateinit var binding : ActivityFavoriteBinding
    lateinit var pref : SharedPreferences
    lateinit var favVm : FavViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                R.id.home -> {
                    // Logika untuk item 3
                    startActivity(Intent(this,HomeActivity::class.java))
                    true
                }
                else -> false
            }
        }

        pref = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val userId = pref.getString("id", "").toString()

        favVm = ViewModelProvider(this)[FavViewModel::class.java]
        favVm.getFav(userId)
        favVm.liveDataFav.observe(this){
            binding.rvFav.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false)
            binding.rvFav.adapter = FavAdapter(it,this)
        }
    }

    override fun onItemClick(data: DataProduct) {
        TODO("Not yet implemented")
    }

    override fun onDeleteItemClick(favId: String) {
        val userId = pref.getString("id", "").toString()
        val deleteCall = favVm.deleteFav(userId, favId)
        deleteCall.enqueue(object : Callback<GetFavouriteItem> {
            override fun onResponse(
                call: Call<GetFavouriteItem>,
                response: Response<GetFavouriteItem>
            ) {
                if (response.isSuccessful){
                    val deletedFavItem = response.body()
                    if (deletedFavItem != null){
                        Toast.makeText(this@FavoriteActivity, "Fav deleted successfully", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@FavoriteActivity, "Failed to delete fav", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this@FavoriteActivity, "Failed to delete fav", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetFavouriteItem>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
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