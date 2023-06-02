package com.axellinoanggoro.binar_e_commerce.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axellinoanggoro.binar_e_commerce.model.GetFavouriteItem
import com.axellinoanggoro.binar_e_commerce.model.GetTransHistoryItem
import com.axellinoanggoro.binar_e_commerce.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class FavViewModel @Inject constructor(private val api : ApiService) :ViewModel()  {
    val liveDataFav : MutableLiveData<List<GetFavouriteItem>> = MutableLiveData()

    fun getFav(id : String){
        api.getFavById(id).enqueue(object : Callback<List<GetFavouriteItem>> {
            override fun onResponse(
                call: Call<List<GetFavouriteItem>>,
                response: Response<List<GetFavouriteItem>>
            ) {
                if (response.isSuccessful) {
                    liveDataFav.postValue(response.body())
                } else {
                    liveDataFav.postValue(null)
                }
            }

            override fun onFailure(call: Call<List<GetFavouriteItem>>, t: Throwable) {
                liveDataFav.postValue(null)
            }
        })
    }

    fun deleteFav(id: String, favId : String) : Call<GetFavouriteItem>{
        return api.deleteFavById(id, favId)
    }
}