package com.lokodom.todaymeal.navigation

sealed class AppScreens (val route: String) {

    object LoginScreen: AppScreens("login_screen_route")
    object RandomDishScreen: AppScreens("random_dish_route")
    object MenuScreen: AppScreens("menu_route")
    object FavScreen: AppScreens("fav_route")


}