package com.lokodom.todaymeal.viewmodel.Login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthCredential
import com.google.firebase.firestore.FirebaseFirestore
import com.lokodom.todaymeal.database.User
import com.lokodom.todaymeal.viewmodel.MealViewModel
import kotlinx.coroutines.launch

fun MealViewModel.signInWithGoogleCredential(credential: AuthCredential, home:() -> Unit)
        = viewModelScope.launch {
    try {

        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful){
                    Log.d("FirebaseTutorial", "Exito")
                    home()
                    //getNotificationToken()
                    //val token = FirebaseMessaging.getInstance().token.result.toString()
                }
            }
            .addOnFailureListener {
                Log.d("FirebaseTutorial", "fallo con google ${it}")
            }

    }catch (e: Exception){
        Log.d("FirebaseTutorial", "fallo sin completar ${e.localizedMessage}")
    }
}

fun MealViewModel.signInEmailAndPassword(context: Context, email:String, password:String, home: ()->Unit)=
    viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        println("succesful")
                        home()
                    }
                    else{
                        println(it.result.toString())
                        Toast.makeText(context,
                            "No registrado",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }
        catch (e: Exception){
            println(e.message)
            Toast.makeText(context,
                "No registrado",
                Toast.LENGTH_SHORT).show()
        }
    }

fun MealViewModel.createUserWithEmail(email:String, password:String, home: ()->Unit) {
    if (_isLoading.value == false) {
        _isLoading.postValue(true)
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val userName = it.result.user?.email?.split("@")?.get(0)

                        createUser(userName)

                        home()
                    } else {
                        Log.d("login", "Fallo en el inicio de sesión")
                    }
                    _isLoading.postValue(false)
                }
        }catch (e:Exception) {
            Log.d("login", "${e.message }")
        }

    }
}

private fun MealViewModel.createUser(name: String?){
    val userId = auth.currentUser?.uid

    val user = User(
        userId = userId.toString(),
        name = name.toString(),
        quote = "la madre q me pario",
        id = null
    ).toMap()

    FirebaseFirestore.getInstance().collection("users")
        .add(user)
        .addOnSuccessListener {
            Log.d("FirebaseTutorial", "Created ${it.id}")
        }.addOnFailureListener {
            Log.d("FirebaseTutorial", "Error ${it}")
        }
}