package ru.malushko.rickandmortytesttask.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.malushko.rickandmortytesttask.data.network.ApiService
import ru.malushko.rickandmortytesttask.domain.Character


class PagingSource(
    private val service: ApiService
) : PagingSource<Int, Character>() {

    private val mapper = Mapper()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {

        val page: Int = params.key ?: 1

        return try {
            val characters = service.getCharacters(page).results.map { mapper.mapDtoToEntity(it) }
            return LoadResult.Page(
                data = characters,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (characters.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

}