package com.example.food_2_fork_kmm_my.datasource.cache

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(
        schema = RecipeDatabase.Schema,
        "recipes.db"
    )
}