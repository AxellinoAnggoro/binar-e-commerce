package com.axellinoanggoro.binar_e_commerce.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axellinoanggoro.binar_e_commerce.model.DataUsers
import com.axellinoanggoro.binar_e_commerce.model.GetUsersItem
import com.axellinoanggoro.binar_e_commerce.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val api: ApiService) : ViewModel() {
    var postDataUsers: MutableLiveData<GetUsersItem> = MutableLiveData()

    fun postUsers(): MutableLiveData<GetUsersItem> {
        return postDataUsers
    }

    fun addDataUsers(name: String, email: String, password: String) {
        api.postNewUsers(DataUsers(name, password, email)).enqueue(object : Callback<GetUsersItem> {
            override fun onResponse(call: Call<GetUsersItem>, response: Response<GetUsersItem>) {
                if (response.isSuccessful) {
                    postDataUsers.postValue(response.body())
                } else {
                    postDataUsers.postValue(null)
                }
            }

            override fun onFailure(call: Call<GetUsersItem>, t: Throwable) {
                postDataUsers.postValue(null)
            }
        })
    }
}