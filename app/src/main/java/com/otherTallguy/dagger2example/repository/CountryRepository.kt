package com.otherTallguy.dagger2example.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.otherTallguy.dagger2example.db.CountryDB
import com.otherTallguy.dagger2example.model.Country
import com.otherTallguy.dagger2example.model.Universities
import com.otherTallguy.dagger2example.retrofit.CountriesApi
import javax.inject.Inject

/** Here dagger will check for CountriesApi since we are using constructor injection here
 * and Dagger will provide the object here automatically
 */

class CountryRepository @Inject constructor(
    private val countriesApi: CountriesApi,
    private val countryDB: CountryDB
) {

    private val _countries = MutableLiveData<List<Country>>() // private member only
    val countries: LiveData<List<Country>> // public and read only.
        get() = _countries

    private val _universities = MutableLiveData<List<Universities>>() // private member only
    val universities: LiveData<List<Universities>> // public and read only.
        get() = _universities

   /* // suspend function to call the api
    suspend fun getCountries() {
        val result = countriesApi.getCountries()
        if (result.body() != null) {
            result.body()?.let { body ->
                countryDB.getCountryDAO().addCountries(body)
                _countries.postValue(body)
            }


        }

    }*/

    suspend fun getUniversities(countryName : String) {
        val result = countriesApi.getUniversities(countryName)
        if (result.body() != null) {
            result.body()?.let { body ->
                countryDB.getCountryDAO().addUniversities(body)
                _universities.postValue(body)
            }


        }

    }

}

