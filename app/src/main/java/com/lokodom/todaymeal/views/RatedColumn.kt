package com.lokodom.todaymeal.views

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lokodom.todaymeal.R
import com.lokodom.todaymeal.database.Rating
import com.lokodom.todaymeal.viewmodel.MealViewModel

@Composable
fun RatedColumn(data: List<Rating>?, viewModel : MealViewModel, context:Context) {

    if (data != null){

        LazyColumn(content = {
            items(data){ meal ->
                Card{
                    Box(modifier = Modifier
                        .clickable { viewModel.selectName(meal.name, context) }
                        .border(1.dp, Color.Gray)){
                        Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(text = meal.name, textDecoration = TextDecoration.Underline,
                                fontSize = 20.sp)
                            Spacer(modifier = Modifier.height(10.dp))

                            val ratingValue = when (meal.rating.toDouble()) {
                                in Double.MIN_VALUE..2.0 -> R.drawable.estrellas
                                in 2.0..3.0 -> R.drawable.estrellas2
                                in 3.0..4.0 -> R.drawable.estrellas3
                                in 4.0..5.0 -> R.drawable.estrellas4
                                5.0 -> R.drawable.estrellas5
                                else -> R.drawable.estrellas}

                            Image(painter = painterResource(id =ratingValue
                            ), contentDescription = null, modifier = Modifier.width(150.dp).height(20.dp))

                        }
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
            }
        })
    }
}