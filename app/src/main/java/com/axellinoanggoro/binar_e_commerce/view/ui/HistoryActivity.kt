package com.axellinoanggoro.binar_e_commerce.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {
    lateinit var binding : ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}