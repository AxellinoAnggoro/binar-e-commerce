package com.axellinoanggoro.binar_e_commerce.view.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axellinoanggoro.binar_e_commerce.R
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding
    lateinit var pref : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val id = pref.getString("id","")

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    startActivity(Intent(this,HomeActivity::class.java))
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

    }
}