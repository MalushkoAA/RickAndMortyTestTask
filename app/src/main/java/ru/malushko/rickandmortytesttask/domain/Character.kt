package ru.malushko.rickandmortytesttask.domain

import ru.malushko.rickandmortytesttask.data.network.models.LocationDto

data class Character(
    val created: String? = null,
    val episode: List<String>? = null,
    val gender: String? = null,
    val id: Int? = null,
    val image: String? = null,
    val location: LocationDto? = null,
    val name: String? = null,
    val species: String? = null,
    val status: String? = null,
    val type: String? = null,
    val url: String? = null
)
