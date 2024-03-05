package com.lokodom.todaymeal.database

data class User(
    val userId : String,
    val name: String,
    val quote: String,
    val id: String?
){
    fun toMap(): MutableMap<String, Any>{
        return mutableMapOf(
            "userId" to this.userId,
            "quote" to this.quote,
            "name" to this.name,

            )
    }
}