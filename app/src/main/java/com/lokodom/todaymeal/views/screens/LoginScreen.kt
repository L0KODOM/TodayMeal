package com.lokodom.todaymeal.views.screens

import android.content.Context
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import com.lokodom.todaymeal.R
import com.lokodom.todaymeal.navigation.AppScreens
import com.lokodom.todaymeal.viewmodel.Login.createUserWithEmail
import com.lokodom.todaymeal.viewmodel.Login.signInEmailAndPassword
import com.lokodom.todaymeal.viewmodel.Login.signInWithGoogleCredential
import com.lokodom.todaymeal.viewmodel.MealViewModel

val red = Color(220/255f, 61/255f, 61/255f, 1.0f)

@Composable
fun LoginScreen(navController: NavController,
                viewModel: MealViewModel = hiltViewModel()
){

    var email by remember{ mutableStateOf("") }
    var password by remember{ mutableStateOf("") }
    var changeLoginToRegister by remember{ mutableStateOf(true) }
    var seePassword by remember{ mutableStateOf(false) }

    val isLoading:Boolean by viewModel.isLoading.observeAsState(initial = false)

    //Login con Google
    val token = "267721350020-ooe178to146lp038qa295nethip44fuf.apps.googleusercontent.com"
    val context = LocalContext.current


    if (isLoading){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column (horizontalAlignment = Alignment.CenterHorizontally){
            Image(painter = painterResource(id = R.drawable.pngegg), contentDescription ="logo",
                modifier = Modifier
                    .size(150.dp)
                    .background(red)
                    .clip(CircleShape))
            Spacer(modifier = Modifier.height(20.dp))
            TextField(value = email, onValueChange = {email = it},
                label = { Text(text = "email")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))
            Spacer(modifier = Modifier.height(10.dp))
            TextField(value = password, onValueChange = {password = it} ,
                label = { Text(text = "password")},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "visisble",
                        modifier = Modifier.clickable { seePassword = !seePassword })
                },
                visualTransformation = if (seePassword) {
                    VisualTransformation.None
                }else PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Forgot password?")

            Spacer(modifier = Modifier.height(10.dp))

            LoginOrRegisterButton(viewModel = viewModel, context = context,
                changeLoginToRegister = changeLoginToRegister, email = email,
                password = password, navController= navController)

            Text(text = if (!changeLoginToRegister) {
                "Don't have an account yet? "
            }else{
                "Already have an account?"
            })

            Spacer(modifier = Modifier.width(5.dp))

            Text(text = if (!changeLoginToRegister) {
                "Register"
            }else{
                "Login"
            }, textDecoration = TextDecoration.Underline, color = Color.Blue,
                modifier = Modifier.clickable { changeLoginToRegister = !changeLoginToRegister})

            Spacer(modifier = Modifier.height(10.dp))

            GoogleSigning(token = token, viewModel = viewModel,
                navController = navController, context = context)
        }
    }
}
@Composable
fun LoginOrRegisterButton(viewModel: MealViewModel, context:Context, changeLoginToRegister:Boolean,
                          email:String,password:String,navController: NavController){
    Button(onClick = {if (!changeLoginToRegister) {
        viewModel.signInEmailAndPassword(context, email, password) {
            navController.navigate(
                AppScreens.RandomDishScreen.route
            )
        }
    }else{ viewModel.createUserWithEmail(email,password){navController.navigate(
        AppScreens.RandomDishScreen.route
    )}

    } },
        colors = ButtonDefaults.buttonColors(orange)
    ) {
        Text(text = if (!changeLoginToRegister) {
            "Login"
        }else{"Register"})
    }
}
@Composable
fun GoogleSigning(token:String, viewModel: MealViewModel,navController: NavController,context: Context){

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts
            .StartActivityForResult(),
    ){
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)
            val credential = GoogleAuthProvider.getCredential(account.idToken,null)
            viewModel.signInWithGoogleCredential(credential){
                navController.navigate(AppScreens.RandomDishScreen.route)
            }
        }catch (e:Exception){
            Log.d("FirebaseTutorial", "signIn con google falló ${e.localizedMessage}")

        }
    }

    Box (modifier = Modifier
        .padding(5.dp)
        .clickable {
            val options = GoogleSignInOptions
                .Builder(
                    GoogleSignInOptions.DEFAULT_SIGN_IN
                )
                .requestIdToken(token)
                .requestEmail()
                .build()
            val signInClient = GoogleSignIn.getClient(context, options)
            launcher.launch(signInClient.signInIntent)
        }){
        Row (verticalAlignment = Alignment.CenterVertically){
            Box(modifier = Modifier.size(50.dp)){
                Image(painter = painterResource(id = R.drawable.google), contentDescription = "google",
                    contentScale = ContentScale.FillBounds)
            }
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "Login con Google")
        }
    }
}