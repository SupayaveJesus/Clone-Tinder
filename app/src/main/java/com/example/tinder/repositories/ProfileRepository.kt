package com.example.tinder.repositories
import com.example.tinder.models.Profile
import kotlin.collections.ArrayList

object ProfileRepository {
    private val profiles: List<Profile> = listOf(
        Profile(
            1, "Alice", 25,  "Loves hiking and outdoor adventures.",
             listOf(
                "https://randomuser.me/api/portraits/women/1.jpg",
                "https://randomuser.me/api/portraits/women/2.jpg",
                "https://randomuser.me/api/portraits/women/3.jpg",
                "https://randomuser.me/api/portraits/women/4.jpg",
                "https://randomuser.me/api/portraits/women/5.jpg",
            )
        ),
        Profile(
            2,"Bob",  30,  "A foodie who enjoys trying new recipes.",
            listOf(
                "https://randomuser.me/api/portraits/men/1.jpg",
                "https://randomuser.me/api/portraits/men/2.jpg",
                "https://randomuser.me/api/portraits/men/3.jpg",
                "https://randomuser.me/api/portraits/men/4.jpg",
                "https://randomuser.me/api/portraits/men/5.jpg",
            )
        ),
        Profile(
            3,"Cathy",  28,  "Passionate about art and painting.",
            listOf(
                "https://randomuser.me/api/portraits/women/91.jpg",
                "https://randomuser.me/api/portraits/women/92.jpg",
                "https://randomuser.me/api/portraits/women/93.jpg",
                "https://randomuser.me/api/portraits/women/94.jpg",
                "https://randomuser.me/api/portraits/women/95.jpg"
            )
        ),
        Profile(
            4, "Carlos", 32, "Músico y productor. La música es mi vida",
            listOf(
                "https://randomuser.me/api/portraits/men/10.jpg",
                "https://randomuser.me/api/portraits/men/11.jpg",
                "https://randomuser.me/api/portraits/men/12.jpg",
                "https://randomuser.me/api/portraits/men/13.jpg",
                "https://randomuser.me/api/portraits/men/14.jpg"
            )
        ),
        Profile(
            5, "Sofia", 24, "Viajera empedernida. 30 países y contando",
            listOf(
                "https://randomuser.me/api/portraits/women/20.jpg",
                "https://randomuser.me/api/portraits/women/21.jpg",
                "https://randomuser.me/api/portraits/women/22.jpg",
                "https://randomuser.me/api/portraits/women/23.jpg",
                "https://randomuser.me/api/portraits/women/34.jpg",

                )
        ),
        Profile(
            6, "Miguel", 27, "Desarrollador de día, gamer de noche",
            listOf(
                "https://randomuser.me/api/portraits/men/20.jpg",
                "https://randomuser.me/api/portraits/men/21.jpg",
                "https://randomuser.me/api/portraits/men/22.jpg",
                "https://randomuser.me/api/portraits/men/23.jpg",
                "https://randomuser.me/api/portraits/men/24.jpg",

                )
        ),
        Profile(
            7, "Laura", 29, "Yoga instructor. Namaste",
            listOf(
                "https://randomuser.me/api/portraits/women/30.jpg",
                "https://randomuser.me/api/portraits/women/31.jpg",
                "https://randomuser.me/api/portraits/women/32.jpg",
                "https://randomuser.me/api/portraits/women/33.jpg",
                "https://randomuser.me/api/portraits/women/34.jpg"
            )
        ),
        Profile(
            8, "David", 26, "Fotógrafo profesional. Capturo momentos",
            listOf(
                "https://randomuser.me/api/portraits/men/30.jpg",
                "https://randomuser.me/api/portraits/men/31.jpg",
                "https://randomuser.me/api/portraits/men/32.jpg",
                "https://randomuser.me/api/portraits/men/32.jpg",
                "https://randomuser.me/api/portraits/men/33.jpg",

                )
        ),
        Profile(
            9, "Ana", 31, "Chef profesional. Amo la cocina italiana",
            listOf(
                "https://randomuser.me/api/portraits/women/40.jpg",
                "https://randomuser.me/api/portraits/women/41.jpg",
                "https://randomuser.me/api/portraits/women/42.jpg",
                "https://randomuser.me/api/portraits/women/43.jpg",
                "https://randomuser.me/api/portraits/women/44.jpg",

                )
        ),
        Profile(
            10, "Pedro", 33, "Médico y corredor de maratones",
            listOf(
                "https://randomuser.me/api/portraits/men/40.jpg",
                "https://randomuser.me/api/portraits/men/41.jpg",
                "https://randomuser.me/api/portraits/men/42.jpg",
                "https://randomuser.me/api/portraits/men/43.jpg",
                "https://randomuser.me/api/portraits/men/44.jpg"
            )
        ),
    )

    fun getProfiles(): List<Profile> = profiles
}
