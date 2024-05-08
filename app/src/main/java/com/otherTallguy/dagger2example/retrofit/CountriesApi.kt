package com.otherTallguy.dagger2example.retrofit


import com.otherTallguy.dagger2example.model.Country
import com.otherTallguy.dagger2example.model.Universities

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountriesApi {

    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getCountries(): Response<List<Country>> // we are using coroutines here so making the function suspend.

    @GET("search")
    suspend fun getUniversities(@Query("country") country : String): Response<List<Universities>> // we are using coroutines here so making the function suspend.
}