package com.example.food_2_fork_kmm_my.di

import com.example.food_2_fork_kmm_my.interactors.recipe_detail.GetRecipe

class GetRecipeModule(
    private val cacheModule: CacheModule
) {

    val getRecipe by lazy {
        GetRecipe(
            recipeCache = cacheModule.recipeCache
        )
    }

}