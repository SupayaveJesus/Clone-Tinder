package com.example.tinder.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.tinder.models.Profile
import com.example.tinder.ui.theme.TinderTheme

class LikesDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val likeProfiles =
            intent.getSerializableExtra("likes") as? ArrayList<Profile> ?: arrayListOf()

        setContent {
            TinderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LikesDetail(
                        likeProfiles = likeProfiles,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LikesDetail(
    likeProfiles: List<Profile>,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    if (context is ComponentActivity) {
                        context.finish()
                    }
                }
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = "Likes (${likeProfiles.size})",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        if (likeProfiles.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Filled.Favorite,
                        contentDescription = null,
                        modifier = Modifier.size(80.dp),
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No tienes likes aún",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "¡Dale like a algunos perfiles!",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }

            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(likeProfiles) { profile ->
                    LikeItem(profile = profile)
                }
            }
        }
    }
}

    @Composable
    fun LikeItem(profile: Profile) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Row(modifier = Modifier.fillMaxSize()) {
                // Foto del perfil
                Box(
                    modifier = Modifier
                        .width(120.dp)
                        .fillMaxHeight()
                ) {
                    AsyncImage(
                        model = profile.photos.first(),
                        contentDescription = "Foto de ${profile.name}, ${profile.age} años",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }

                // Información del perfil
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${profile.name}, ${profile.age}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = profile.description,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }

    @Composable
    fun LikesDetailPreview() {
        TinderTheme {
            LikesDetail(
                likeProfiles = listOf(
                    Profile(
                         1,
                         "Alice",
                         25,
                         "Loves hiking and outdoor adventures.",
                         listOf("https://randomuser.me/api/portraits/women/1.jpg")
                    ),
                    Profile(
                        id = 2,
                        name = "Bob",
                        age = 30,
                        description = "A foodie who enjoys trying new recipes.",
                        photos = listOf("https://randomuser.me/api/portraits/men/1.jpg")
                    )
                )
            )
        }
    }
@Preview(showBackground = true, name = "LikesDetail (vacío)")
@Composable
fun LikesDetailEmptyPreview() {
    TinderTheme {
        LikesDetail(likeProfiles = emptyList())
    }
}

@Preview(showBackground = true, name = "LikesDetail (con datos)")
@Composable
fun LikesDetailNonEmptyPreview() {
    TinderTheme {
        LikesDetail(
            likeProfiles = listOf(
                Profile(
                    id = 1,
                    name = "Alice",
                    age = 25,
                    description = "Loves hiking and outdoor adventures.",
                    photos = listOf("https://randomuser.me/api/portraits/women/1.jpg")
                ),
                Profile(
                    id = 2,
                    name = "Bob",
                    age = 30,
                    description = "A foodie who enjoys trying new recipes.",
                    photos = listOf("https://randomuser.me/api/portraits/men/1.jpg")
                )
            )
        )
    }
}
