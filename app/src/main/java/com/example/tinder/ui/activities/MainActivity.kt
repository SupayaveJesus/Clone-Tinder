package com.example.tinder.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.tinder.models.Profile
import com.example.tinder.repositories.ProfileRepository
import com.example.tinder.ui.theme.TinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TinderTheme {
                TinderApp()
            }
        }
    }
}

@Composable
fun TinderApp() {
    val queue = remember {
        mutableStateListOf<Profile>().apply { addAll(ProfileRepository.getProfiles()) }
    }
    val likes = remember { mutableStateListOf<Profile>() }

    MainScreen(
        profiles = queue,
        likes = likes,
        onLike = { p ->
            likes += p
            queue.removeAll { it.id == p.id }
        },
        onDislike = { p ->
            queue.removeAll { it.id == p.id }
        }
    )
}

@Composable
fun MainScreen(
    profiles: List<Profile>,
    likes: List<Profile>,
    onLike: (Profile) -> Unit,
    onDislike: (Profile) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val current = profiles.firstOrNull()

    Box(modifier = modifier.fillMaxSize()) {
        if (current == null) {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("No more profiles")
            }
        } else {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {

                ProfileCardSimple(
                    profile = current,
                    onLike = { onLike(current) },
                    onDislike = { onDislike(current) }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            FloatingActionButton(
                onClick = {
                    val intent =
                        android.content.Intent(context, LikesDetailsActivity::class.java)
                            .apply {
                                putExtra("likes", ArrayList(likes))
                            }
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .padding(16.dp),
                containerColor = Color(0xFF2ECC71),
                contentColor = Color.White
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.KeyboardArrowRight,
                        contentDescription = "Ver likes",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = likes.size.toString(),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}

@Composable
fun ProfileCardSimple(
    profile: Profile,
    onLike: () -> Unit,
    onDislike: () -> Unit,
) {
    var photoIndex by remember(profile.id) { mutableStateOf(0) }

    var visible by remember(profile.id) { mutableStateOf(true) }
    //  AnimatedVisibility(visible = visible, enter = fadeIn(), exit = fadeOut()) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(470.dp)
            ) {
                AsyncImage(
                    model = profile.photos[photoIndex],
                    contentDescription = "Foto de ${profile.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .align(Alignment.TopCenter),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    profile.photos.forEachIndexed { i, _ ->
                        Box(
                            Modifier
                                .weight(1f)
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(
                                    if (i <= photoIndex) Color.Black
                                    else Color.White.copy(alpha = 0.35f)
                                )
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = { if (photoIndex > 0) photoIndex-- },
                        shape = CircleShape,
                        border = BorderStroke(0.dp, Color.Transparent),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Anterior",
                            tint = Color.White
                        )
                    }
                    OutlinedButton(
                        onClick = { if (photoIndex < profile.photos.size - 1) photoIndex++ },
                        shape = CircleShape,
                        border = BorderStroke(0.dp, Color.Transparent),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            Icons.Filled.KeyboardArrowRight,
                            contentDescription = "Siguiente",
                            tint = Color.White
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(
                            Brush.verticalGradient(
                                listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.75f)
                                )
                            )
                        )
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp)
            ) {
                Text(
                    text = "${profile.name} ${profile.age}",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    text = profile.description,
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 16.sp,
                    lineHeight = 18.sp,
                    maxLines = 2,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RoundIconButton(
                        icon = Icons.Filled.Close,
                        border = Color(0xFFFF6B81),
                        iconTint = Color(0xFFFF6B81),
                        onClick = { visible = false; onDislike() }
                    )
                    RoundIconButton(
                        icon = Icons.Filled.Favorite,
                        border = Color(0xFF2ECC71),
                        iconTint = Color(0xFF2ECC71),
                        onClick = { visible = false; onLike() }
                    )
                }
            }
        }
    }
}


@Composable
private fun RoundIconButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    border: Color,
    iconTint: Color,
    onClick: () -> Unit,
    size: Dp = 64.dp
) {
    OutlinedButton(
        onClick = onClick,
        shape = CircleShape,
        border = BorderStroke(3.dp, border),
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.size(size),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.Transparent)
    ) {
        androidx.compose.material3.Icon(
            icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(28.dp)
        )
    }
}


private fun sampleProfiles(): List<Profile> = listOf(
    Profile(
        id = 1,
        name = "Abigail",
        age = 28,
        description = "Le encanta el café y los perros.",
        photos = listOf(
            "https://randomuser.me/api/portraits/women/1.jpg",
            "https://randomuser.me/api/portraits/women/2.jpg",
            "https://randomuser.me/api/portraits/women/3.jpg",
            "https://randomuser.me/api/portraits/women/4.jpg",
            "https://randomuser.me/api/portraits/women/5.jpg"
        )
    ),
    Profile(
        id = 2,
        name = "Bob",
        age = 30,
        description = "Aficionado a cocinar.",
        photos = listOf(
            "https://randomuser.me/api/portraits/men/1.jpg",
            "https://randomuser.me/api/portraits/men/2.jpg",
            "https://randomuser.me/api/portraits/men/3.jpg",
            "https://randomuser.me/api/portraits/men/4.jpg",
            "https://randomuser.me/api/portraits/men/5.jpg"
        )
    )
)

@Preview(showBackground = true, name = "Profile Card")
@Composable
fun ProfileCardPreview() {
    TinderTheme {
        ProfileCardSimple(
            profile = sampleProfiles().first(),
            onLike = {},
            onDislike = {}
        )
    }
}

//pruebas
@Preview(showBackground = true, name = "MainScreen (lista)")
@Composable
fun MainScreenPreview() {
    TinderTheme {
        MainScreen(
            profiles = sampleProfiles(),
            likes = emptyList(),
            onLike = {},
            onDislike = {}
        )
    }
}
