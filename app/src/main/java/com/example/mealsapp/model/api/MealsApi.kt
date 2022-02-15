package com.example.mealsapp.model.api

import com.example.mealsapp.model.response.MealsCategoryResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class MealsWebservice {
    private lateinit var api:MealsApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //creates a retrofit client instance
        api = retrofit.create(MealsApi::class.java)
    }
    //the Call wrapper class allows us to call or execute our api and wait for a response.
    //Apart from receiving data we can also get additional Http information of whether our api call was successful or not.
    fun getMeals(): Call<MealsCategoryResponse> {
      return  api.getMeals()
    }
    interface MealsApi{
        @GET("categories.php")
        fun getMeals(): Call<MealsCategoryResponse>
    }
}