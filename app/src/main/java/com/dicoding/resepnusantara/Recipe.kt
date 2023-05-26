package com.dicoding.resepnusantara

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val title: String,
    val desc: String,
    val ingredients: String,
    val steps: String,
    val photo: Int
) : Parcelable
