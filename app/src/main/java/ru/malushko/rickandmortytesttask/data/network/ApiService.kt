package ru.malushko.rickandmortytesttask.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.malushko.rickandmortytesttask.ResponseModel
import ru.malushko.rickandmortytesttask.data.network.models.ResultDto

interface ApiService {

    @GET("character/")
    suspend fun getCharacters(
        @Query("page") page: Int = 1
    ): ResponseModel

    @GET("character/{id}")
    suspend fun getCharacterDetail(
        @Path("id") id:Int
    ):ResultDto


}