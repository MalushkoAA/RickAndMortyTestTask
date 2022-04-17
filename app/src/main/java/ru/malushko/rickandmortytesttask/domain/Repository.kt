package ru.malushko.rickandmortytesttask.domain

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import androidx.paging.PagingData

interface Repository {

    fun getPagedCharacters(): Flow<PagingData<Character>>

    suspend fun getCharacterDetail(characterId:Int):Character

}