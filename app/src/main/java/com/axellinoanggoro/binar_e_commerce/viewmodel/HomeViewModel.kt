package com.axellinoanggoro.binar_e_commerce.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axellinoanggoro.binar_e_commerce.model.GetNewsUpdate
import com.axellinoanggoro.binar_e_commerce.model.GetNewsUpdateItem
import com.axellinoanggoro.binar_e_commerce.model.GetProductsItem
import com.axellinoanggoro.binar_e_commerce.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val api: ApiService) : ViewModel() {
    val _dataProduct: MutableLiveData<List<GetProductsItem>> = MutableLiveData()
    val liveDataNews: MutableLiveData<List<GetNewsUpdateItem>> = MutableLiveData()


    fun getNews() {
        api.getAllNews().enqueue(object : Callback<List<GetNewsUpdateItem>> {
            override fun onResponse(
                call: Call<List<GetNewsUpdateItem>>,
                response: Response<List<GetNewsUpdateItem>>
            ) {
                if (response.isSuccessful) {
                    liveDataNews.postValue(response.body())
                } else {
                    liveDataNews.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<GetNewsUpdateItem>>, t: Throwable) {
                liveDataNews.postValue(null)
            }

        })
    }

    fun setProduct() = viewModelScope.launch {
        val response = api.getProduct(1)
        try {
            _dataProduct.postValue(response)
        } catch (e: Exception) {
            Log.e("error", e.message!!)
        }

    }


}