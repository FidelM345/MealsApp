package com.example.mealsapp.ui.meals
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.mealsapp.model.response.Category

@Composable
fun MealsCategoriesScreen() {
    //the view model will be alive as long as the composable is alive
    //if the composable is recomposed. It will be provided the same viewmodel instance.
    val viewModel: MealsCategoryViewModel = viewModel()
    val meals = viewModel.mealsState.value


    LazyColumn(contentPadding = PaddingValues(all = 8.dp)) {
        items(meals) { category ->
            MealCategory(category = category)
        }
    }
}

@Composable
fun MealCategory(category: Category) {
    var isExpanded by remember { mutableStateOf(false) }
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        //modifier = Modifier.animateContentSize() - will animate contents of our recyclerview if any
        //of the children elements experience a change in size.
        Row(modifier = Modifier.animateContentSize()) {
// Basic
            Image(
                painter = rememberImagePainter(category.thumbUrl),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(72.dp)
                    .padding(4.dp)
            )

            DisplayTextComposables(category, isExpanded)

            Icon(imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else
                Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand row icon",
                modifier = Modifier
                    .padding(16.dp)
                    .align(
                        if (isExpanded) Alignment.Bottom
                        else
                            Alignment.CenterVertically
                    )
                    .clickable {
                        isExpanded = !isExpanded
                    }
                //  .align(Alignment.CenterVertically)
            )
        }


    }

}

@Composable
private fun DisplayTextComposables(
    category: Category,
    isExpanded: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(16.dp)
    ) {
        Text(
            text = category.name,
            style = MaterialTheme.typography.h6,
            //  fontSize = 18.sp,
//                    modifier = Modifier
//                        .padding(start = 12.dp)
//                        .align(Alignment.CenterHorizontally)
        )
        //makes the text composable grey
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = category.description,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                maxLines = if (isExpanded) 10 else 4,
            )
        }

    }
}








