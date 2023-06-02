package com.axellinoanggoro.binar_e_commerce.view.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_e_commerce.R
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityHistoryBinding
import com.axellinoanggoro.binar_e_commerce.view.adapter.HistoryAdapter
import com.axellinoanggoro.binar_e_commerce.viewmodel.HistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryActivity : AppCompatActivity() {
    lateinit var binding : ActivityHistoryBinding
    lateinit var pref : SharedPreferences
    lateinit var historyVm : HistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.account -> {
                    startActivity(Intent(this,ProfileActivity::class.java))
                    true
                }
                R.id.home -> {
                    // Logika untuk item 2
                    startActivity(Intent(this,HomeActivity::class.java))
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

        pref = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val userId = pref.getString("id", "").toString()

        historyVm = ViewModelProvider(this)[HistoryViewModel::class.java]
        historyVm.getHistory(userId)
        historyVm.liveDataHistory.observe(this){
            binding.historyRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            binding.historyRv.adapter = HistoryAdapter(it)
        }
    }
}