package com.axellinoanggoro.binar_e_commerce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityMainBinding
import com.axellinoanggoro.binar_e_commerce.view.ui.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        Handler().postDelayed({
            val intent = Intent(this@MainActivity,HomeActivity::class.java)
            startActivity(intent)
            finish()
        },3000)
    }
}