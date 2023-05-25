package com.axellinoanggoro.binar_e_commerce.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetNewsUpdateItem(
    @SerializedName("content")
    val content: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id_news")
    val idNews: String,
    @SerializedName("news_image")
    val newsImage: String,
    @SerializedName("title")
    val title: String
):Parcelable