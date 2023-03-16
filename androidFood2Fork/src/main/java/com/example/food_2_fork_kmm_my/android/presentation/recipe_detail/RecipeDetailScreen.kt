package com.example.food_2_fork_kmm_my.android.presentation.recipe_detail


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.food_2_fork_kmm_my.android.presentation.recipe_list.components.RecipeCard
import com.example.food_2_fork_kmm_my.android.presentation.theme.AppTheme
import com.example.food_2_fork_kmm_my.domain.model.Recipe

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalStdlibApi
@Composable
fun RecipeDetailScreen(
    recipe: Recipe?
//    state: RecipeDetailState,
//    onTriggerEvent: (RecipeDetailEvents) -> Unit, // this will be used later when we do the error handling
) {
    AppTheme(
        displayProgressBar = false,

        ) {
        if (recipe == null) {
            Text("Unable to get the details of this recipe...")
        } else {
            RecipeCard(recipe = recipe,
                onClick = {})
        }
    }
}