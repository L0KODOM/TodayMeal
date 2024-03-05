package com.lokodom.todaymeal.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favmeals")
data class FavouriteMeals(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "image") val image: String?,
    @ColumnInfo(name = "country") val country: String?,
    @ColumnInfo(name = "category") val category: String?,
    @PrimaryKey(autoGenerate = false) val id: String,
)