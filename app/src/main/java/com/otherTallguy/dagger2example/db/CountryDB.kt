package com.otherTallguy.dagger2example.db

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.otherTallguy.dagger2example.model.Country
import com.otherTallguy.dagger2example.model.Universities

@Database(entities = [Country::class,Universities::class], version = 4)
@TypeConverters(Converters::class)
abstract class CountryDB : RoomDatabase() {

    abstract fun getCountryDAO() : CountryDAO

   /* val MIGRATION_1_2 = object : Migration(1, 2){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Universities` (`state-province` Text,`domains` Text," +
                    "`web_pages` Text,`name` Text,`alpha_two_code` Text,`country` Text, PRIMARY KEY(`state-province`))")
        }
    }*/

}