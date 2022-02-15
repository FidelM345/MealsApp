package com.example.mealsapp.ui.meals

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mealsapp.model.response.Category
import com.example.mealsapp.model.response.MealsCategoryResponse
import com.example.mealsapp.ui.theme.MealsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealsAppTheme {
                MealsCategoryScreen(name = "I am the Android Guru")
            }
        }
    }
}

@Composable
fun MealsCategoryScreen(name: String) {
    //the view model will be alive as long as the composable is alive
    //if the composable is recomposed. It will be provided the same viewmodel instance.
    val viewModel:MealsCategoryViewModel = viewModel()
    val rememberedMeals:MutableState<List<Category>> = remember{mutableStateOf(emptyList())}

    viewModel.getMeals {
        val mealsFromApi=it?.categories
        rememberedMeals.value = mealsFromApi.orEmpty() //returns empty list if null
    }
    LazyColumn{
        items(rememberedMeals.value){
            category ->
            Text(text = category.name, fontSize = 18.sp, modifier = Modifier.padding(start = 12.dp, bottom = 18.dp))
        }
    }
   // Text(text = rememberedMeals.value.toString())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealsAppTheme {
        MealsCategoryScreen("Android")
    }
}