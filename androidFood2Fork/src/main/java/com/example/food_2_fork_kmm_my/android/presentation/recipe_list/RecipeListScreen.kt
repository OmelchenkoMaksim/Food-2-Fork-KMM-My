package com.example.food_2_fork_kmm_my.android.presentation.recipe_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.food_2_fork_kmm_my.android.presentation.recipe_list.components.RecipeList
import com.example.food_2_fork_kmm_my.android.presentation.recipe_list.components.SearchAppBar
import com.example.food_2_fork_kmm_my.android.presentation.theme.AppTheme
import com.example.food_2_fork_kmm_my.presentation.recipe_list.FoodCategoryUtil
import com.example.food_2_fork_kmm_my.presentation.recipe_list.RecipeListEvents
import com.example.food_2_fork_kmm_my.presentation.recipe_list.RecipeListState
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun RecipeListScreen(
    state: RecipeListState,
    onTriggerEvent: (RecipeListEvents) -> Unit,
    onClickRecipeListItem: (Int) -> Unit,
) {
    AppTheme(
        displayProgressBar = state.isLoading,
        dialogQueue = state.queue,
        onRemoveHeadMessageFromQueue = {
                                       onTriggerEvent(RecipeListEvents.OnRemoveHeadMessageFromQueue)
        },
    ) {
        val foodCategories = remember { FoodCategoryUtil().getAllFoodCategories() }
        Scaffold(
            topBar = {
                SearchAppBar(
                    query = state.query,
                    onQueryChanged = {
                        onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                    },
                    onExecuteSearch = {
                        onTriggerEvent(RecipeListEvents.NewSearch)
                    },
                    categories = foodCategories,
                    onSelectedCategoryChanged = {
                        onTriggerEvent(RecipeListEvents.OnSelectedCategory(it))
                    },
                    selectedCategore = state.selectedCategory
                )
            },
        ) {
            RecipeList(
                loading = state.isLoading,
                recipes = state.recipes,
                page = state.page,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeListEvents.NextPage)
                },
                onClickRecipeListItem = onClickRecipeListItem
            )
        }
    }

}