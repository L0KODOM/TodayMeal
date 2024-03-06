package com.lokodom.todaymeal.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lokodom.todaymeal.viewmodel.MealViewModel
import com.lokodom.todaymeal.views.screens.orange

@Composable
fun FavList(viewModel: MealViewModel){

    val favMeals by viewModel.favs.observeAsState(arrayListOf())

    val context = LocalContext.current


    LazyColumn(modifier = Modifier.padding(top = 100.dp),content = {
        var itemCount = favMeals.size

        items(count = itemCount){
                index -> var auxIndex = index;

            val meal = favMeals[auxIndex]
            Card {
                if (meal != null) {
                    Column (horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { meal.name?.let { viewModel.selectName(it, context) } }) {
                        AsyncImage(model = meal.image, contentDescription = "image")
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(modifier = Modifier.padding(10.dp)){
                            Column {
                                Row {
                                    Box(modifier = Modifier
                                        .shadow(5.dp)
                                        .background(orange)
                                        .padding(5.dp)){
                                        Text(text = "Name: ", fontWeight = FontWeight.ExtraBold)
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Box(modifier = Modifier.padding(5.dp)){
                                        Text(text = meal.name!!)
                                    }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Row {
                                    Box(modifier = Modifier
                                        .shadow(5.dp)
                                        .background(orange)
                                        .padding(5.dp)){
                                        Text(text = "Category: ", fontWeight = FontWeight.ExtraBold)
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Box(modifier = Modifier.padding(5.dp)){
                                        Text(text = meal.category!!)
                                    }
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Row {
                                    Box(modifier = Modifier
                                        .shadow(5.dp)
                                        .background(orange)
                                        .padding(5.dp)){
                                        Text(text = "Country: ", fontWeight = FontWeight.ExtraBold)
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Box(modifier = Modifier.padding(5.dp)){
                                        Text(text = meal.country!!)
                                    }
                                }
                            }

                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Button(onClick = { viewModel.deleteFav(meal) }, colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                            Icon(imageVector = Icons.Filled.Delete, contentDescription = "like")
                        }
                    }

                }

            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    })

}