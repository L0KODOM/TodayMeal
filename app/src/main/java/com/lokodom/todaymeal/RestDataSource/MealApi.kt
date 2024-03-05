package com.lokodom.todaymeal.RestDataSource

import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("random.php")
    suspend fun getRandomDish(): Meals

    @GET("categories.php")
    suspend fun getCategories(): Categories

    @GET("list.php?i=list")
    suspend fun getIngredients(): Ingredients

    @GET("filter.php")
    suspend fun getByArea(
        @Query("a") country: String
    ): Meals

    @GET("filter.php")
    suspend fun getByCategory(
        @Query("c") country: String
    ): Meals

    @GET("filter.php")
    suspend fun getByIngredient(
        @Query("i") ingredient: String
    ): Meals

    @GET("search.php")
    suspend fun getByName(
        @Query("s") name: String
    ): Meals


}