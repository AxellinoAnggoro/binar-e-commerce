package com.axellinoanggoro.binar_e_commerce.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.axellinoanggoro.binar_e_commerce.model.GetProductsItem
import com.axellinoanggoro.binar_e_commerce.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val api : ApiService): ViewModel() {

    val _detailProduct : MutableLiveData<GetProductsItem> = MutableLiveData()

    fun detailProduct() = viewModelScope.launch {
        val response = api.getProductById(1,1)
        try {
            _detailProduct.postValue(response)
        }catch (e:Exception){
            Log.e("Error",e.message!!)
        }
    }
}