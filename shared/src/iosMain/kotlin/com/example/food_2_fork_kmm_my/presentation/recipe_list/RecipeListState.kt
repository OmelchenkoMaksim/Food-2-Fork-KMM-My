package com.example.food_2_fork_kmm_my.presentation.recipe_list

import com.example.food_2_fork_kmm_my.domain.model.Recipe

actual data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val recipes: List<Recipe> = listOf(),
    val bottomRecipe: Recipe? = null, // track the recipe at the bottom of the list so we know when to trigger pagination
)