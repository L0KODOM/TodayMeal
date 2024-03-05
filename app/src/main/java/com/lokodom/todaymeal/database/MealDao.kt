package com.lokodom.todaymeal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meal: FavouriteMeals)

    @Query("SELECT * FROM favmeals ORDER BY id DESC")
    fun getAll(): LiveData<List<FavouriteMeals>>

    @Delete
    fun delete(meal: FavouriteMeals)
}