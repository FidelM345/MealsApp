package com.example.mealsapp.model

import com.example.mealsapp.model.api.MealsWebservice
import com.example.mealsapp.model.response.MealsCategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository(private val webservice: MealsWebservice = MealsWebservice()) {
    fun getMeals(successCallBack: (response: MealsCategoryResponse?) -> Unit) =
    //the Call wrapper class provides us with two methods.
    // 1. For executes the api call synchronously hence blocks the main thread in android - execute()
        // 2. Executes the api call asynchronously hence does not block the android UI thread - enqueue()
        webservice.getMeals().enqueue(object : Callback<MealsCategoryResponse> {
            override fun onResponse(
                call: Call<MealsCategoryResponse>,
                response: Response<MealsCategoryResponse>
            ) {
                if (response.isSuccessful)
                    successCallBack(response.body())
            }

            override fun onFailure(call: Call<MealsCategoryResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
}