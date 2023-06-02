package com.axellinoanggoro.binar_e_commerce.network

import com.axellinoanggoro.binar_e_commerce.model.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    //Home
    @GET("news_update")
    fun getAllNews(): Call<List<GetNewsUpdateItem>>
    @GET("sliders/{id}")
    fun getSliderById(
        @Path("id") id : Int,
    ):Call<List<GetSlidersItem>>
    @GET("sliders")
    fun getAllSliders() : Call<List<GetSlidersItem>>

    //Login
    @GET("users")
    fun getAllUsers(): Call<List<GetUsersItem>>

    //Register
    @POST("users")
    fun postNewUsers(@Body request : DataUsers) : Call<GetUsersItem>

    //Product
    @GET("category_product/{id}/products")
    suspend fun getProduct(@Path("id") id: Int): GetProducts
    @GET("category_product/{id}/products/{id_product}")
    suspend fun getProductById(
        @Path("id") id: Int,
        @Path("id_product") id_product: Int
    ): GetProductsItem

    //Cart
    @GET("users/{id}/cart")
    fun getCartById(
        @Path("id") id: String
    ) : Call<List<GetCartItem>>
    @POST("users/{id}/cart")
    fun postNewCart(@Body request : GetProductsItem) : Call<GetCartItem>
    @DELETE("users/{id}/cart/{cartId}")
    fun deleteCartById(
        @Path("id") id: String,
        @Path("cartId") cartId : String
    ) : Call<GetCartItem>


    //History
    @GET("users/{id}/transhistory")
    fun getHistoryById(
        @Path("id") id: String
    ) : Call<List<GetTransHistoryItem>>

    //Fav
    @GET("users/{id}/favourite")
    fun getFavById(
        @Path("id") id: String
    ) : Call<List<GetFavouriteItem>>
    @DELETE("users/{id}/favourite/{favId}")
    fun deleteFavById(
        @Path("id") id: String,
        @Path("favId") cartId : String
    ) : Call<GetFavouriteItem>
}