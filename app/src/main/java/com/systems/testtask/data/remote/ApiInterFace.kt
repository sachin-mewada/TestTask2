package com.systems.testtask.data.remote

import com.systems.testtask.data.remoteDataClass.MovieDetails
import com.systems.testtask.data.remoteDataClass.SeachResults
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterFace {
    @GET("/")
    suspend fun getMovieList(
        @Query("apikey") apiKey: String,
        @Query("s") searchString: String,
//        @Query("page") pageNo: String
    ): Response<SeachResults>

    @GET("/")
    suspend fun getMovieDetails(
        @Query("apikey") apiKey: String,
        @Query("i") imdbId: String
    ): Response<MovieDetails>
}