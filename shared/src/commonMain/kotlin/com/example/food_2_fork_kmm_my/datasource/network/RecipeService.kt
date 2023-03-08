package com.example.food_2_fork_kmm_my.datasource.network

import com.example.food_2_fork_kmm_my.domain.model.Recipe

interface RecipeService {

    suspend fun search(
        page:Int,
        query:String
    ):List<Recipe>

    suspend fun get(
        id:Int
    ):Recipe

}