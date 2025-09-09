package com.example.tinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tinder.models.Profile
import com.example.tinder.ui.theme.TinderTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TinderTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    MainScreen(
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ListProfile(modifier)
    }

}

@Composable
fun ListProfile(Modifier: Modifier) {
    val profile = arrayListOf(
        Profile(
            "Alice",
            25,
            "Loves hiking and outdoor adventures.",
            listOf(
                "https://randomuser.me/api/portraits/women/1.jpg",
                "https://randomuser.me/api/portraits/women/2.jpg",
                "https://randomuser.me/api/portraits/women/3.jpg",
                "https://randomuser.me/api/portraits/women/4.jpg",
                "https://randomuser.me/api/portraits/women/5.jpg",
            )
        ),
        Profile(
            "Bob",
            30,
            "A foodie who enjoys trying new cuisines.",
            listOf(
                "https://randomuser.me/api/portraits/men/1.jpg",
                "https://randomuser.me/api/portraits/men/2.jpg",
                "https://randomuser.me/api/portraits/men/3.jpg",
                "https://randomuser.me/api/portraits/men/4.jpg",
                "https://randomuser.me/api/portraits/men/5.jpg",
            )
        )
    )
    LazyRow {
        items(profile.size) { profileIndex ->
            ProfileCard(
                profile = profile[profileIndex],
                modifier = Modifier
            )
        }
    }

}

@Composable
fun ProfileCard(profile: Profile, modifier: Modifier) {

}

@Preview
@Composable
fun ListProfilePreview() {
    TinderTheme {
        ListProfile(Modifier)
    }
}

