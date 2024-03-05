package com.lokodom.todaymeal.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.lokodom.todaymeal.viewmodel.MealViewModel

data class Country(val name: String, val image: String)

val countries: List<Country> = listOf(
    Country("Spanish  ", "https://cdn.pixabay.com/photo/2012/04/16/12/06/flag-35697_960_720.png"),
    Country("Greek ", "https://flagsworld.org/img/cflags/Greece-flag.png"),
    Country("Italian  ", "https://www.mtonauticastore.es/data/prod/img/bandiera_italia_cm_20x30.jpg"),
    Country("French   ", "https://img.freepik.com/vector-premium/bandera-francesa-simbolo-nacion_395514-32.jpg"),
    Country("British  ", "https://upload.wikimedia.org/wikipedia/en/thumb/a/ae/Flag_of_the_United_Kingdom.svg/1200px-Flag_of_the_United_Kingdom.svg.png"),
    Country("Chinese  ", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Zeng_Liansong%27s_proposal_for_the_PRC_flag.svg/220px-Zeng_Liansong%27s_proposal_for_the_PRC_flag.svg.png"),
    Country("Canadian ", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Flag_of_Canada_%28Pantone%29.svg/800px-Flag_of_Canada_%28Pantone%29.svg.png"),
    Country("Jamaican ", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/Flag_of_Jamaica.svg/1200px-Flag_of_Jamaica.svg.png"),
    Country("Dutch    ", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/20/Flag_of_the_Netherlands.svg/1280px-Flag_of_the_Netherlands.svg.png"),
    Country("Malaysian", "https://img.freepik.com/vector-premium/bandera-malasia-vector_671352-123.jpg"),
    Country("American ", "https://miro.medium.com/v2/resize:fit:1200/0*o0-6o1W1DKmI5LbX.png"),
    Country("Egyptian ", "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fe/Flag_of_Egypt.svg/2560px-Flag_of_Egypt.svg.png"),
    Country("Croatian ", "https://cdn.abicart.com/shop/19667/art67/h6972/143306972-origpic-6d8197.jpg"),
    Country("Irish ", "https://upload.wikimedia.org/wikipedia/commons/thumb/4/45/Flag_of_Ireland.svg/2560px-Flag_of_Ireland.svg.png"),
)

@Composable
fun CountryColumn(
    viewModel: MealViewModel = hiltViewModel()
){

    LazyColumn(content = {
        items(countries){data ->
            Box(modifier = Modifier
                .clickable {
                    viewModel.selectCountry(data.name)
                }){
                Row (verticalAlignment = Alignment.CenterVertically){
                    Box(modifier = Modifier.width(100.dp)){
                        Text(text = data.name, color = Color.White)
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(modifier = Modifier
                        .width(125.dp)
                        .height(50.dp)){
                        AsyncImage(model = data.image,
                            contentDescription = null, modifier = Modifier.size(50.dp),
                            contentScale = ContentScale.FillBounds)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(20.dp))
        }
    })
}