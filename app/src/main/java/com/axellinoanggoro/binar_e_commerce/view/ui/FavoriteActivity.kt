package com.axellinoanggoro.binar_e_commerce.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axellinoanggoro.binar_e_commerce.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
    }
}