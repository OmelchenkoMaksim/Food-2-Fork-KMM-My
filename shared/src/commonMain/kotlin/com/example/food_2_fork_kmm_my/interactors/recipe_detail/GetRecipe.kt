package com.example.food_2_fork_kmm_my.interactors.recipe_detail

import com.example.food_2_fork_kmm_my.datasource.cache.RecipeCache
import com.example.food_2_fork_kmm_my.domain.model.GenericMessageInfo
import com.example.food_2_fork_kmm_my.domain.model.Recipe
import com.example.food_2_fork_kmm_my.domain.model.UIComponentType
import com.example.food_2_fork_kmm_my.domain.util.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Retrieve a recipe from the cache given it's unique id.
 */
class GetRecipe (
    private val recipeCache: RecipeCache,
){
    fun execute(
        recipeId: Int
    ): Flow<DataState<Recipe>> = flow {
        emit(DataState.loading())
        try {
            emit(DataState.loading())

            val recipe =  recipeCache.get(recipeId)

            emit(DataState.data(message = null, data = recipe))

        }catch (e: Exception){
            emit(
                DataState.error<Recipe>(
                    message = GenericMessageInfo.Builder()
                        .id("GetRecipe.Error")
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description(e.message ?: "Unknown error")
                ))
        }
    }

}