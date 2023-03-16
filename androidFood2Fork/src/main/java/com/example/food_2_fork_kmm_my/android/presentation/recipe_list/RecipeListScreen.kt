package com.example.food_2_fork_kmm_my.android.presentation.recipe_list

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.food_2_fork_kmm_my.android.presentation.recipe_list.components.RecipeList
import com.example.food_2_fork_kmm_my.android.presentation.recipe_list.components.SearchAppBar
import com.example.food_2_fork_kmm_my.android.presentation.theme.AppTheme
import com.example.food_2_fork_kmm_my.presentation.recipe_list.RecipeListEvents
import com.example.food_2_fork_kmm_my.presentation.recipe_list.RecipeListState

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterialApi::class)
@Composable
fun RecipeListScreen(
    state: RecipeListState, // state going IN
    onTriggerEvent: (RecipeListEvents) -> Unit, // events coming OUT
    onSelectRecipe: (Int) -> Unit,
) {

    AppTheme(displayProgressBar = state.isLoading) {
//GradientDemo()
        // это что-то вроде стратегии компоновки макета
        Scaffold(topBar = {
            SearchAppBar(
                query = state.query,

                onQueryChanged = {
                    onTriggerEvent(RecipeListEvents.OnUpdateQuery(it))
                },

                // update state
                onExecuteSearch = {
                    onTriggerEvent(RecipeListEvents.NewSearch)
                })
        }) {
            RecipeList(
                loading = state.isLoading,
                recipes = state.recipes,
                page = state.page,
                onTriggerNextPage = {
                    onTriggerEvent(RecipeListEvents.NextPage)
                },
                onClickRecipeListItem = onSelectRecipe
            )
        }


        /*
        LazyColumn {
            items(100) { recipeId ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSelectRecipe(recipeId)
                        }
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp),
                        text = "RecipeId = ${recipeId}"
                    )
                }
            }
        }
        */
    }
}