package com.lokodom.todaymeal.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lokodom.todaymeal.R
import com.lokodom.todaymeal.navigation.AppScreens
import com.lokodom.todaymeal.ui.theme.ThinkSmart
import com.lokodom.todaymeal.viewmodel.MealViewModel
import com.lokodom.todaymeal.views.cards.DishCard
import com.lokodom.todaymeal.views.cards.LoadingCard

val orange = Color(255 / 255f, 100 / 255f, 20f / 200, 1.0f)
val magenta = Color(255 / 255f, 100 / 255f, 200 / 255f, 1.0f)

@Composable
fun RandomDishScreen(
    navController: NavController,
    viewModel: MealViewModel = hiltViewModel()
){

    val context = LocalContext.current
    val isLoading by viewModel.isLoading.observeAsState(false)
    var name by remember{ mutableStateOf("") }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.LightGray)){
        Image(painter = painterResource(id = R.drawable.fondo), contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize())
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),horizontalAlignment = Alignment.CenterHorizontally){
            Row (modifier = Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween){
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu", tint = Color.Black,
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            navController.navigate(
                                route =
                                AppScreens.MenuScreen.route
                            )
                        })
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "Today Meal", fontFamily = ThinkSmart, color = magenta, fontSize = 62.sp)
                    Spacer(modifier = Modifier.height(5.dp))
                    ElevatedButton(onClick = { viewModel.addDish() }, modifier = Modifier
                        .padding(5.dp)) {
                        Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = "add",
                            tint = magenta)
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Fav",
                        tint = Color.Magenta,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(
                                    route =
                                    AppScreens.FavScreen.route
                                )
                            })
                }
                Icon(imageVector = Icons.Filled.AccountBox, contentDescription ="log out",
                    modifier = Modifier
                        .padding(20.dp)
                        .clickable {
                            navController.navigate(
                                route =
                                AppScreens.LoginScreen.route
                            )
                        }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = name, onValueChange = {name = it}, placeholder = {
                Text(text = "Search")
            },
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.Search, contentDescription = "search",
                        modifier = Modifier.clickable { viewModel.selectName(name, context) })
                })
            Spacer(modifier = Modifier.height(10.dp))

            if (isLoading){
                LoadingCard()
            }else DishCard(data = viewModel.state.mealData, viewModel = viewModel, context)
        }
    }

}