package com.otherTallguy.dagger2example.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.otherTallguy.dagger2example.model.Country
import com.otherTallguy.dagger2example.model.Universities

@Dao
interface CountryDAO {

  /*  @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCountries(countries: List<Country>)

    @Query("SELECT * FROM Country")
    fun getCountries() : List<Country>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUniversities(universities: List<Universities>)

    @Query("SELECT * FROM Universities")
    fun getUniversities() : List<Universities>

}