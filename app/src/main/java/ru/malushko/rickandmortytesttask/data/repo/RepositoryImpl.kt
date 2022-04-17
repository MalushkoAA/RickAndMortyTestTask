package ru.malushko.rickandmortytesttask.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.malushko.rickandmortytesttask.data.Mapper
import ru.malushko.rickandmortytesttask.data.PagingSource
import ru.malushko.rickandmortytesttask.data.network.ApiFactory
import ru.malushko.rickandmortytesttask.data.network.models.ResultDto
import ru.malushko.rickandmortytesttask.domain.Character
import ru.malushko.rickandmortytesttask.domain.Repository

class RepositoryImpl() : Repository {
    private val _character = MutableLiveData<ResultDto>()
    val character: LiveData<ResultDto>
        get() = _character

    val mapper = Mapper()

    val apiService = ApiFactory.apiService

    override fun getPagedCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                20,
                1,
                enablePlaceholders = false,
                1
            ),
            pagingSourceFactory = { PagingSource(apiService) }
        ).flow
    }

    override suspend fun getCharacterDetail(characterId: Int): Character {
        return mapper.mapDtoToEntity(apiService.getCharacterDetail(characterId))
    }


}