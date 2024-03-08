package com.lokodom.todaymeal.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.lokodom.todaymeal.data.Category
import com.lokodom.todaymeal.data.Ingredient
import com.lokodom.todaymeal.data.MealData
import com.lokodom.todaymeal.repository.MealRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import android.content.Context
import com.lokodom.todaymeal.database.Rating
import org.mockito.Mock

@ExperimentalCoroutinesApi
class MealViewModelTest {

    @RelaxedMockK
    private lateinit var repository: MealRepository

    private lateinit var mealViewModel: MealViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        mealViewModel = MealViewModel(repository)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `test addDish function`() = runTest {
        // Given
        val expectedMealData = MealData("Spicy Arrabiata Penne","Italian","https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
            "Vegetarian","52771", "https://www.youtube.com/watch?v=1IszT_guI08","penne rigate","olive oil","garlic","chopped tomatoes","red chile flakes",
            "italian seasoning","basil","Parmigiano-Reggiano",null,null,
            null,null,null,null,null,"1 pound","1/4 cup","3 cloves",
            "1 tin ","1/2 teaspoon","1/2 teaspoon","6 leaves","spinkling",null,
            null,null,null,null,null,null,)
        coEvery { repository.getRandomMeal() } returns expectedMealData

        //When
        mealViewModel.addDish()

        // Asegúrate de que la tarea asíncrona se complete
        advanceUntilIdle()

        delay(1000)

        // Verificar que la comida obtenida sea la esperada
        mealViewModel.isLoading.value?.let { assertTrue(it) }

        delay(1000)

        assert(mealViewModel.state.mealData == expectedMealData)

        mealViewModel.isLoading.value?.let { assertFalse(it) }

    }

    @Test
    fun `test addCategories function`() = runTest {
        // Given
        val expectedCategory1 = Category("Beef" , "beef.jpg")
        val expectedCategory2 = Category("Chicken" , "chicken.jpg")
        val catList= listOf<Category>(expectedCategory1,expectedCategory2)
        coEvery { repository.getCategoryList() } returns catList

        //When
        mealViewModel.addCategories()

        advanceUntilIdle()

        delay(1000)


        mealViewModel.isLoading.value?.let { assertTrue(it) }

        while (mealViewModel.Catstate.category == null) {
            advanceUntilIdle()
            delay(100)
        }

        // Then
        assertEquals(catList, mealViewModel.Catstate.category)

        delay(1000)

        mealViewModel.isLoading.value?.let { assertFalse(it) }

    }

    @Test
    fun `test addIngredients function`() = runTest {
        // Given
        val expectedIng1 = Ingredient("Beef" , "meat")
        val expectedIng2 = Ingredient("Chicken" , "meat")
        val ingList= listOf<Ingredient>(expectedIng1,expectedIng2)
        coEvery { repository.getIngredientList() } returns ingList

        //When
        mealViewModel.addAllIngredients()

        advanceUntilIdle()

        delay(1000)


        mealViewModel.isLoading.value?.let { assertTrue(it) }

        while (mealViewModel.ingredientList.ingredientList == null) {
            advanceUntilIdle()
            delay(100)
        }

        // Then
        assertEquals(ingList, mealViewModel.ingredientList.ingredientList)

        delay(1000)

        mealViewModel.isLoading.value?.let { assertFalse(it) }

    }

    @Test
    fun `test SelectCountry function`() = runTest{
        val expectedMealData1 = MealData("Spicy Arrabiata Penne","Italian","https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
            "Vegetarian","52771", "https://www.youtube.com/watch?v=1IszT_guI08","penne rigate","olive oil","garlic","chopped tomatoes","red chile flakes",
            "italian seasoning","basil","Parmigiano-Reggiano",null,null,
            null,null,null,null,null,"1 pound","1/4 cup","3 cloves",
            "1 tin ","1/2 teaspoon","1/2 teaspoon","6 leaves","spinkling",null,
            null,null,null,null,null,null,)
        val expectedMealData2 = MealData("Spicy Arrabiata Penne","Italian","https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
            "Vegetarian","52771", "https://www.youtube.com/watch?v=1IszT_guI08","penne rigate","olive oil","garlic","chopped tomatoes","red chile flakes",
            "italian seasoning","basil","Parmigiano-Reggiano",null,null,
            null,null,null,null,null,"1 pound","1/4 cup","3 cloves",
            "1 tin ","1/2 teaspoon","1/2 teaspoon","6 leaves","spinkling",null,
            null,null,null,null,null,null,)
        val mealList = listOf<MealData>(expectedMealData1,expectedMealData2)
        coEvery { repository.getByArea(any()) } returns mealList

        //When
        mealViewModel.selectCountry("")

        advanceUntilIdle()

        delay(1000)


        mealViewModel.isLoading.value?.let { assertTrue(it) }

        while (mealViewModel.ListState.list == null) {
            advanceUntilIdle()
            delay(100)
        }

        // Then
        assertEquals(mealList, mealViewModel.ListState.list)

        delay(1000)

        mealViewModel.isLoading.value?.let { assertFalse(it) }


    }

