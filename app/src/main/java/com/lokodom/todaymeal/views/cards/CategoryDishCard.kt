package com.lokodom.todaymeal.views.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lokodom.todaymeal.data.MealData
import com.lokodom.todaymeal.viewmodel.MealViewModel

@Composable
fun CategoryDishCard(
    data: List<MealData>?,
    viewModel : MealViewModel
){
    val context = LocalContext.current

    LazyColumn(content = {
        items(data!!){meal ->
            Card {
                Box(modifier = Modifier
                    .clickable { viewModel.selectName(meal.name, context) }
                    .border(1.dp, Color.Gray)){
                    Column (horizontalAlignment = Alignment.CenterHorizontally){
                        AsyncImage(model = meal.image, contentDescription = "image")
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(modifier = Modifier.width(200.dp)){
                            Text(text = meal.name, textDecoration = TextDecoration.Underline)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    })

}