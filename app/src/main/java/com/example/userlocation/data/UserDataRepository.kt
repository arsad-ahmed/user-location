package com.example.userlocation.data

import com.example.userlocation.model.UserModelItem
import com.example.userlocation.util.BASE_URL
import com.example.userlocation.util.END_POINT
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitApiService
{
    @GET(END_POINT)
    suspend fun getUserData():Response<List<UserModelItem>>
}

class UserDataRepository
{
    private val apiService:RetrofitApiService

    init
    {
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService=retrofit.create(RetrofitApiService::class.java)
    }

    suspend fun getUserData():Response<List<UserModelItem>>
    {
        return apiService.getUserData()
    }
}