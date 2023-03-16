package com.example.food_2_fork_kmm_my.presentation.recipe_list

sealed class RecipeListEvents {

    object LoadRecipes : RecipeListEvents()

    object NextPage : RecipeListEvents()

    object NewSearch : RecipeListEvents()

    data class OnUpdateQuery(val query: String) : RecipeListEvents()
}
