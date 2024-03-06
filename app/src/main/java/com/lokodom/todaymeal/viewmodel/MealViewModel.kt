package com.lokodom.todaymeal.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.lokodom.todaymeal.database.FavouriteMeals
import com.lokodom.todaymeal.repository.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
import com.lokodom.todaymeal.R
import com.lokodom.todaymeal.database.Rating
import com.lokodom.todaymeal.database.User
import com.lokodom.todaymeal.viewmodel.State.CategoriesState
import com.lokodom.todaymeal.viewmodel.State.IngredientListState
import com.lokodom.todaymeal.viewmodel.State.MealList
import com.lokodom.todaymeal.viewmodel.State.MealState
import com.lokodom.todaymeal.viewmodel.State.RatedListState
import com.lokodom.todaymeal.viewmodel.State.RatingState
import com.lokodom.todaymeal.viewmodel.State.TranslateState

@HiltViewModel
class MealViewModel @Inject constructor(
    private val repository: MealRepository
) : ViewModel() {

    var state by mutableStateOf(MealState())
        private set

    var ListState by mutableStateOf(MealList())
        private set

    val favs: LiveData<List<FavouriteMeals>> by lazy { repository.getFavouriteMeals() }

    var Catstate by mutableStateOf(CategoriesState())
        private set

    var ingredientList by mutableStateOf(IngredientListState())
        private set

    var ratedListState by mutableStateOf(RatedListState())
        private set

    val auth: FirebaseAuth = Firebase.auth

    val _isLoading: MutableLiveData<Boolean>
            by lazy { MutableLiveData<Boolean>(false) }

    val isLoading: LiveData<Boolean> get()=_isLoading

    val _state = mutableStateOf(TranslateState())

    var ratingState by mutableStateOf(RatingState())
        private set

    val translateState : State<TranslateState> = _state

    fun addDish(){
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO){
                _isLoading.postValue(true)
                state = state.copy(mealData = repository.getRandomMeal())
                _isLoading.postValue(false)
            }
    }

    fun addCategories(){
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO){
                _isLoading.postValue(true)
                Catstate = Catstate.copy(category  = repository.getCategoryList())
                _isLoading.postValue(false)
            }
    }

    fun addAllIngredients(){
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO){
                _isLoading.postValue(true)
                ingredientList = ingredientList.copy(ingredientList  = repository.getIngredientList())
                _isLoading.postValue(false)
            }
    }

    fun selectCountry(country:String){
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO){
                _isLoading.postValue(true)
                ListState = ListState.copy(list = repository.getByArea(country))
                _isLoading.postValue(false)
            }
    }

    fun selectCategory(cat: String){
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO){
                _isLoading.postValue(true)
                ListState = ListState.copy(list = repository.getByCategory(cat))
                _isLoading.postValue(false)
            }
    }

    fun selectIngredient(ingredient: String){
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO){
                _isLoading.postValue(true)
                ListState = ListState.copy(list = repository.getByIngredient(ingredient))
                _isLoading.postValue(false)
            }
    }

    fun selectName(name: String, contxt: Context){
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO){
                try {
                    _isLoading.postValue(true)
                    state = state.copy(mealData = repository.getByName(name))
                    _isLoading.postValue(false)
                } catch (e: Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(contxt, "Not Found", Toast.LENGTH_SHORT).show()
                        _isLoading.postValue(false)
                    }
                }
            }
    }

    fun removeMealDataFromScreen(){
        viewModelScope.launch {
            state = state.copy(mealData = null)
        }
    }

    fun saveFav(){

        viewModelScope.launch (Dispatchers.IO){
            repository.insertMeal(state.mealData)
        }
    }

    fun deleteFav(toDelete: FavouriteMeals){
        viewModelScope.launch (Dispatchers.IO) {
            repository.deleteMeal(toDelete)
        }
    }

    private fun getNotificationToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("FirebaseTutorial", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            val tokenStr = token.toString()


            // Log and toast
            Log.d("Token", tokenStr)
        })
    }

    fun ratingResults(){
        viewModelScope.launch {
            try {
                val ratingResults = repository.getRatingResults()
                ratedListState = ratedListState.copy(ratedList  = ratingResults)
            } catch (e: Exception) {
                Log.d("error","${e.message}")
            }
        }
    }

    fun changeRating(num: Int, name: String, image: String){
        viewModelScope.launch {
            ratingState = ratingState.copy(stars = num)
        }

        val ratingValue = when (num) {
            R.drawable.estrellas -> "1"
            R.drawable.estrellas2 -> "2"
            R.drawable.estrellas3 -> "3"
            R.drawable.estrellas4 -> "4"
            R.drawable.estrellas5 -> "5"
            else -> "Unknown"
        }

        val rating = Rating(
            name = name,
            rating = ratingValue,
            image = image,
            votes = "1"
        ).toMap()

        FirebaseFirestore.getInstance().collection("ratings")
            .add(rating)
            .addOnSuccessListener {
                Log.d("FirebaseTutorial", "Created ${it.id}")
            }.addOnFailureListener {
                Log.d("FirebaseTutorial", "Error ${it}")
            }
    }


}