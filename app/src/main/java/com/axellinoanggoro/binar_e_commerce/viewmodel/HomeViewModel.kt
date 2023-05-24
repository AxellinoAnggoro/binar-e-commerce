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
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val api:ApiService): ViewModel() {
     val _dataProduct : MutableLiveData<List<GetProductsItem>> = MutableLiveData()




    fun setProduct() = viewModelScope.launch {
        val response = api.getProduct(1)
        try {
            _dataProduct.postValue(response)
        }catch(e: Exception) {
            Log.e("error", e.message!!)
        }

    }



}