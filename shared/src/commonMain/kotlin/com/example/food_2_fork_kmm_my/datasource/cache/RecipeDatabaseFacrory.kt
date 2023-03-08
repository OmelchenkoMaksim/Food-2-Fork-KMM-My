package com.example.food_2_fork_kmm_my.datasource.cache

import com.squareup.sqldelight.db.SqlDriver

class RecipeDatabaseFactory(
    private val driverFactory: DriverFactory
) {
    fun createDatabase(): RecipeDatabase {
        return RecipeDatabase(driverFactory.createDriver())
    }
}

expect class DriverFactory {
    fun createDriver(): SqlDriver
}