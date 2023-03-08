package com.example.food_2_fork_kmm_my.datasource.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = RecipeDatabase.Schema,
            context = context,
            name = "recipes.db" // имя базы данных больше нигде не объявляется (вроде)
        )
    }
}