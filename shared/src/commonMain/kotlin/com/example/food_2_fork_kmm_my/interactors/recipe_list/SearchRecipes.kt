package com.example.food_2_fork_kmm_my.interactors.recipe_list

import com.example.food_2_fork_kmm_my.datasource.network.RecipeService
import com.example.food_2_fork_kmm_my.domain.model.Recipe
import com.example.food_2_fork_kmm_my.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService
) {
    fun execute(
        page: Int,
        query: String
    ): Flow<DataState<List<Recipe>>> = flow {
        // how can we emit loading?
        emit(DataState.loading())

        // emit recipes
        try {
            val recipes = recipeService.search(
                page = page,
                query = query
            )
            emit(DataState.data(message = null, data = recipes))
        } catch (
            e: Exception
        ) {
            // how can we emit an error?
            emit(DataState.error<List<Recipe>>(message = e.message ?: "Unkown Error"))
        }
    }
}