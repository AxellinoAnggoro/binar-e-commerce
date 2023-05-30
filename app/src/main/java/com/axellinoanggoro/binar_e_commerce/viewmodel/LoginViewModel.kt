package com.axellinoanggoro.binar_e_commerce.viewmodel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axellinoanggoro.binar_e_commerce.model.GetUsersItem
import com.axellinoanggoro.binar_e_commerce.network.ApiService
import com.axellinoanggoro.binar_e_commerce.view.ui.HomeActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val api: ApiService, private val context: Context) : ViewModel() {
    val loginStatus: MutableLiveData<Boolean> = MutableLiveData()
    private lateinit var pref : SharedPreferences

    fun authenticateLogin(email: String, password: String) {
        api.getAllUsers().enqueue(object : Callback<List<GetUsersItem>> {
            override fun onResponse(
                call: Call<List<GetUsersItem>>,
                response: Response<List<GetUsersItem>>
            ) {
                if (response.isSuccessful) {
                    val getCreds = response.body()
                    val auth = getCreds?.find { it.email == email && it.password == password }
                    if (auth != null) {
                        storeLoginData(auth.idUsers, auth.name, auth.email, auth.password)
                        loginStatus.postValue(true)
                    } else {
                        loginStatus.postValue(false)
                    }
                }
            }

            override fun onFailure(call: Call<List<GetUsersItem>>, t: Throwable) {
                loginStatus.postValue(false)
            }

        })
    }

    private fun storeLoginData(id : String, name: String, email: String, password: String){
        pref = context.getSharedPreferences("login_data", Context.MODE_PRIVATE)
        val save = pref.edit()
        save.putString("id", id)
        save.putString("name", name)
        save.putString("email", email)
        save.putString("password", password)
        save.apply()
    }
}