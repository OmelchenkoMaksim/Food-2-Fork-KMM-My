package com.example.food_2_fork_kmm_my.datasource.cache

import com.example.food_2_fork_kmm_my.domain.model.Recipe

interface RecipeCache {
    fun insert(recipe: Recipe)
    fun insert(recipes: List<Recipe>)
    fun search(query: String, page:Int):List<Recipe>
    fun getAll(page: Int):List<Recipe>
    @Throws(NullPointerException::class)
    fun get(recipeId: Int):Recipe?
}