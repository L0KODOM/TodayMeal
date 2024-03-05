package com.lokodom.todaymeal.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lokodom.todaymeal.data.Ingredient
import com.lokodom.todaymeal.viewmodel.MealViewModel

@Composable
fun IngredientColumn(data: List<Ingredient>?, viewModel : MealViewModel) {

    if (data != null){
        LazyColumn {
            items(data) { ingredient ->
                Box(modifier = Modifier.clickable { viewModel.selectIngredient(ingredient.name) }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.width(125.dp)) {

                            Text(text = ingredient.name, color = Color.White, fontSize = 20.sp,
                                textDecoration = TextDecoration.Underline)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        ingredient.type?.let { Text(text = it, color = Color.White) }
                    }

                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }

    }
}