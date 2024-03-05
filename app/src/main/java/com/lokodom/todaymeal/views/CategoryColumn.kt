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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lokodom.todaymeal.data.Category
import com.lokodom.todaymeal.viewmodel.MealViewModel

@Composable
fun CategoryColumn(data: List<Category>?, viewModel : MealViewModel) {

    if (data != null){
        LazyColumn {
            items(data) { category ->
                Box(modifier = Modifier.clickable { viewModel.selectCategory(category.name) }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.width(125.dp)) {

                            Text(text = category.name, color = Color.White)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        AsyncImage(model = category.image, contentDescription = "Image")
                    }

                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }

    }
}