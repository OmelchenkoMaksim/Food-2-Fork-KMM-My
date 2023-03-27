package com.example.food_2_fork_kmm_my.di

import com.example.food_2_fork_kmm_my.datasource.cache.*
import com.example.food_2_fork_kmm_my.domain.util.DatetimeUtil

class CacheModule {

    private val driverFactory:DriverFactory by lazy {
        DriverFactory()
    }

    val recipeDatabase:RecipeDatabase by lazy {
        RecipeDatabaseFactory(driverFactory = driverFactory).createDatabase()
    }

    val recipeCache:RecipeCache by lazy {
        RecipeCacheImpl(
            recipeDatabase = recipeDatabase,
            datetimeUtil =  DatetimeUtil(),
        )
    }

}