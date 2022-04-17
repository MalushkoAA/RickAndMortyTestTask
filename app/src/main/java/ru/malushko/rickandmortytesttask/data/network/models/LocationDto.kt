package ru.malushko.rickandmortytesttask.data.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationDto(
    val name: String? = null,
    val url: String? = null
) : Parcelable
