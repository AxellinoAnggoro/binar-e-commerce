package com.axellinoanggoro.binar_e_commerce.view.ui

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityHistoryBinding
import com.axellinoanggoro.binar_e_commerce.view.adapter.HistoryAdapter
import com.axellinoanggoro.binar_e_commerce.viewmodel.HistoryViewModel

class HistoryActivity : AppCompatActivity() {
    lateinit var binding : ActivityHistoryBinding
    lateinit var pref : SharedPreferences
    lateinit var historyVm : HistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

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