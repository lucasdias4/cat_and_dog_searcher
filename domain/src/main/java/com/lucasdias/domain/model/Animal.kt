package com.lucasdias.domain.model

import android.os.Parcelable
import com.lucasdias.domain.enum.AnimalType
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Animal(
    val id: String,
    val name: String,
    val temperament: String,
    val imageUrl: String,
    val wikipediaUrl: String?,
    val type: AnimalType
) : Parcelable
