package com.example.tinder.models

import java.io.Serializable

data class Profile(
    val id:Int,
    val name:String,
    val age:Int,
    val description:String,
    val photos:List<String>,
    //val photoIndex: Int = 0
): Serializable