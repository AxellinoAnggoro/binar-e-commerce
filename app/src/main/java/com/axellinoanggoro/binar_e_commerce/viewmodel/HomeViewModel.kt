package com.axellinoanggoro.binar_e_commerce.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.axellinoanggoro.binar_e_commerce.model.GetSlidersItem
import com.axellinoanggoro.binar_e_commerce.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val api:ApiService): ViewModel() {
    var liveDataProduct : MutableLiveData<List<GetSlidersItem>> = MutableLiveData()


}