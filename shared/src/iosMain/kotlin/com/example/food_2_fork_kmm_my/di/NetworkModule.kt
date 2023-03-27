package com.example.food_2_fork_kmm_my.di

import com.example.food_2_fork_kmm_my.datasource.network.KtorClientFactory
import com.example.food_2_fork_kmm_my.datasource.network.RecipeService
import com.example.food_2_fork_kmm_my.datasource.network.RecipeServiceImpl

class NetworkModule {

    val recipeService :RecipeService by lazy {
        RecipeServiceImpl(
            httpClient = KtorClientFactory().build(),
            baseUrl = RecipeServiceImpl.BASE_URL
        )
    }

}