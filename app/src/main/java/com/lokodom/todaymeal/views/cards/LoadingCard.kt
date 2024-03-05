package com.lokodom.todaymeal.views.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer

@Composable
fun LoadingCard(){
    Card (
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .width(353.dp)
            .testTag("loadingCard")
    ){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            ImageLoading()
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier
                .width(100.dp)
                .height(30.dp)
                , contentAlignment = Alignment.Center){
                Text(text = "Loading...")
                Spacer(modifier = Modifier.height(20.dp))
            }
        }

    }

}
@Composable
fun ImageLoading(){
    Box(modifier = Modifier
        .shimmer()
        .size(353.dp)
        .background(Color.Green),
        contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}