package com.example.tinder.models

data class Profile(
    val name:String,
    val age:Int,
    val description:String,
    val photos:List<String>,
    val photoIndex: Int = 0
)