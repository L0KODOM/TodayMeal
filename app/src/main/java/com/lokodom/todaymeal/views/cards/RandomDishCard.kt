package com.lokodom.todaymeal.views.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lokodom.todaymeal.data.MealData
import com.lokodom.todaymeal.viewmodel.MealViewModel

@Composable
fun RandomDishCard(data: MealData?,
                   viewModel: MealViewModel
){
    val context = LocalContext.current

    Card (modifier = Modifier.clickable {
        if (data != null) {
            viewModel.selectName(data.name, context)
        }
    }){
        if (data != null) {
            Column (horizontalAlignment = Alignment.CenterHorizontally){
                AsyncImage(model = data.image, contentDescription = "image")
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(10.dp)){
                    Column {
                        Row {
                            Box(modifier = Modifier
                                .shadow(5.dp)
                                .background(Color.Magenta),
                                contentAlignment = Alignment.Center){
                                Text(text = "Name:  ")
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = data.name)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Box(modifier = Modifier
                                .shadow(5.dp)
                                .background(Color.Magenta),
                                contentAlignment = Alignment.Center){
                                Text(text = "Category:  ")
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = data.category!!)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Row {
                            Box(modifier = Modifier
                                .shadow(5.dp)
                                .background(Color.Magenta),
                                contentAlignment = Alignment.Center){
                                Text(text = "Country:  ")
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = data.country!!)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = { viewModel.saveFav() }) {
                            Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = "like")
                        }
                    }

                }
            }

        }

    }
}