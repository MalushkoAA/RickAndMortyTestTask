package ru.malushko.rickandmortytesttask.data.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class InfoDto(
    val count: Int? = null,
    val next: String? = null,
    val pages: Int? = null,
    val prev: @RawValue Any? = null
) : Parcelable
