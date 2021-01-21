package com.example.safecar.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Marcadores(
    val title: String,
    val lat: String,
    val long: String
) : Parcelable