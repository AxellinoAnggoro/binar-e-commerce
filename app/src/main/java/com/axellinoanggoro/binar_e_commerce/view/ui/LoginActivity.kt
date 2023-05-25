package com.axellinoanggoro.binar_e_commerce.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.axellinoanggoro.binar_e_commerce.R
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityLoginBinding
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

        }
    }


}