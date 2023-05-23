package com.axellinoanggoro.binar_e_commerce.network

import com.axellinoanggoro.binar_e_commerce.model.GetNewsUpdateItem
import com.axellinoanggoro.binar_e_commerce.model.GetSlidersItem
import com.axellinoanggoro.binar_e_commerce.model.GetUsersItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("news")
    fun getAllNews(): Call<List<GetNewsUpdateItem>>

    @GET("users")
    fun getAllUsers(): Call<List<GetUsersItem>>

    @GET("sliders/{id}")
    fun getSliderById(
        @Path("id") id : Int,
    ):Call<List<GetSlidersItem>>




}