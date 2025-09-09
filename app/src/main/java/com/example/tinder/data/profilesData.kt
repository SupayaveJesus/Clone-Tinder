package com.example.tinder.data

import com.example.tinder.models.Profile

fun profiles(): List<Profile> = listOf(
    Profile(
        name = "Alice",
        age = 25,
        description = "Loves hiking and outdoor adventures.",
        photos = listOf(
            "https://randomuser.me/api/portraits/women/1.jpg",
            "https://randomuser.me/api/portraits/women/2.jpg",
            "https://randomuser.me/api/portraits/women/3.jpg",
            "https://randomuser.me/api/portraits/women/4.jpg",
            "https://randomuser.me/api/portraits/women/5.jpg",

            )
    ),
    Profile(
        name = "Bob",
        age = 30,
        description = "A foodie who enjoys trying new recipes.",
        photos = listOf(
            "https://randomuser.me/api/portraits/men/1.jpg",
            "https://randomuser.me/api/portraits/men/2.jpg",
            "https://randomuser.me/api/portraits/men/3.jpg",
            "https://randomuser.me/api/portraits/men/4.jpg",
            "https://randomuser.me/api/portraits/men/5.jpg",
            )
    ),
)