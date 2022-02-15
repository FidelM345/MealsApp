package com.example.mealsapp.model.response


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("idCategory")
    val id: String,
    @SerializedName("strCategory")
    val name: String,
    @SerializedName("strCategoryDescription")
    val description: String,
    @SerializedName("strCategoryThumb")
    val thumbUrl: String
)

data class MealsCategoryResponse(
    @SerializedName("categories")
    val categories: List<Category>
)