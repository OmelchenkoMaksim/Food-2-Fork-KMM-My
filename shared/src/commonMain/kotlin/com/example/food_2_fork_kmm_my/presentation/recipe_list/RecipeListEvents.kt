package com.example.food_2_fork_kmm_my.presentation.recipe_list

// TODO("We will be adding more events to this UI as the course progresses")
sealed class RecipeListEvents {

    object LoadRecipes: RecipeListEvents()

    object NewSearch: RecipeListEvents()

    object NextPage: RecipeListEvents()

    data class OnUpdateQuery(val query: String): RecipeListEvents()

}