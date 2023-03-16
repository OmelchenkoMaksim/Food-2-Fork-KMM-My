package com.example.food_2_fork_kmm_my.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.food_2_fork_kmm_my.domain.model.Recipe
import com.example.food_2_fork_kmm_my.interactors.recipe_list.SearchRecipes
import com.example.food_2_fork_kmm_my.presentation.recipe_list.RecipeListEvents
import com.example.food_2_fork_kmm_my.presentation.recipe_list.RecipeListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
    private val searchRecipes: SearchRecipes,
) : ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        onTriggerEvent(RecipeListEvents.LoadRecipes)
    }

    fun onTriggerEvent(event: RecipeListEvents) {
        when (event) {
            RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            RecipeListEvents.NewSearch -> {
                newSearch()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }
            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query = event.query)
            }
            else -> handleError("Invalid")
        }
    }

    /**
     * Perform a new search:
     * 1. page = 1
     * 2. list position needs to be reset
     */
    private fun newSearch() {
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    /**
     * Get the next page of recipes
     */
    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }

            dataState.message?.let { message ->
                handleError(message)
            }
        }
    }

    private fun appendRecipes(recipes: List<Recipe>) {
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }

    private fun handleError(errorMessage: String) {
        TODO("handle the errors")
    }

}


/*

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
    private val searchRecipes: SearchRecipes
) : ViewModel() {

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
      onTriggerEvent(RecipeListEvents.LoadRecipes)
    }

    fun onTriggerEvent(event: RecipeListEvents) {
        when (event) {
            RecipeListEvents.LoadRecipes -> loadRecipes()
            RecipeListEvents.NextPage -> nextPage()
            else -> handleError("INVALID EVENT")
        }
    }

    private fun handleError(errorMessage: String) {
        TODO("handle the errors")
    }

    private fun nextPage() {
        state.value = state.value.copy(page = state.value.page + 1) // increment page for pagination
        loadRecipes()
    }

    private fun loadRecipes() {
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query
        ).onEach { dataState ->
//            println("Recipe List View Model loading: ${dataState.isLoading}")
            state.value = state.value.copy(isLoading = dataState.isLoading)
            dataState.data?.let { recipes ->
//                println("Recipe List View Model : ${recipes}")
//                state.value = state.value.copy(recipes = recipes)
                appendRecipes(recipes)
            }
            dataState.message?.let { message ->
                handleError(message)
            }
        }.launchIn(viewModelScope) // when we use onEach we need to use launchIn

    }

    // for pagination
    private fun appendRecipes(recipes: List<Recipe>) {
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }
}



*/







