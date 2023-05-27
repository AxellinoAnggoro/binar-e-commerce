package com.axellinoanggoro.binar_e_commerce.network

import com.axellinoanggoro.binar_e_commerce.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("news_update")
    fun getAllNews(): Call<List<GetNewsUpdateItem>>

    @GET("users")
    fun getAllUsers(): Call<List<GetUsersItem>>

    @POST("users")
    fun postNewUsers(@Body request : DataUsers) : Call<GetUsersItem>

    @GET("sliders/{id}")
    fun getSliderById(
        @Path("id") id : Int,
    ):Call<List<GetSlidersItem>>

    @GET("sliders")
    fun getAllSliders() : Call<List<GetSlidersItem>>

    //Product
    @GET("category_product/{id}/products")
    suspend fun getProduct(@Path("id") id: Int): GetProducts

    @GET("category_product/{id}/products/{id_product}")
    suspend fun getProductById(
        @Path("id") id: Int,
        @Path("id_product") id_product: Int
    ): GetProductsItem




}