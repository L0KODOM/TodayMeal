package com.lokodom.todaymeal.database

data class Rating(
    val name: String = "",
    val rating: String= "",
    val image : String = "",
    val votes: String = ""
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "name" to this.name,
            "rating" to this.rating,
            "image" to this.image
        )
    }
}