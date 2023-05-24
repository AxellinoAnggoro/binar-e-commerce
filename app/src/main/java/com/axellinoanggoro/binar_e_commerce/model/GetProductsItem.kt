package com.axellinoanggoro.binar_e_commerce.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetProductsItem(
    @SerializedName("category_productId")
    val categoryProductId: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id_product")
    val idProduct: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("product_image")
    val productImage: String
):Parcelable