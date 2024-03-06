package com.lokodom.todaymeal.views.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.lokodom.todaymeal.R
import com.lokodom.todaymeal.navigation.AppScreens
import com.lokodom.todaymeal.ui.theme.ThinkSmart
import com.lokodom.todaymeal.viewmodel.MealViewModel
import com.lokodom.todaymeal.viewmodel.State.RatedListState
import com.lokodom.todaymeal.views.CategoryColumn
import com.lokodom.todaymeal.views.CountryColumn
import com.lokodom.todaymeal.views.IngredientColumn
import com.lokodom.todaymeal.views.RatedColumn
import com.lokodom.todaymeal.views.cards.CategoryDishCard
import com.lokodom.todaymeal.views.cards.DishCard

@Composable
fun MenuScreen(
    navController: NavController,
    viewModel: MealViewModel = hiltViewModel()
){
    val context = LocalContext.current
    var countriesExpanded by remember { mutableStateOf(false) }
    var categoriesExpanded by remember { mutableStateOf(false) }
    val isLoading by viewModel.isLoading.observeAsState(false)
    var ratedExpanded by remember{ mutableStateOf(false) }
    var ingredientsExpanded by remember{ mutableStateOf(false) }
    var helpExpanded by remember{ mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.fondo3), contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize())
        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally){


            Row (modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround){
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back", tint = Color.White,
                    modifier = Modifier
                        .padding(vertical = 20.dp, horizontal = 20.dp)
                        .clickable { navController.navigate(AppScreens.RandomDishScreen.route) })
                Text(text = "Menu", fontFamily = ThinkSmart, color = Color.White, fontSize = 82.sp)
                Row {
                    Icon(imageVector = Icons.Filled.Build, contentDescription = "back", tint = Color.White,
                        modifier = Modifier
                            .padding(vertical = 20.dp, horizontal = 20.dp)
                            .clickable { helpExpanded = !helpExpanded })
                    if (helpExpanded or countriesExpanded or categoriesExpanded or ratedExpanded or ingredientsExpanded){
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "back", tint = Color.White,
                            modifier = Modifier
                                .padding(vertical = 20.dp, horizontal = 20.dp)
                                .clickable {
                                countriesExpanded= false
                                categoriesExpanded= false
                                ratedExpanded = false
                                ingredientsExpanded = false})
                    }
                }

            }

            Spacer(modifier = Modifier.height(30.dp))

            DishCard(data = viewModel.state.mealData, viewModel = viewModel, context)

            if (isLoading){
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }else CategoryDishCard(data = viewModel.ListState.list, viewModel = viewModel)

            Column (modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center){

                if (helpExpanded){
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center){
                        Image(painter = painterResource(id = R.drawable.culinary_confessional),
                            contentDescription = null)
                    }
                }

                if (!countriesExpanded && !ratedExpanded && !ingredientsExpanded && !helpExpanded){
                    SelectionBox(
                        categoriesExpanded = categoriesExpanded,
                        countriesExpanded = countriesExpanded,
                        ratedExpanded = ratedExpanded,
                        ingredientsExpanded = ingredientsExpanded,
                        isLoading = isLoading,
                        modifier = Modifier
                            .clickable {
                                viewModel.addCategories()
                                categoriesExpanded = !categoriesExpanded
                                countriesExpanded = false
                                ratedExpanded = false
                                ingredientsExpanded = false
                            },
                        viewModel = viewModel,
                        icon = Icons.Filled.DateRange,
                        name = "CATEGORIES",
                        context = context
                    )
                }
                if (!categoriesExpanded && !ratedExpanded && !ingredientsExpanded && !helpExpanded){

                    Spacer(modifier = Modifier.width(40.dp))

                    SelectionBox(
                        categoriesExpanded = categoriesExpanded,
                        countriesExpanded = countriesExpanded,
                        ratedExpanded = ratedExpanded,
                        ingredientsExpanded = ingredientsExpanded,
                        isLoading = isLoading,
                        modifier = Modifier
                            .clickable {
                                countriesExpanded = !countriesExpanded
                                categoriesExpanded = false
                                ratedExpanded = false
                                ingredientsExpanded = false
                            },
                        viewModel = viewModel,
                        icon = Icons.Filled.Place,
                        name = "COUNTRIES",
                        context = context
                    )

                }
                if (!categoriesExpanded && !countriesExpanded && !ingredientsExpanded && !helpExpanded){

                    Spacer(modifier = Modifier.width(40.dp))

                    SelectionBox(
                        categoriesExpanded = categoriesExpanded,
                        countriesExpanded = countriesExpanded,
                        ratedExpanded = ratedExpanded,
                        ingredientsExpanded = ingredientsExpanded,
                        isLoading = isLoading,
                        modifier = Modifier
                            .clickable {
                                ratedExpanded = !ratedExpanded
                                categoriesExpanded = false
                                countriesExpanded = false
                                ingredientsExpanded = false
                                viewModel.ratingResults()
                            },
                        viewModel = viewModel,
                        icon = Icons.Filled.Star,
                        name = "TOP RATED",
                        context = context
                    )

                }
                if (!categoriesExpanded && !ratedExpanded && !countriesExpanded && !helpExpanded){

                    Spacer(modifier = Modifier.width(40.dp))

                    SelectionBox(
                        categoriesExpanded = categoriesExpanded,
                        countriesExpanded = countriesExpanded,
                        ratedExpanded = ratedExpanded,
                        ingredientsExpanded = ingredientsExpanded,
                        isLoading = isLoading,
                        modifier = Modifier
                            .clickable {
                                ingredientsExpanded = !ingredientsExpanded
                                categoriesExpanded = false
                                ratedExpanded = false
                                countriesExpanded = false
                                viewModel.addAllIngredients()
                            },
                        viewModel = viewModel,
                        icon = Icons.Filled.ShoppingCart,
                        name = "INGREDIENTS",
                        context = context
                    )
                }
            }
        }
    }
}
@Composable
fun SelectionBox(categoriesExpanded:Boolean,
                 countriesExpanded:Boolean,
                 ratedExpanded:Boolean,
                 ingredientsExpanded: Boolean,
                 isLoading:Boolean,
                 modifier: Modifier,
                 viewModel: MealViewModel,
                 icon: ImageVector,
                 name: String,
                 context:Context){

    Box(modifier = Modifier
        .fillMaxWidth()){
        Column (modifier = Modifier.padding(20.dp)){
            Box(modifier = modifier,
                contentAlignment = Alignment.Center){
                Row {
                    Icon(imageVector = icon, contentDescription =null,
                        tint = orange)
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = name, fontFamily = ThinkSmart, color = Color.White, textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.ExtraBold, fontSize = 24.sp)
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            if (countriesExpanded){
                CountryColumn()
            }
            if (categoriesExpanded) {
                if (isLoading){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else CategoryColumn(data = viewModel.Catstate.category, viewModel = viewModel)
            }
            if (ratedExpanded) {
                if (isLoading){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else RatedColumn(data = viewModel.ratedListState.ratedList, viewModel = viewModel, context)
            }
            if (ingredientsExpanded) {
                if (isLoading){
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else IngredientColumn(data = viewModel.ingredientList.ingredientList, viewModel = viewModel)
            }
        }
    }
}