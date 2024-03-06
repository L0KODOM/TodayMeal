package com.lokodom.todaymeal.views.cards


import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.lokodom.todaymeal.R
import com.lokodom.todaymeal.data.MealData
import com.lokodom.todaymeal.viewmodel.MealViewModel
import com.lokodom.todaymeal.viewmodel.translate.translation
import com.lokodom.todaymeal.viewmodel.translate.translationGreek
import com.lokodom.todaymeal.views.screens.magenta
import com.lokodom.todaymeal.views.screens.orange

@Composable
fun DishCard(data: MealData?, viewModel: MealViewModel, context: Context) {

    val scrollState = rememberScrollState()

    if (data != null) {
        Card  (modifier = Modifier
            .padding(10.dp)){

            val link = data.youtube
            val linkIntent = remember{ Intent(Intent.ACTION_VIEW, Uri.parse(link)) }
            val textList = listOf<String>(data.name,data.category!!,data.country!!,
                "${data.ingredient1} - ${data.measure1}",
                "${data.ingredient2} - ${data.measure2}",
                "${data.ingredient3} - ${data.measure3}",
                "${data.ingredient4} - ${data.measure4}",
                "${data.ingredient5} - ${data.measure5}",
                "${data.ingredient6} - ${data.measure6}",
                "${data.ingredient7} - ${data.measure7}",
                "${data.ingredient8} - ${data.measure8}",
                "${data.ingredient9} - ${data.measure9}",
                "${data.ingredient10} - ${data.measure10}",
                "${data.ingredient11} - ${data.measure11}",
                "${data.ingredient12} - ${data.measure12}",
                "${data.ingredient13} - ${data.measure13}",
                "${data.ingredient14} - ${data.measure14}",
                "${data.ingredient15} - ${data.measure15}",
            )
            var translated by remember{ mutableStateOf(false) }
            var translatedGreek by remember{ mutableStateOf(false) }


            Column (horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(scrollState)){

                AsyncImage(model = data.image, contentDescription = "image")

                Spacer(modifier = Modifier.height(20.dp))

                Box(modifier = Modifier.padding(10.dp)){

                    Column {
                        Row {
                            Box(modifier = Modifier
                                .height(15.dp)
                                .width(25.dp)
                                .clickable {
                                    viewModel.translation(textList, context)
                                    translated = true
                                }){
                                AsyncImage(model ="https://cdn.pixabay.com/photo/2012/04/16/12/06/flag-35697_960_720.png" , contentDescription = "translate",
                                    contentScale = ContentScale.FillBounds)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(modifier = Modifier
                                .height(15.dp)
                                .width(25.dp)
                                .clickable {
                                    translated = false
                                    translatedGreek = false
                                }){
                                AsyncImage(model ="https://upload.wikimedia.org/wikipedia/en/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1200px-Flag_of_the_United_Kingdom.svg.png",
                                    contentDescription = "translate",
                                    contentScale = ContentScale.FillBounds)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(modifier = Modifier
                                .height(15.dp)
                                .width(25.dp)
                                .clickable {
                                    viewModel.translationGreek(textList, context)
                                    translatedGreek = true
                                }){
                                AsyncImage(model ="https://flagsworld.org/img/cflags/Greece-flag.png" , contentDescription = "translate",
                                    contentScale = ContentScale.FillBounds)
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))

                        Row {
                            Box(modifier = Modifier
                                .shadow(5.dp)
                                .background(orange)
                                .padding(5.dp)){
                                Text(text = if (translated) {
                                    "Nombre:  "
                                }else if (translatedGreek){
                                    "όνομα"
                                }else "Name:  ", fontWeight = FontWeight.ExtraBold)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(modifier = Modifier
                                .padding(5.dp)
                                .width(200.dp)){
                                Text(text = if (!translated && !translatedGreek) {
                                    data.name
                                }else viewModel.translateState.value.translatedName)
                            }

                        }

                        Spacer(modifier = Modifier.height(10.dp))


                        Row {
                            Box(modifier = Modifier
                                .shadow(5.dp)
                                .background(orange)
                                .padding(5.dp)){
                                Text(text = if (translated) {
                                    "Categoría:  "
                                }else if (translatedGreek){
                                    "κατηγορία"
                                }else "Category:  ", fontWeight = FontWeight.ExtraBold)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(modifier = Modifier.padding(5.dp)){
                                Text(text = if (!translated && !translatedGreek) {
                                    data.category
                                }else viewModel.translateState.value.translatedCategory)
                            }
                        }


                        Spacer(modifier = Modifier.height(10.dp))

                        Row {
                            Box(modifier = Modifier
                                .shadow(5.dp)
                                .background(orange)
                                .padding(5.dp),){
                                Text(text = if (translated) {
                                    "País:  "
                                }else if (translatedGreek){
                                    "Χώρα"
                                }else "Country:  ", fontWeight = FontWeight.ExtraBold)
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Box(modifier = Modifier.padding(5.dp)){
                                Text(text = if (!translated && !translatedGreek) {
                                    data.country
                                }else viewModel.translateState.value.translatedCountry)
                            }
                        }

                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(text = "Video", modifier = Modifier.clickable { context.startActivity(linkIntent) },
                    color = orange, textDecoration = TextDecoration.Underline)
                Spacer(modifier = Modifier.height(20.dp))


                Box(modifier = Modifier
                    .width(100.dp)
                    .height(50.dp)
                    .border(shape = RoundedCornerShape(20), width = 2.dp, color = orange),
                    contentAlignment = Alignment.Center){
                    Text(text = if (translated) {
                        "RECETA"
                    }else if (translatedGreek){
                        "συνταγή"
                    }else "RECIPE", fontWeight = FontWeight.ExtraBold)
                }

                Spacer(modifier = Modifier.height(10.dp))
                Box(modifier = Modifier.padding(10.dp)){
                    Column {
                        Text(text = if (!translated && !translatedGreek) {
                            "${data.ingredient1} - ${data.measure1}"
                        }else viewModel.translateState.value.translatedIng1)
                        Text(text = if (!translated && !translatedGreek) {
                            "${data.ingredient2} - ${data.measure2}"
                        }else viewModel.translateState.value.translatedIng2)
                        Text(text = if (!translated && !translatedGreek) {
                            "${data.ingredient3} - ${data.measure3}"
                        }else viewModel.translateState.value.translatedIng3)
                        Text(text = if (!translated && !translatedGreek) {
                            "${data.ingredient4} - ${data.measure4}"
                        }else viewModel.translateState.value.translatedIng4)
                        Text(text = if (!translated && !translatedGreek) {
                            "${data.ingredient5} - ${data.measure5}"
                        }else viewModel.translateState.value.translatedIng5)

                        if (data.ingredient6 != null && data.ingredient6 != ""){
                            (if (!translated && !translatedGreek) {
                                "${data.ingredient6} - ${data.measure6}"
                            }else viewModel.translateState.value.translatedIng6)?.let { Text(text = it) }
                        }

                        if (data.ingredient7 != null && data.ingredient7 != ""){
                            (if (!translated && !translatedGreek) {
                                "${data.ingredient7} - ${data.measure7}"
                            }else viewModel.translateState.value.translatedIng7)?.let { Text(text = it) }
                        }

                        if (data.ingredient8 != null && data.ingredient8 != ""){
                            (if (!translated && !translatedGreek) {
                                "${data.ingredient8} - ${data.measure8}"
                            }else viewModel.translateState.value.translatedIng8)?.let { Text(text = it) }
                        }

                        if (data.ingredient9 != null && data.ingredient9 != ""){
                            (if (!translated && !translatedGreek) {
                                "${data.ingredient9} - ${data.measure9}"
                            }else viewModel.translateState.value.translatedIng9)?.let { Text(text = it) }
                        }

                        if (data.ingredient10 != null && data.ingredient10 != ""){
                            (if (!translated && !translatedGreek) {
                                "${data.ingredient10} - ${data.measure10}"
                            }else viewModel.translateState.value.translatedIng10)?.let { Text(text = it) }
                        }

                        if (data.ingredient11 != null && data.ingredient11 != ""){
                            (if (!translated && !translatedGreek) {
                                "${data.ingredient11} - ${data.measure11}"
                            }else viewModel.translateState.value.translatedIng11)?.let { Text(text = it) }
                        }

                        if (data.ingredient12 != null && data.ingredient12 != ""){
                            (if (!translated && !translatedGreek) {
                                "${data.ingredient12} - ${data.measure12}"
                            }else viewModel.translateState.value.translatedIng12)?.let { Text(text = it) }
                        }

                        if (data.ingredient13 != null && data.ingredient13 != ""){
                            (if (!translated && !translatedGreek) {
                                "${data.ingredient13} - ${data.measure13}"
                            }else viewModel.translateState.value.translatedIng13)?.let { Text(text = it) }
                        }

                        if (data.ingredient14 != null && data.ingredient14!= ""){
                            (if (!translated && !translatedGreek) {
                                "${data.ingredient14} - ${data.measure14}"
                            }else viewModel.translateState.value.translatedIng14)?.let { Text(text = it) }
                        }

                        if (data.ingredient15 != null && data.ingredient15!= ""){
                            (if (!translated && !translatedGreek) {
                                "${data.ingredient15} - ${data.measure15}"
                            }else viewModel.translateState.value.translatedIng15)?.let { Text(text = it) }
                        }

                    }
                }
                Button(onClick = { viewModel.saveFav() }, colors = ButtonDefaults.buttonColors(containerColor = magenta)) {
                    Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = "like")
                }
                Spacer(modifier = Modifier.height(10.dp))
                val rating = viewModel.ratingState.stars
                Box{
                    Image(painter = painterResource(id = rating), contentDescription = "rating",
                        modifier = Modifier
                            .width(150.dp)
                            .height(20.dp))
                    Box(modifier = Modifier
                        .width(150.dp)
                        .height(20.dp)){
                        Row (modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceBetween){
                            Button(onClick = { viewModel.changeRating(R.drawable.estrellas, data.name,
                                data.image) },
                                modifier = Modifier.size(20.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                                Text(text = "1")
                            }
                            Button(onClick = { viewModel.changeRating(R.drawable.estrellas2, data.name,
                                data.image) },
                                modifier = Modifier.size(20.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                                Text(text = "2")
                            }
                            Button(onClick = { viewModel.changeRating(R.drawable.estrellas3, data.name,
                                data.image )},
                                modifier = Modifier.size(20.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                                Text(text = "3")
                            }
                            Button(onClick = { viewModel.changeRating(R.drawable.estrellas4, data.name,
                                data.image )},
                                modifier = Modifier.size(20.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                                Text(text = "4")
                            }
                            Button(onClick = { viewModel.changeRating(R.drawable.estrellas5, data.name,
                                data.image) },
                                modifier = Modifier.size(20.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                                Text(text = "5")
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }
}