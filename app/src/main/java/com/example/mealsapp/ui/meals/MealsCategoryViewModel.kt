package com.example.mealsapp.ui.meals

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.example.mealsapp.model.MealsRepository
import com.example.mealsapp.model.response.Category
import com.example.mealsapp.model.response.MealsCategoryResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// view model prepares data to be displayed by the view class.
//it holds the presentation logic. The view classes holds the UI logic.
// Helps the app to survives configuration changes
class MealsCategoryViewModel(private val repository: MealsRepository = MealsRepository()) :
    ViewModel() {
    //the remember function is not needed here because this object will live as long as the
    //view model lives. //  val rememberedMeals: MutableState<List<Category>> = remember { mutableStateOf(emptyList()) }
    val mealsState: MutableState<List<Category>> = mutableStateOf(emptyList())
    private val mealsJob = Job()
    private val coroutineCustomScope = CoroutineScope(mealsJob + Dispatchers.IO)

    init {
        coroutineCustomScope.launch {
            mealsState.value = getMeals()
        }
    }

    private suspend fun getMeals(): List<Category> {
        return repository.getMeals().categories
    }

    override fun onCleared() {
        super.onCleared()
        //since we are using a custom coroutineScope then we must cancel the job here
        //if we had used the viewModelScope then this would not have been necessary.
        mealsJob.cancel()
    }
}