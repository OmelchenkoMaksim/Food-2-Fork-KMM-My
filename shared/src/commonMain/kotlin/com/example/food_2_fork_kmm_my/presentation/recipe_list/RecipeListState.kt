package com.example.food_2_fork_kmm_my.presentation.recipe_list

import com.example.food_2_fork_kmm_my.domain.model.Recipe

data class RecipeListState(
    val isLoading: Boolean = false,
    val page: Int = 1,
    val query: String = "",
    val selectedCategory:FoodCategory? = null,
    val recipes: List<Recipe> = listOf(),
)
