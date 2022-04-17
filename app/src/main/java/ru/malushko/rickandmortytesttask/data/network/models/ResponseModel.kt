package ru.malushko.rickandmortytesttask

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.malushko.rickandmortytesttask.data.network.models.InfoDto
import ru.malushko.rickandmortytesttask.data.network.models.ResultDto

@Parcelize
data class ResponseModel(
    val info: InfoDto,
    val results: List<ResultDto>
) : Parcelable