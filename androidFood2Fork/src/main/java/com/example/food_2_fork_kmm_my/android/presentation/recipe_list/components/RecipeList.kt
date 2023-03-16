package com.example.food_2_fork_kmm_my.android.presentation.recipe_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food_2_fork_kmm_my.datasource.network.RecipeServiceImpl.Companion.RECIPE_PAGINATION_PAGE_SIZE
import com.example.food_2_fork_kmm_my.domain.model.Recipe
import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun RecipeList(
    loading: Boolean,
    recipes: List<Recipe>,
    page: Int,
    onTriggerNextPage: () -> Unit,
    onClickRecipeListItem: (Int) -> Unit,

    ) {

    Box(modifier = Modifier.background(color = MaterialTheme.colors.surface))
    {
        if (loading && recipes.isEmpty()) {
            // Loading
            LoadingRecipeListShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
        } else if (recipes.isEmpty()) {
//    nothing to show ... no recipes
        } else {
            LazyColumn {
// we need the way to tracks the index
                itemsIndexed(
                    items = recipes
                ) { index, recipe ->
                    // это логика заставляющая работать пагинацию
                    if ((index + 1) >= page * RECIPE_PAGINATION_PAGE_SIZE && !loading) {
                        onTriggerNextPage()
                    }

                    RecipeCard(
                        recipe = recipe,
                        onClick = {
                            onClickRecipeListItem(recipe.id)
                        })
                }
            }
        }
    }
}