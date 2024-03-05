package com.lokodom.todaymeal.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.lokodom.todaymeal.RestDataSource.IngredientDto
import com.lokodom.todaymeal.RestDataSource.MealApi
import com.lokodom.todaymeal.data.Category
import com.lokodom.todaymeal.data.Ingredient
import com.lokodom.todaymeal.data.MealData
import com.lokodom.todaymeal.database.FavouriteMeals
import com.lokodom.todaymeal.database.MealDao
import com.lokodom.todaymeal.database.Rating
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val mealApi: MealApi,
    private val mealDao: MealDao
) : MealRepository {

    private var dish: MealData? = null

    override suspend fun getRandomMeal(): MealData {
        val randomDishResponse = mealApi.getRandomDish()
        val exactDish = randomDishResponse.meals[0]

        val name = exactDish.name
        val country = exactDish.country
        val category = exactDish.category
        val image = exactDish.image
        val id = exactDish.id
        val youtube = exactDish.youtube
        val in1 = exactDish.ingredient1
        val in2 = exactDish.ingredient2
        val in3 = exactDish.ingredient3
        val in4 = exactDish.ingredient4
        val in5 = exactDish.ingredient5
        val in6 = exactDish.ingredient6
        val in7 = exactDish.ingredient7
        val in8 = exactDish.ingredient8
        val in9 = exactDish.ingredient9
        val in10 = exactDish.ingredient10
        val in11 = exactDish.ingredient11
        val in12 = exactDish.ingredient12
        val in13 = exactDish.ingredient13
        val in14 = exactDish.ingredient14
        val in15 = exactDish.ingredient15
        val mes1 = exactDish.measure1
        val mes2 = exactDish.measure2
        val mes3 = exactDish.measure3
        val mes4 = exactDish.measure4
        val mes5 = exactDish.measure5
        val mes6 = exactDish.measure6
        val mes7 = exactDish.measure7
        val mes8 = exactDish.measure8
        val mes9 = exactDish.measure9
        val mes10 = exactDish.measure10
        val mes11 = exactDish.measure11
        val mes12 = exactDish.measure12
        val mes13 = exactDish.measure13
        val mes14 = exactDish.measure14
        val mes15 = exactDish.measure15

        dish = MealData(name = name, country = country, category = category
            , image = image, id = id, youtube = youtube, ingredient1 = in1,
            ingredient2 = in2, ingredient3 = in3, ingredient4 = in4, ingredient5 = in5,
            ingredient6 = in6, ingredient7 = in7, ingredient8 = in8, ingredient9 = in9,
            ingredient10 = in10, ingredient11 = in11, ingredient12 = in12,
            ingredient13 = in13, ingredient14 = in14, ingredient15 = in15, measure1 = mes1,
            measure2 = mes2, measure3 = mes3, measure4 = mes4, measure5 = mes5, measure6 = mes6, measure7 = mes7,
            measure8 = mes8, measure9 = mes9, measure10 = mes10, measure11 = mes11, measure12 = mes12, measure13 = mes13,
            measure14 = mes14, measure15 = mes15)
        return dish!!
    }

    override suspend fun getCategoryList(): List<Category> {
        val list = mealApi.getCategories()

        val uno = Category(list.categories[0].name, list.categories[0].image)
        val two = Category(list.categories[1].name, list.categories[1].image)
        val three = Category(list.categories[2].name, list.categories[2].image)
        val four = Category(list.categories[3].name, list.categories[3].image)
        val five = Category(list.categories[4].name, list.categories[4].image)
        val six = Category(list.categories[5].name, list.categories[5].image)
        val seven = Category(list.categories[6].name, list.categories[6].image)
        val eight = Category(list.categories[7].name, list.categories[7].image)
        val nine = Category(list.categories[8].name, list.categories[8].image)
        val ten = Category(list.categories[9].name, list.categories[9].image)
        val eleven = Category(list.categories[10].name, list.categories[10].image)
        val twelve = Category(list.categories[11].name, list.categories[11].image)
        val thirdteen = Category(list.categories[12].name, list.categories[12].image)
        val fourteen = Category(list.categories[13].name, list.categories[13].image)

        return listOf(uno, two,three,four,five,six,seven,eight,
            nine,ten,eleven,twelve,thirdteen,fourteen)
    }

    override suspend fun getIngredientList(): List<Ingredient> {
        val ingredientList = mealApi.getIngredients()
        val finalList = mutableListOf<Ingredient>()

        for (i in 0 until minOf(500, ingredientList.meals.size)) {
            val type = ingredientList.meals[i].type ?: ""
            val ingredient = Ingredient(ingredientList.meals[i].name, type)
            finalList.add(ingredient)
        }

        println(finalList)
        return finalList
    }

    override suspend fun getByIngredient(ingredient: String): List<MealData> {
        val IngredientDishResponse = mealApi.getByIngredient(ingredient)
        val meals = IngredientDishResponse.meals ?: emptyList()
        val mealDataList = meals.map { meal ->
            MealData(
                name = meal.name,
                country = meal.country,
                category = meal.category,
                image = meal.image,
                id = meal.id,
                youtube = meal.youtube,
                ingredient15 = meal.ingredient15,
                ingredient14 = meal.ingredient14,
                ingredient13 = meal.ingredient13,
                ingredient12 = meal.ingredient12,
                ingredient11 = meal.ingredient11,
                ingredient10 = meal.ingredient10,
                ingredient9 = meal.ingredient9,
                ingredient8 = meal.ingredient8,
                ingredient7 = meal.ingredient7,
                ingredient6 = meal.ingredient6,
                ingredient5 = meal.ingredient5,
                ingredient4 = meal.ingredient4,
                ingredient3 = meal.ingredient3,
                ingredient2 = meal.ingredient2,
                ingredient1 = meal.ingredient1,
                measure15 = meal.measure15,
                measure14 = meal.measure14,
                measure13 = meal.measure13,
                measure12 = meal.measure12,
                measure11 = meal.measure11,
                measure10 = meal.measure10,
                measure9 = meal.measure9,
                measure8 = meal.measure8,
                measure7 = meal.measure7,
                measure6 = meal.measure6,
                measure5 = meal.measure5,
                measure4 = meal.measure4,
                measure3 = meal.measure3,
                measure2 = meal.measure2,
                measure1 = meal.measure1
            )
        }
        return mealDataList
    }

    override suspend fun getByArea(country:String): List<MealData> {
        val AreaDishResponse = mealApi.getByArea(country)
        val meals = AreaDishResponse.meals ?: emptyList()
        val mealDataList = meals.map { meal ->
            MealData(
                name = meal.name,
                country = country,
                category = meal.category,
                image = meal.image,
                id = meal.id,
                youtube = meal.youtube,
                ingredient15 = meal.ingredient15,
                ingredient14 = meal.ingredient14,
                ingredient13 = meal.ingredient13,
                ingredient12 = meal.ingredient12,
                ingredient11 = meal.ingredient11,
                ingredient10 = meal.ingredient10,
                ingredient9 = meal.ingredient9,
                ingredient8 = meal.ingredient8,
                ingredient7 = meal.ingredient7,
                ingredient6 = meal.ingredient6,
                ingredient5 = meal.ingredient5,
                ingredient4 = meal.ingredient4,
                ingredient3 = meal.ingredient3,
                ingredient2 = meal.ingredient2,
                ingredient1 = meal.ingredient1,
                measure15 = meal.measure15,
                measure14 = meal.measure14,
                measure13 = meal.measure13,
                measure12 = meal.measure12,
                measure11 = meal.measure11,
                measure10 = meal.measure10,
                measure9 = meal.measure9,
                measure8 = meal.measure8,
                measure7 = meal.measure7,
                measure6 = meal.measure6,
                measure5 = meal.measure5,
                measure4 = meal.measure4,
                measure3 = meal.measure3,
                measure2 = meal.measure2,
                measure1 = meal.measure1
            )
        }
        return mealDataList

    }

    override suspend fun getByCategory(cat: String): List<MealData> {
        val CatDishResponse = mealApi.getByCategory(cat)
        val meals = CatDishResponse.meals ?: emptyList()
        val mealDataList = meals.map { meal ->
            MealData(
                name = meal.name,
                country = meal.country,
                category = cat,
                image = meal.image,
                id = meal.id,
                youtube = meal.youtube,
                ingredient15 = meal.ingredient15,
                ingredient14 = meal.ingredient14,
                ingredient13 = meal.ingredient13,
                ingredient12 = meal.ingredient12,
                ingredient11 = meal.ingredient11,
                ingredient10 = meal.ingredient10,
                ingredient9 = meal.ingredient9,
                ingredient8 = meal.ingredient8,
                ingredient7 = meal.ingredient7,
                ingredient6 = meal.ingredient6,
                ingredient5 = meal.ingredient5,
                ingredient4 = meal.ingredient4,
                ingredient3 = meal.ingredient3,
                ingredient2 = meal.ingredient2,
                ingredient1 = meal.ingredient1,
                measure15 = meal.measure15,
                measure14 = meal.measure14,
                measure13 = meal.measure13,
                measure12 = meal.measure12,
                measure11 = meal.measure11,
                measure10 = meal.measure10,
                measure9 = meal.measure9,
                measure8 = meal.measure8,
                measure7 = meal.measure7,
                measure6 = meal.measure6,
                measure5 = meal.measure5,
                measure4 = meal.measure4,
                measure3 = meal.measure3,
                measure2 = meal.measure2,
                measure1 = meal.measure1
            )
        }
        return mealDataList
    }

    override suspend fun getByName(name: String): MealData? {

        val nameDishResponse = mealApi.getByName(name)
        val exactDish = nameDishResponse.meals[0]

        val name = name
        val country = exactDish.country
        val category = exactDish.category
        val image = exactDish.image
        val id = exactDish.id
        val youtube = exactDish.youtube
        val in1 = exactDish.ingredient1
        val in2 = exactDish.ingredient2
        val in3 = exactDish.ingredient3
        val in4 = exactDish.ingredient4
        val in5 = exactDish.ingredient5
        val in6 = exactDish.ingredient6
        val in7 = exactDish.ingredient7
        val in8 = exactDish.ingredient8
        val in9 = exactDish.ingredient9
        val in10 = exactDish.ingredient10
        val in11 = exactDish.ingredient11
        val in12 = exactDish.ingredient12
        val in13 = exactDish.ingredient13
        val in14 = exactDish.ingredient14
        val in15 = exactDish.ingredient15
        val mes1 = exactDish.measure1
        val mes2 = exactDish.measure2
        val mes3 = exactDish.measure3
        val mes4 = exactDish.measure4
        val mes5 = exactDish.measure5
        val mes6 = exactDish.measure6
        val mes7 = exactDish.measure7
        val mes8 = exactDish.measure8
        val mes9 = exactDish.measure9
        val mes10 = exactDish.measure10
        val mes11 = exactDish.measure11
        val mes12 = exactDish.measure12
        val mes13 = exactDish.measure13
        val mes14 = exactDish.measure14
        val mes15 = exactDish.measure15


        dish = MealData(name = name, country = country, category = category
            , image = image, id = id, youtube = youtube, ingredient1 = in1,
            ingredient2 = in2, ingredient3 = in3, ingredient4 = in4, ingredient5 = in5,
            ingredient6 = in6, ingredient7 = in7, ingredient8 = in8, ingredient9 = in9,
            ingredient10 = in10, ingredient11 = in11, ingredient12 = in12,
            ingredient13 = in13, ingredient14 = in14, ingredient15 = in15, measure1 = mes1,
            measure2 = mes2, measure3 = mes3, measure4 = mes4, measure5 = mes5, measure6 = mes6, measure7 = mes7,
            measure8 = mes8, measure9 = mes9, measure10 = mes10, measure11 = mes11, measure12 = mes12, measure13 = mes13,
            measure14 = mes14, measure15 = mes15)
        return dish
    }

    override fun getFavouriteMeals(): LiveData<List<FavouriteMeals>> =
        mealDao.getAll()

    override suspend fun getRatingResults(): List<Rating> {
        val db = FirebaseFirestore.getInstance()
        val rated = mutableListOf<Rating>()

        try {
            val querySnapshot = db.collection("ratings").get().await()
            for (document in querySnapshot) {
                val result = document.toObject(Rating::class.java)
                rated.add(result)
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "firestore ${e.message}")
        }
        return rated
            .groupBy { it.name }
            .mapValues { (_, group) ->
                group.map { it.rating.toInt() }.average().toInt() }
            .map { (name, rating) ->
                val groupList = rated.filter { it.name == name }
                val image = groupList.firstOrNull()?.image ?: ""
                Rating(name, rating.toString(), image = image)
            }
            .sortedByDescending { it.rating.toInt() }
            .take(5)
    }

    override fun insertMeal(dishMeal: MealData?) {

        val name = dishMeal?.name
        val image = dishMeal?.image
        val country = dishMeal?.country!!
        val category = dishMeal?.category!!
        val id = dishMeal.id

        val favMeal = FavouriteMeals(name = name, image = image, country = country,
            category = category, id = id)

        mealDao.insert(favMeal)

    }

    override fun deleteMeal(toDelete:FavouriteMeals) =
        mealDao.delete(toDelete)









}