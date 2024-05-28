package com.systems.testtask.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.systems.testtask.data.remote.ApiInterFace
import com.systems.testtask.data.remoteDataClass.MovieDetails
import com.systems.testtask.data.remoteDataClass.SeachResults
import com.systems.testtask.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel : ViewModel() {

    private val _movieList = MutableLiveData<SeachResults>()

    val movieList: MutableLiveData<SeachResults>
        get() = _movieList

    private val _movieDetails = MutableLiveData<MovieDetails>()

    val movieDetails: MutableLiveData<MovieDetails>
        get() = _movieDetails

    fun getMovieList(searchString: String) {
        val apiInterFace = getApiInterFace()

        viewModelScope.launch {
            val getMovieListResponse = apiInterFace.getMovieList(Constants.API_KEY, searchString)
            if (getMovieListResponse.isSuccessful) {
                Log.e("RetrofitError", getMovieListResponse.raw().request().url().toString())
                _movieList.value = getMovieListResponse.body()
            } else {
                Log.e("RetrofitError", getMovieListResponse.message())
            }
        }
    }


    fun getMovieDetails(imdbId: String) {
        val apiInterFace = getApiInterFace()
        viewModelScope.launch {
            val movieDetailsResponse = apiInterFace.getMovieDetails(Constants.API_KEY, imdbId)
            if (movieDetailsResponse.isSuccessful) {
                _movieDetails.value = movieDetailsResponse.body()
                Log.e("RetrofitError", movieDetailsResponse.message())
            } else {
                Log.e("RetrofitError", movieDetailsResponse.message())
            }
        }
    }

    private fun getApiInterFace(): ApiInterFace {
        val retrofitBuilder = Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofitBuilder.create(ApiInterFace::class.java)
    }
}