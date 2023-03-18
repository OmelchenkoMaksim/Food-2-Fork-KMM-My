package com.example.food_2_fork_kmm_my.android.di

import com.example.food_2_fork_kmm_my.datasource.network.KtorClientFactory
import com.example.food_2_fork_kmm_my.datasource.network.RecipeService
import com.example.food_2_fork_kmm_my.datasource.network.RecipeServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun provideRecipeService(
        httpClient: HttpClient,
    ): RecipeService {
        return RecipeServiceImpl(
            httpClient = httpClient,
            baseUrl = RecipeServiceImpl.BASE_URL,
        )
    }
}
