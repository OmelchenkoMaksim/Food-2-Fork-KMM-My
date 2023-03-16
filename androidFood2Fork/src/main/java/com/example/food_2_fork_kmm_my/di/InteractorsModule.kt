package com.example.food_2_fork_kmm_my.di

import com.example.food_2_fork_kmm_my.datasource.network.RecipeService
import com.example.food_2_fork_kmm_my.interactors.recipe_list.SearchRecipes
import com.example.food_2_fork_kmm_my.interactors.recipe_detail.GetRecipe
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import com.example.food_2_fork_kmm_my.datasource.cache.RecipeCache

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService,
        recipeCache: RecipeCache
    ): SearchRecipes {
        return SearchRecipes(
            recipeService = recipeService,
            recipeCache = recipeCache
        )
    }

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeService: RecipeService
    ): GetRecipe {
        return GetRecipe(recipeService = recipeService)
    }
}