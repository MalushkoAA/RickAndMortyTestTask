package ru.malushko.rickandmortytesttask.domain.usecases

import ru.malushko.rickandmortytesttask.domain.Repository

class GetCharacterDetailUseCase(
    private val repo: Repository
) {
    suspend operator fun invoke(characterId:Int) = repo.getCharacterDetail(characterId)
}