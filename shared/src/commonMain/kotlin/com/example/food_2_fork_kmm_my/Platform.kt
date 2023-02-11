package com.example.food_2_fork_kmm_my

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform