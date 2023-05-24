package com.axellinoanggoro.binar_e_commerce.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataProduct(
    var product : String,
    var harga : String,
    var img : String
):Parcelable