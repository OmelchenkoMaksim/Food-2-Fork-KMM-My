    package com.example.food_2_fork_kmm_my.presentation.recipe_list

class FoodCategoryUtil {
    fun getAllFoodCategories(): List<FoodCategory> {
        return listOf(
            FoodCategory.ERROR,
            FoodCategory.CHICKEN,
            FoodCategory.BEEF,
            FoodCategory.SOUP,
            FoodCategory.DESSERT,
            FoodCategory.VEGETARIAN,
            FoodCategory.MILK,
            FoodCategory.VEGAN,
            FoodCategory.PIZZA,
            FoodCategory.DONUT
        )
    }

    fun getFoodCategory(value: String): FoodCategory? {
        val map = FoodCategory.values().associateBy(FoodCategory::value)
        return map[value]
    }

}