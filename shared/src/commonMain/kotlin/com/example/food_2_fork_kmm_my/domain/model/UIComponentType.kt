package com.example.food_2_fork_kmm_my.domain.model

sealed class UIComponentType {

    object Dialog : UIComponentType()
//    object SnackBar : UIComponentType()
//    object Toast : UIComponentType()

    object None : UIComponentType()
}