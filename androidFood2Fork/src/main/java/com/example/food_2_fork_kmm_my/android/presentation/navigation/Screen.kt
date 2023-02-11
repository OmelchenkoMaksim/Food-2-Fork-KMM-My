package com.example.food_2_fork_kmm_my.android.presentation.navigation

sealed class Screen(
    val route: String,
) {
    object RecipeList : Screen("recipeList")

    object RecipeDetail : Screen("recipeDetail")
}