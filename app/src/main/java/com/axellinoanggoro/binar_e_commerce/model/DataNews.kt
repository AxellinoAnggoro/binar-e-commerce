package com.axellinoanggoro.binar_e_commerce.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataNews(
    val img : String,
    val title : String,
    val desc : String
): Parcelable
