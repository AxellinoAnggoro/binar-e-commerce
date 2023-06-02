package com.axellinoanggoro.binar_e_commerce.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axellinoanggoro.binar_e_commerce.model.GetTransHistoryItem
import com.axellinoanggoro.binar_e_commerce.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val api: ApiService) : ViewModel() {
    val liveDataHistory : MutableLiveData<List<GetTransHistoryItem>> = MutableLiveData()

    fun getHistory(id : String){
        api.getHistoryById(id).enqueue(object : Callback<List<GetTransHistoryItem>>{
            override fun onResponse(
                call: Call<List<GetTransHistoryItem>>,
                response: Response<List<GetTransHistoryItem>>
            ) {
                if (response.isSuccessful) {
                    liveDataHistory.postValue(response.body())
                } else {
                    liveDataHistory.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<GetTransHistoryItem>>, t: Throwable) {
                liveDataHistory.postValue(null)
            }

        })
    }
}