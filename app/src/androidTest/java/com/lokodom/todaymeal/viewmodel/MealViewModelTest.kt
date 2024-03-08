package com.lokodom.todaymeal.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lokodom.todaymeal.repository.MealRepository
import io.mockk.impl.annotations.RelaxedMockK
import junit.framework.TestCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import org.junit.Before
import org.junit.Rule
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MyViewModelTest {

    @RelaxedMockK
    private lateinit var repository: MealRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: MealViewModel

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        viewModel = MealViewModel(repository)
    }

    @Test
    fun testChangeRating() {
        // Given
        val num = 2
        val name = "TestMeal"
        val image = "test_image_url"

        // When
        viewModel.changeRating(num, name, image)


        // Then
        TestCase.assertEquals(num, viewModel.ratingState.stars)
        // For example, you can check if the ratingState has been updated correctly
        // Also, you may need to wait for some time for the Firestore operation to complete
        // and verify the result accordingly
    }
}