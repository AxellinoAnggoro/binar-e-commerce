package com.axellinoanggoro.binar_e_commerce.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.axellinoanggoro.binar_e_commerce.R
import com.axellinoanggoro.binar_e_commerce.databinding.ActivityRegisterBinding
import com.axellinoanggoro.binar_e_commerce.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val name = binding.etNameRegister.text.toString()
            val email = binding.etEmailRegister.text.toString()
            val password = binding.etPasswordRegister.text.toString()
            addUsers(name, email, password)
        }
    }

    fun addUsers(name : String, email : String, password : String){
        val vm = ViewModelProvider(this).get(RegisterViewModel::class.java)
        vm.addDataUsers(name, email, password)
        vm.postUsers().observe(this){
            if (it != null){
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
            }
        }
    }
}