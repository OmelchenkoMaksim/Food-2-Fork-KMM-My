package com.example.food_2_fork_kmm_my.di

import com.example.food_2_fork_kmm_my.interactors.recipe_list.SearchRecipes

class SearchRecipesModule(
    val networkModule: NetworkModule,
    val cacheModule: CacheModule
) {
    val searchRecipes: SearchRecipes by lazy {
        SearchRecipes(
            recipeService = networkModule.recipeService,
            recipeCache = cacheModule.recipeCache
        )
    }


}