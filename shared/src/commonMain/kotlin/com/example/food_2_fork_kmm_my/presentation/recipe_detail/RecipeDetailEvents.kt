package com.example.food_2_fork_kmm_my.presentation.recipe_detail

sealed class RecipeDetailEvents {

    data class GetRecipe(val recipeId: Int) : RecipeDetailEvents()

    object OnRemoveHeadMessageFromQueue : RecipeDetailEvents()
}