    @Test
    fun `test SelectCategory function`() = runTest{
        val expectedMealData1 = MealData("Spicy Arrabiata Penne","Italian","https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
            "Vegetarian","52771", "https://www.youtube.com/watch?v=1IszT_guI08","penne rigate","olive oil","garlic","chopped tomatoes","red chile flakes",
            "italian seasoning","basil","Parmigiano-Reggiano",null,null,
            null,null,null,null,null,"1 pound","1/4 cup","3 cloves",
            "1 tin ","1/2 teaspoon","1/2 teaspoon","6 leaves","spinkling",null,
            null,null,null,null,null,null,)
        val expectedMealData2 = MealData("Spicy Arrabiata Penne","Italian","https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
            "Vegetarian","52771", "https://www.youtube.com/watch?v=1IszT_guI08","penne rigate","olive oil","garlic","chopped tomatoes","red chile flakes",
            "italian seasoning","basil","Parmigiano-Reggiano",null,null,
            null,null,null,null,null,"1 pound","1/4 cup","3 cloves",
            "1 tin ","1/2 teaspoon","1/2 teaspoon","6 leaves","spinkling",null,
            null,null,null,null,null,null,)
        val mealList = listOf<MealData>(expectedMealData1,expectedMealData2)
        coEvery { repository.getByCategory(any()) } returns mealList

        //When
        mealViewModel.selectCategory("")

        advanceUntilIdle()

        delay(1000)


        mealViewModel.isLoading.value?.let { assertTrue(it) }

        while (mealViewModel.ListState.list == null) {
            advanceUntilIdle()
            delay(100)
        }

        // Then
        assertEquals(mealList, mealViewModel.ListState.list)

        delay(1000)

        mealViewModel.isLoading.value?.let { assertFalse(it) }


    }

    @Test
    fun `test SelectIngredient function`() = runTest{
        val expectedMealData1 = MealData("Spicy Arrabiata Penne","Italian","https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
            "Vegetarian","52771", "https://www.youtube.com/watch?v=1IszT_guI08","penne rigate","olive oil","garlic","chopped tomatoes","red chile flakes",
            "italian seasoning","basil","Parmigiano-Reggiano",null,null,
            null,null,null,null,null,"1 pound","1/4 cup","3 cloves",
            "1 tin ","1/2 teaspoon","1/2 teaspoon","6 leaves","spinkling",null,
            null,null,null,null,null,null,)
        val expectedMealData2 = MealData("Spicy Arrabiata Penne","Italian","https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
            "Vegetarian","52771", "https://www.youtube.com/watch?v=1IszT_guI08","penne rigate","olive oil","garlic","chopped tomatoes","red chile flakes",
            "italian seasoning","basil","Parmigiano-Reggiano",null,null,
            null,null,null,null,null,"1 pound","1/4 cup","3 cloves",
            "1 tin ","1/2 teaspoon","1/2 teaspoon","6 leaves","spinkling",null,
            null,null,null,null,null,null,)
        val mealList = listOf<MealData>(expectedMealData1,expectedMealData2)
        coEvery { repository.getByIngredient(any()) } returns mealList

        //When
        mealViewModel.selectIngredient("")

        advanceUntilIdle()

        delay(1000)


        mealViewModel.isLoading.value?.let { assertTrue(it) }

        while (mealViewModel.ListState.list == null) {
            advanceUntilIdle()
            delay(100)
        }

        // Then
        assertEquals(mealList, mealViewModel.ListState.list)

        delay(1000)

        mealViewModel.isLoading.value?.let { assertFalse(it) }


    }

    @Test
    fun `test SelectName function`() = runTest{
        val expectedMealData1 = MealData("Spicy Arrabiata Penne","Italian","https://www.themealdb.com/images/media/meals/ustsqw1468250014.jpg",
            "Vegetarian","52771", "https://www.youtube.com/watch?v=1IszT_guI08","penne rigate","olive oil","garlic","chopped tomatoes","red chile flakes",
            "italian seasoning","basil","Parmigiano-Reggiano",null,null,
            null,null,null,null,null,"1 pound","1/4 cup","3 cloves",
            "1 tin ","1/2 teaspoon","1/2 teaspoon","6 leaves","spinkling",null,
            null,null,null,null,null,null,)
        val context = Mockito.mock(Context::class.java)

        coEvery { repository.getByName(any()) } returns expectedMealData1

        //When
        mealViewModel.selectName("", contxt = context)

        advanceUntilIdle()

        delay(1000)


        mealViewModel.isLoading.value?.let { assertTrue(it) }

        while (mealViewModel.state.mealData == null) {
            advanceUntilIdle()
            delay(100) // Wait for a short duration before checking again
        }

        // Then
        assertEquals(expectedMealData1, mealViewModel.state.mealData)

        delay(1000)

        mealViewModel.isLoading.value?.let { assertFalse(it) }


    }

    @Test
    fun `test RatingResults function`() = runTest{
        val expectedRating1 = Rating("croissant","5","croissant.jpg","30")
        val expectedRating2 = Rating("ChocolateCroissant","5","ChocolateCroissant.jpg","30")
        val ratingList = listOf<Rating>(expectedRating1,expectedRating2)
        coEvery { repository.getRatingResults() } returns ratingList

        //When
        mealViewModel.ratingResults()



        while (mealViewModel.ratedListState.ratedList == null) {
            advanceUntilIdle()
            delay(100)
        }

        // Then
        assertEquals(ratingList, mealViewModel.ratedListState.ratedList)



    }


}

