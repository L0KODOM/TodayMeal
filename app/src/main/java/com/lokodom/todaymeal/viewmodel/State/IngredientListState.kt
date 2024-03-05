package com.lokodom.todaymeal.viewmodel.State

import com.lokodom.todaymeal.data.Ingredient

data class IngredientListState(
    val ingredientList : List<Ingredient>? = null
)
