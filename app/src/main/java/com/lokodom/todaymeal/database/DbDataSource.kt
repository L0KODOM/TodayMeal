package com.lokodom.todaymeal.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database( entities = [FavouriteMeals::class], version = 1)
abstract class DbDataSource : RoomDatabase(){
    abstract fun mealDao(): MealDao
}