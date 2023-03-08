package com.example.food_2_fork_kmm_my.interactors.recipe_detail

import com.example.food_2_fork_kmm_my.datasource.network.RecipeService
import com.example.food_2_fork_kmm_my.domain.model.Recipe
import com.example.food_2_fork_kmm_my.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRecipe(
    private val recipeService: RecipeService // we will change this to cache later
) {

    fun execute(
        recipeId: Int
    ): Flow<DataState<Recipe>> = flow {
        emit(DataState.loading())
        try {
            val recipe = recipeService.get(
                id = recipeId
            )
            emit(DataState.data(message = null, data = recipe))
        } catch (e: Exception) {
            emit(DataState.error<Recipe>(message = e.message ?: "Unknown error"))
        }
    }
}