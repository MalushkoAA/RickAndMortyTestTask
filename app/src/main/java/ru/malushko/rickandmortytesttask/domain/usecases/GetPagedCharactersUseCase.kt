package ru.malushko.rickandmortytesttask.domain.usecases

import ru.malushko.rickandmortytesttask.domain.Repository

class GetPagedCharactersUseCase(
    private val repo: Repository
) {
    operator fun invoke() = repo.getPagedCharacters()
}