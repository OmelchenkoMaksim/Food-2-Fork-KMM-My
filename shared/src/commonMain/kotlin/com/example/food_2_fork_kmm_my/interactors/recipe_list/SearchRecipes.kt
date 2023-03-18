package com.example.food_2_fork_kmm_my.interactors.recipe_list

import com.example.food_2_fork_kmm_my.datasource.cache.RecipeCache
import com.example.food_2_fork_kmm_my.datasource.network.RecipeService
import com.example.food_2_fork_kmm_my.domain.model.GenericMessageInfo
import com.example.food_2_fork_kmm_my.domain.model.Recipe
import com.example.food_2_fork_kmm_my.domain.model.UIComponentType
import com.example.food_2_fork_kmm_my.domain.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchRecipes(
    private val recipeService: RecipeService,
    private val recipeCache: RecipeCache,
) {
    //    private val logger = Logger("SearchRecipes")
    fun execute(
        page: Int,
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow {
        emit(DataState.loading())

        // emit recipes
        try {
            val recipes = recipeService.search(
                page = page,
                query = query
            )

            // delay 500ms so we can see loading
//            delay(500)
            if (query == "error") {
                throw Exception("Error in search")
            }
            // insert into cache
            recipeCache.insert(recipes)

            // query the cache
            val cacheResult = if (query.isBlank()) {
                recipeCache.getAll(page = page)
            } else {
                recipeCache.search(
                    query = query,
                    page = page,
                )
            }
            // emit List<Recipe> from cache
            emit(DataState.data<List<Recipe>>(message = null, data = cacheResult))
        } catch (e: Exception) {
            emit(
                DataState.error<List<Recipe>>(
                    message = GenericMessageInfo.Builder()
                        .id("SearchRecipes.Error")
                        .title("Error")
                        .uiComponentType(UIComponentType.Dialog)
                        .description(e.message ?: "Unknown error")
                ))
        }
        /* {
            // how can we emit an error?
            emit(DataState.error<List<Recipe>>(message = e.message ?: "Unkown Error"))
        }*/
    }
}