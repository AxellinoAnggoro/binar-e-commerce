package com.axellinoanggoro.binar_e_commerce.model


import com.google.gson.annotations.SerializedName

data class GetFavouriteItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id_fav")
    val idFav: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("product_image")
    val productImage: String,
    @SerializedName("userId")
    val userId: String
)