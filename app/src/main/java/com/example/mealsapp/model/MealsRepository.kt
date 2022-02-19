package com.example.mealsapp.model

import com.example.mealsapp.model.api.MealsWebservice
import com.example.mealsapp.model.response.MealsCategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webservice: MealsWebservice = MealsWebservice()) {
    suspend fun getMeals():MealsCategoryResponse =
    //the Call wrapper class provides us with two methods.
    // 1. For executes the api call synchronously hence blocks the main thread in android - execute()
        // 2. Executes the api call asynchronously hence does not block the android UI thread - enqueue()
        webservice.getMeals()
}