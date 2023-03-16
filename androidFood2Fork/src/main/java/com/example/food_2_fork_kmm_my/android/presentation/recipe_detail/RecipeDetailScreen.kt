package com.example.food_2_fork_kmm_my.android.presentation.recipe_detail

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.food_2_fork_kmm_my.android.presentation.recipe_detail.components.RecipeView
import com.example.food_2_fork_kmm_my.android.presentation.recipe_list.components.RecipeCard
import com.example.food_2_fork_kmm_my.android.presentation.theme.AppTheme
import com.example.food_2_fork_kmm_my.domain.model.Recipe

@OptIn(ExperimentalStdlibApi::class)
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?,
){
    AppTheme(
        displayProgressBar = false
    ) {
        if(recipe == null){
            Text("Unable to get the details of this recipe...")
        }
        else{
            RecipeView(recipe = recipe)
        }
    }
}