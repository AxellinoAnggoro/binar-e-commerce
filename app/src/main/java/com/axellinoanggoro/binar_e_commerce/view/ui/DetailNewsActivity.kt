package com.axellinoanggoro.binar_e_commerce.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityDetailNewsBinding
import com.axellinoanggoro.binar_e_commerce.model.DataNews
import com.bumptech.glide.Glide

class DetailNewsActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<DataNews>("data_news")
        setDetail(data)

        binding.imageView.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
        }
    }

    private fun setDetail(data: DataNews?) {
        val img = data?.img
        val title = data?.title
        val dsc = data?.desc

        binding.setTitle.text = title
        binding.setContent.text = dsc
        Glide.with(this).load(img).into(binding.ivDetail)
    }
}