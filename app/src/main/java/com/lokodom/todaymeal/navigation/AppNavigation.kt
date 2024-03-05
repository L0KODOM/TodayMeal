package com.lokodom.todaymeal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.lokodom.todaymeal.views.screens.FavScreen
import com.lokodom.todaymeal.views.screens.LoginScreen
import com.lokodom.todaymeal.views.screens.MenuScreen
import com.lokodom.todaymeal.views.screens.RandomDishScreen

@Composable
fun AppNavigation(){
    val navController= rememberNavController()

    NavHost(navController = navController,
        startDestination = if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            AppScreens.LoginScreen.route
        }else{
            AppScreens.RandomDishScreen.route
        }){
        composable(AppScreens.LoginScreen.route){
            LoginScreen(navController = navController)
        }
        composable(AppScreens.RandomDishScreen.route){
            RandomDishScreen(navController)
        }
        composable(AppScreens.MenuScreen.route){
            MenuScreen(navController)
        }
        composable(AppScreens.FavScreen.route){
            FavScreen(navController)
        }
    }

}