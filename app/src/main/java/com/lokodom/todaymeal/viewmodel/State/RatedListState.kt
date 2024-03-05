package com.lokodom.todaymeal.viewmodel.State

import com.lokodom.todaymeal.database.Rating

data class RatedListState(
    val ratedList: List<Rating>? = null
)
