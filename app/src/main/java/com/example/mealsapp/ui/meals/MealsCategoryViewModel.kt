package com.example.mealsapp.ui.meals

import androidx.lifecycle.ViewModel
import com.example.mealsapp.model.MealsRepository
import com.example.mealsapp.model.response.Category
import com.example.mealsapp.model.response.MealsCategoryResponse

// view model prepares data to be displayed by the view class.
//it holds the presentation logic. The view classes holds the UI logic.
// Helps the app to survives configuration changes
class MealsCategoryViewModel(private val repository: MealsRepository= MealsRepository()):ViewModel() {
    fun getMeals(successCallBack: (response: MealsCategoryResponse?) -> Unit) = repository.getMeals{
        response->
        successCallBack(response)
    }
}