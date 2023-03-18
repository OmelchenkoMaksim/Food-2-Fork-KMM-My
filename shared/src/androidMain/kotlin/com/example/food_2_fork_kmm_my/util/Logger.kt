package com.example.food_2_fork_kmm_my.util

import com.example.food_2_fork_kmm_my.BuildConfig.DEBUG


actual class Logger actual constructor(
    private val className: String
) {

    actual fun log(msg: String) {
        if (!DEBUG) {
            // production logging - Crashlytics or something else
        } else {
            printLogD(className, msg)
        }
    }
}