package ru.malushko.rickandmortytesttask.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.malushko.rickandmortytesttask.data.repo.RepositoryImpl
import ru.malushko.rickandmortytesttask.domain.Character
import ru.malushko.rickandmortytesttask.domain.usecases.GetCharacterDetailUseCase
import ru.malushko.rickandmortytesttask.domain.usecases.GetPagedCharactersUseCase


class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = RepositoryImpl()
    private val getPagedCharactersUseCase = GetPagedCharactersUseCase(repo)
    private val getCharacterDetailUseCase = GetCharacterDetailUseCase(repo)

    val charactersFlow: Flow<PagingData<Character>> = getPagedCharactersUseCase()

    private val _hero = MutableLiveData<Character>()
    val hero: LiveData<Character>
        get() = _hero

    fun getHero(charId: Int) {
        viewModelScope.launch {
            try {
                _hero.value = getCharacterDetailUseCase(charId)!!
            } catch (e: Exception) {
            }
        }
    }

}

