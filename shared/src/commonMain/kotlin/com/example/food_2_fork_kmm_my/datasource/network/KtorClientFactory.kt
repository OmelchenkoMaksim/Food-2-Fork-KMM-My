package com.example.food_2_fork_kmm_my.datasource.network

import com.example.food_2_fork_kmm_my.datasource.network.model.RecipeDto
import com.example.food_2_fork_kmm_my.domain.model.Recipe
import com.example.food_2_fork_kmm_my.domain.util.DatetimeUtil
import io.ktor.client.*

expect class KtorClientFactory() {
 
    fun build():HttpClient
}


fun RecipeDto.toRecipe(): Recipe{
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = pk,
        title = title,
        featuredImage = featuredImage,
        rating = rating,
        publisher = publisher,
        sourceUrl = sourceUrl,
        ingredients = ingredients,
        dateAdded = datetimeUtil.toLocalDate(longDateAdded.toDouble()),
        dateUpdated = datetimeUtil.toLocalDate(longDateUpdated.toDouble()),
    )
}

fun List<RecipeDto>.toRecipeList(): List<Recipe>{
    return map{it.toRecipe()}
}
