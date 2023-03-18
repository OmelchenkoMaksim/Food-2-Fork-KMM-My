package com.example.food_2_fork_kmm_my.util


actual class Logger actual constructor(
    private val className: String
) {

    actual fun log(msg: String) {
  /*      if(!true){
            // Crashlytics or whatever
        }
        else{
            println("$className: $msg")
        }*/
    }
}