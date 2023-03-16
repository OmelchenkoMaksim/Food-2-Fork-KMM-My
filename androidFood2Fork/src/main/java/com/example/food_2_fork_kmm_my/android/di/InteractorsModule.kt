package com.example.food_2_fork_kmm_my.android.di

import com.example.food_2_fork_kmm_my.datasource.cache.RecipeCache
import com.example.food_2_fork_kmm_my.datasource.network.RecipeService
import com.example.food_2_fork_kmm_my.interactors.recipe_detail.GetRecipe
import com.example.food_2_fork_kmm_my.interactors.recipe_list.SearchRecipes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideSearchRecipes(
        recipeService: RecipeService,
        recipeCache: RecipeCache,
    ): SearchRecipes{
        return SearchRecipes(
            recipeService = recipeService,
            recipeCache = recipeCache
        )
    }

    @Singleton
    @Provides
    fun provideGetRecipe(
        recipeCache: RecipeCache,
    ): GetRecipe{
        return GetRecipe(recipeCache = recipeCache)
    }
}




