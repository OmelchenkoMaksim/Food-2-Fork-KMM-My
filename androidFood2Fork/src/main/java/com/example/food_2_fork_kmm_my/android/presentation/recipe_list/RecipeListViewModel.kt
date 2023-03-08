package com.example.food_2_fork_kmm_my.android.presentation.recipe_list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_2_fork_kmm_my.interactors.recipe_list.SearchRecipes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val savedStateHandle: SavedStateHandle, // don't need for this VM
    private val searchRecipes: SearchRecipes
) : ViewModel() {

    init {
        loadRecipes()
    }
    private fun loadRecipes() {
        searchRecipes.execute(
            page = 1,
            query = "chicken"
        ).onEach { dataState ->
            println("Recipe List View Model loading: ${dataState.isLoading}")
            dataState.data?.let { recipes ->
                println("Recipe List View Model : ${recipes}")
            }
            dataState.message?.let { message ->
                println("Recipe List View Model mes : ${message}")
            }
        }.launchIn(viewModelScope) // when we use onEach we need to use launchIn


    }
}










