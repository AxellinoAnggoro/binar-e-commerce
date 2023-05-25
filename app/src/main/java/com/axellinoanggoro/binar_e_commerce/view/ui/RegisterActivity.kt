package com.axellinoanggoro.binar_e_commerce.view.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
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
            val confPassword = binding.etRePasswordRegister.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmailRegister.error = "Invalid Email"
                binding.etEmailRegister.requestFocus()
                return@setOnClickListener
            } else if (name.isEmpty()) {
                binding.etNameRegister.error = "Name still empty"
                binding.etNameRegister.requestFocus()
                return@setOnClickListener
            } else if (password.isEmpty()) {
                binding.etPasswordRegister.error = "Password still empty"
                binding.etPasswordRegister.requestFocus()
                return@setOnClickListener
            } else if (password != confPassword) {
                binding.etRePasswordRegister.error = "Password doesn't match"
                binding.etRePasswordRegister.requestFocus()
            } else {
                addUsers(name, email, password)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }

        }
    }

    fun addUsers(name: String, email: String, password: String) {
        val vm = ViewModelProvider(this).get(RegisterViewModel::class.java)
        vm.addDataUsers(name, email, password)
        vm.postUsers().observe(this) {
            if (it != null) {
                Toast.makeText(this, "Register Success", Toast.LENGTH_SHORT).show()
            }
        }
    }
}