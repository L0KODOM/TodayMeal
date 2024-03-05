package com.lokodom.todaymeal.RestDataSource

import com.squareup.moshi.Json

data class IngredientDto(
    @field:Json(name = "strIngredient")
    val name: String,
    @field:Json(name = "strType")
    val type: String? = null,

)
