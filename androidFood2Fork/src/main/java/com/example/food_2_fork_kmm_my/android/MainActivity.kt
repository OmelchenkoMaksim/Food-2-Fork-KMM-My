package com.example.food_2_fork_kmm_my.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.food_2_fork_kmm_my.android.presentation.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalStdlibApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
/*        val ktorClient = KtorClientFactory().build()
       CoroutineScope(IO).launch {
           val recipeService = RecipeServiceImpl(
               httpClient = ktorClient,
               baseUrl = RecipeServiceImpl.BASE_URL
           )
           val recipeId = 1551
           val recipe = recipeService.get(recipeId)
           println("KtorTest ${recipe.title}\n")
           println("KtorTest ${recipe.ingredients}\n")
           println("KtorTest ${DatetimeUtil().humanizeDatetime(recipe.dateUpdated)}")
           println("KtorTest date not humanize ${recipe.dateUpdated}")
       }*/
       setContent {
           Navigation()
       }
   }
}
