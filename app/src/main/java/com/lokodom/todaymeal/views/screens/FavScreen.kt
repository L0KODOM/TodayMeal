package com.lokodom.todaymeal.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lokodom.todaymeal.R
import com.lokodom.todaymeal.navigation.AppScreens
import com.lokodom.todaymeal.ui.theme.ThinkSmart
import com.lokodom.todaymeal.viewmodel.MealViewModel
import com.lokodom.todaymeal.views.FavList
import com.lokodom.todaymeal.views.cards.DishCard

@Composable
fun FavScreen(
    navController: NavController,
    viewModel: MealViewModel = hiltViewModel()
){
    val context = LocalContext.current

    Box(modifier = Modifier
        .background(Color.LightGray)
        .fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.fondo2), contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize())
        Row {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back",
                tint = Color.White,
                modifier = Modifier
                    .padding(20.dp)
                    .clickable { navController.navigate(AppScreens.RandomDishScreen.route) })
            Spacer(modifier = Modifier.width(30.dp))
            Text(text = "Favs", fontFamily = ThinkSmart, color = Color.White, fontSize = 82.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            DishCard(data = viewModel.state.mealData , viewModel = viewModel, context = context)
            FavList(viewModel = viewModel)
        }
    }
}