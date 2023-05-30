package com.axellinoanggoro.binar_e_commerce.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axellinoanggoro.binar_e_commerce.model.GetCartItem
import com.axellinoanggoro.binar_e_commerce.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val api: ApiService) : ViewModel() {
    val liveDataCart : MutableLiveData<List<GetCartItem>> = MutableLiveData()

    fun getCart(id : String){
        api.getCartById(id).enqueue(object : Callback<List<GetCartItem>>{
            override fun onResponse(
                call: Call<List<GetCartItem>>,
                response: Response<List<GetCartItem>>
            ) {
                if (response.isSuccessful){
                    liveDataCart.postValue(response.body())
                }else{
                    liveDataCart.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<GetCartItem>>, t: Throwable) {
                liveDataCart.postValue(null)
            }

        })
    }

    fun deleteCart(id: String, cartId : String): Call<GetCartItem> {
        return api.deleteCartById(id, cartId)
    }

}