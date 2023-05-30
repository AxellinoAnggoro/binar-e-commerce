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
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityCartBinding
import com.axellinoanggoro.binar_e_commerce.model.DataProduct
import com.axellinoanggoro.binar_e_commerce.model.GetCartItem
import com.axellinoanggoro.binar_e_commerce.view.adapter.CartAdapter
import com.axellinoanggoro.binar_e_commerce.viewmodel.CartViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class CartActivity : AppCompatActivity(), CartAdapter.OnItemClickListener {

    lateinit var binding : ActivityCartBinding
    lateinit var pref : SharedPreferences
    lateinit var cartVm : CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val userId = pref.getString("id", "").toString()

        cartVm = ViewModelProvider(this)[CartViewModel::class.java]
        cartVm.getCart(userId)
        cartVm.liveDataCart.observe(this){
            binding.rvCart.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
            binding.rvCart.adapter = CartAdapter(it,this)
        }
//        binding.bottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

    }

    override fun onItemClick(data: DataProduct) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("data_product", data)
        startActivity(intent)
    }

    override fun onDeleteItemClick(cartId: String) {
        val userId = pref.getString("id", "").toString()
        val deleteCall = cartVm.deleteCart(userId, cartId)
        deleteCall.enqueue(object : Callback<GetCartItem> {
            override fun onResponse(call: Call<GetCartItem>, response: Response<GetCartItem>) {
                if (response.isSuccessful) {
                    val deletedCartItem = response.body()
                    if (deletedCartItem != null) {
                        // Item deleted successfully, handle the response as needed
                        Toast.makeText(this@CartActivity, "Item deleted successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        // Response body is null, handle the error
                        Toast.makeText(this@CartActivity, "Failed to delete item", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // Failed to delete item, handle the error
                    Toast.makeText(this@CartActivity, "Failed to delete item", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<GetCartItem>, t: Throwable) {
                // Failed to make the API call, handle the error
                Toast.makeText(this@CartActivity, "Failed to delete item", Toast.LENGTH_SHORT).show()
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