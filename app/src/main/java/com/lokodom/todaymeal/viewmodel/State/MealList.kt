package com.lokodom.todaymeal.viewmodel.State

import com.lokodom.todaymeal.data.MealData

data class MealList(
    val list: List<MealData> = emptyList()
)