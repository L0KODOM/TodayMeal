package com.lokodom.todaymeal.repository

import androidx.lifecycle.LiveData
import com.lokodom.todaymeal.RestDataSource.IngredientDto
import com.lokodom.todaymeal.data.Category
import com.lokodom.todaymeal.data.Ingredient
import com.lokodom.todaymeal.data.MealData
import com.lokodom.todaymeal.database.FavouriteMeals
import com.lokodom.todaymeal.database.Rating

interface MealRepository {

    suspend fun getRandomMeal(): MealData

    suspend fun getCategoryList(): List<Category>

    suspend fun getIngredientList(): List<Ingredient>

    suspend fun getByIngredient(ingredient: String): List<MealData>

    suspend fun getByArea(country:String): List<MealData>

    suspend fun getRatingResults(): List<Rating>

    suspend fun getByCategory(cat:String) : List<MealData>

    suspend fun  getByName(name:String) : MealData?

    fun insertMeal(toInsert: MealData?)

    fun deleteMeal(toDelete : FavouriteMeals)

    fun getFavouriteMeals(): LiveData<List<FavouriteMeals>>

}