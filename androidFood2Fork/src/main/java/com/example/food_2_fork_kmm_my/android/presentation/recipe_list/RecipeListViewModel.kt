package com.example.food_2_fork_kmm_my.android.presentation.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_2_fork_kmm_my.domain.model.GenericMessageInfo
import com.example.food_2_fork_kmm_my.domain.model.Recipe
import com.example.food_2_fork_kmm_my.domain.model.UIComponentType
import com.example.food_2_fork_kmm_my.domain.util.GenericMessageInfoQueueUtil
import com.example.food_2_fork_kmm_my.domain.util.Queue
import com.example.food_2_fork_kmm_my.interactors.recipe_list.SearchRecipes
import com.example.food_2_fork_kmm_my.presentation.recipe_list.FoodCategory
import com.example.food_2_fork_kmm_my.presentation.recipe_list.RecipeListEvents
import com.example.food_2_fork_kmm_my.presentation.recipe_list.RecipeListState
import com.example.food_2_fork_kmm_my.util.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(
    private val searchRecipes: SearchRecipes,
): ViewModel() {

    private val logger = Logger("RecipeListVM")

    val state: MutableState<RecipeListState> = mutableStateOf(RecipeListState())

    init {
        loadRecipes()
    }

    fun onTriggerEvent(event: RecipeListEvents){
        when (event){
            RecipeListEvents.LoadRecipes -> {
                loadRecipes()
            }
            RecipeListEvents.NewSearch -> {
                newSearch()
            }
            RecipeListEvents.NextPage -> {
                nextPage()
            }
            is RecipeListEvents.OnSelectCategory -> {
                onSelectCategory(event.category)
            }
            is RecipeListEvents.OnUpdateQuery -> {
                state.value = state.value.copy(query =  event.query, selectedCategory = null)
            }
            is RecipeListEvents.OnRemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
            else -> {
                val messageInfoBuilder = GenericMessageInfo.Builder()
                    .id(UUID.randomUUID().toString())
                    .title("Invalid Event")
                    .uiComponentType(UIComponentType.Dialog)
                    .description("Something went wrong.")
                appendToMessageQueue(messageInfo = messageInfoBuilder)
            }
        }
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.queue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(queue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(queue = queue)
        }catch (e: Exception){
            logger.log("Nothing to remove from DialogQueue")
        }
    }

    /**
     *  Called when a new FoodCategory chip is selected
     */
    private fun onSelectCategory(category: FoodCategory){
        state.value = state.value.copy(selectedCategory = category, query =  category.value)
        newSearch()
    }

    /**
     * Get the next page of recipes
     */
    private fun nextPage(){
        state.value = state.value.copy(page = state.value.page + 1)
        loadRecipes()
    }

    /**
     * Perform a new search:
     * 1. page = 1
     * 2. list position needs to be reset
     */
    private fun newSearch(){
        state.value = state.value.copy(page = 1, recipes = listOf())
        loadRecipes()
    }

    private fun loadRecipes(){
        searchRecipes.execute(
            page = state.value.page,
            query = state.value.query
        ).onEach { dataState ->
            state.value = state.value.copy(isLoading = dataState.isLoading)

            dataState.data?.let { recipes ->
                appendRecipes(recipes)
            }

            dataState.message?.let { message ->
                appendToMessageQueue(message)
            }
        }
    }

    private fun appendRecipes(recipes: List<Recipe>){
        val curr = ArrayList(state.value.recipes)
        curr.addAll(recipes)
        state.value = state.value.copy(recipes = curr)
    }

    private fun appendToMessageQueue(messageInfo: GenericMessageInfo.Builder){
        if(!GenericMessageInfoQueueUtil()
                .doesMessageAlreadyExistInQueue(queue = state.value.queue,messageInfo = messageInfo.build())){
            val queue = state.value.queue
            queue.add(messageInfo.build())
            state.value = state.value.copy(queue = queue)
        }
    }

}





