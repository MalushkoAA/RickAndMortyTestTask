package ru.malushko.rickandmortytesttask.data

import androidx.lifecycle.LiveData
import ru.malushko.rickandmortytesttask.data.network.models.ResultDto
import ru.malushko.rickandmortytesttask.domain.Character

class Mapper {

    fun mapDtoToEntity(dto: ResultDto) = Character(
        created = dto.created,
        episode = dto.episode,
        gender = dto.gender,
        id = dto.id,
        image = dto.image,
        location = dto.location,
        name = dto.name,
        species = dto.species,
        status = dto.status,
        type = dto.type,
        url = dto.url
    )

}