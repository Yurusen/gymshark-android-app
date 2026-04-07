package android.angel.gymshark.presentation.screens.shop

import android.angel.gymshark.core.utils.LocalGlassBackground
import android.angel.gymshark.core.utils.LocalLoggedInHazeState
import android.angel.gymshark.presentation.components.Cards
import android.angel.gymshark.presentation.components.InputField
import android.angel.gymshark.presentation.components.SwipeableCards
import android.angel.gymshark.presentation.components.UiContent
import android.angel.gymshark.ui.theme.AppTheme
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.prototype.gymshark.R
import dev.chrisbanes.haze.hazeChild

@Composable
fun ShopScreen(
    navController: NavController
) {
    var isRefreshing by remember { mutableStateOf(false) }
    var refreshTrigger by remember { mutableStateOf(0) }
    val context = LocalContext.current
    var searchInput by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("WOMENS") }
    val selectedGenderIndex = remember { mutableStateOf(1) }
    val selectedGenderCardIndex = remember { mutableStateOf(0) }
    var selectedMenuItem by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    var lastScrollOffset by remember { mutableStateOf(0) }
    val showHeader = remember { mutableStateOf(true) }

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect { currentOffset ->
                if (currentOffset == 0 && lastScrollOffset == 0) {
                    showHeader.value = true
                } else {
                    showHeader.value = currentOffset < lastScrollOffset
                }
                lastScrollOffset = currentOffset
            }
    }

    UiContent(isRefreshing = isRefreshing, onRefresh = { }) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            stickyHeader {
                AnimatedVisibility(
                    visible = showHeader.value,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        InputField(
                            modifier = Modifier.padding(horizontal = 6.dp),
                            value = searchInput,
                            onValueChange = { input ->
                                searchInput = input
                            },
                            label = "what are you looking for?",
                            placeholder = "what are you looking for?",
                            trailingIcon = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        modifier = Modifier
                                            .padding(end = 19.dp)
                                            .clickable {

                                            },
                                        painter = painterResource(R.drawable.search_icon),
                                        contentDescription = "Clear Input",
                                        tint = AppTheme.systemColors.textPrimary
                                    )
                                }
                            }
                        )
                    }
                }
            }

            item {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(brush = LocalGlassBackground)
                            .hazeChild(LocalLoggedInHazeState.current),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    selectedGender = "WOMENS"
                                    selectedGenderCardIndex.value = 0
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "WOMENS",
                                textAlign = TextAlign.Center,
                                style = AppTheme.typography.bodyLarge.copy(
                                    if (selectedGender == "WOMENS") AppTheme.systemColors.textPrimary else AppTheme.systemColors.textSecondary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    selectedGender = "MENS"
                                    selectedGenderCardIndex.value = 1
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "MENS",
                                textAlign = TextAlign.Center,
                                style = AppTheme.typography.bodyLarge.copy(
                                    if (selectedGender == "MENS") AppTheme.systemColors.textPrimary else AppTheme.systemColors.textSecondary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        repeat(2) { index ->
                            Divider(
                                modifier = Modifier.weight(1f),
                                thickness = 1.dp,
                                color = if (selectedGenderCardIndex.value == index) AppTheme.systemColors.textPrimary else AppTheme.systemColors.textSecondary
                            )
                        }
                    }
                }
            }

            item {
                val genderCards = listOf(
                    Cards {
                        val womensCards = listOf(
                            Cards {
                                Box(modifier = Modifier.height(250.dp)) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = painterResource(R.drawable.shop_banner_womens),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .padding(12.dp),
                                        text = "BESTSELLERS",
                                        style = AppTheme.typography.titleMedium.copy(
                                            AppTheme.systemColors.textPrimary
                                        ),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            },
                            Cards {
                                Box(modifier = Modifier.height(250.dp)) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = painterResource(R.drawable.shop_banner_womens_2),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .padding(12.dp),
                                        text = "FOR THE LIFTERS",
                                        style = AppTheme.typography.titleMedium.copy(
                                            AppTheme.systemColors.textPrimary
                                        ),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        )

                        SwipeableCards(
                            modifier = Modifier.padding(bottom = 6.dp),
                            height = 250.dp,
                            cards = womensCards,
                            hideBorders = true
                        )

                        val womensTrending = listOf(
                            "New Product Drops",
                            "Best Sellers",
                            "New | Interval",
                            "Running",
                            "Adapt Animal X Whitney",
                            "New Shorts"
                        )

                        val womensLeggings = listOf(
                            "All Leggings",
                            "High-Waisted Leggings",
                            "Scrunch Bum Leggings",
                            "Black Leggings",
                            "Flared Leggings",
                            "Seamless Leggings",
                            "Tall Leggings",
                        )

                        val womensClothing = listOf(
                            "All Products",
                            "Leggings",
                            "T-Short & Tops",
                            "Sports Bras",
                            "Vest Tops",
                            "Shorts",
                            "Matching Gym Sets",
                            "Hoodies & Sweatshirts",
                            "Joggers",
                            "Pants",
                            "Gym Jackets",
                            "Modest Workout Clothes & Hijabs",
                            "Unitards"
                        )

                        val womensTrainYourWay = listOf(
                            "Running",
                            "Hybrid Training",
                            "Lifting Essentials",
                            "Pilates Outfits",
                            "Loungewear"
                        )

                        val womensAccessories = listOf(
                            "New Product Drops",
                            "All Accessories",
                            "All Socks",
                            "All Bags",
                            "All Equipment"
                        )

                        val womensLastChance = listOf(
                            "For Less"
                        )

                        val womensMap = mapOf(
                            "TRENDING" to womensTrending,
                            "LEGGINGS" to womensLeggings,
                            "CLOTHING" to womensClothing,
                            "TRAIN YOUR WAY" to womensTrainYourWay,
                            "ACCESSORIES" to womensAccessories,
                            "LAST CHANCE" to womensLastChance
                        )

                        Column(
                        ) {
                            womensMap.forEach { (header, items) ->
                                Text(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .padding(top = 16.dp)
                                        .fillMaxWidth(),
                                    text = header,
                                    style = AppTheme.typography.bodyLarge.copy(
                                        AppTheme.systemColors.textPrimary,
                                        fontWeight = FontWeight.Bold
                                    )
                                )

                                items.forEach { item ->

                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable(interactionSource = remember { MutableInteractionSource() }) {
                                                selectedMenuItem = item
                                                navController.navigate("shop/category?title=${selectedMenuItem}")
                                            },
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(
                                                vertical = 16.dp,
                                                horizontal = 9.dp
                                            )
                                                .fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = item,
                                                style = AppTheme.typography.bodyMedium.copy(
                                                    color = if (selectedMenuItem == item) AppTheme.primaryColors.primary else AppTheme.systemColors.textSecondary
                                                )
                                            )

                                            Icon(
                                                painter = painterResource(R.drawable.caret_right),
                                                contentDescription = null,
                                                tint = if (selectedMenuItem == item) AppTheme.primaryColors.primary else AppTheme.systemColors.textSecondary
                                            )
                                        }

                                    }

                                    Divider(
                                        color = AppTheme.systemColors.textSecondary,
                                        thickness = 1.dp
                                    )
                                }
                            }
                        }

                    },
                    Cards {
                        val mensCards = listOf(
                            Cards {
                                Box(modifier = Modifier.height(250.dp)) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = painterResource(R.drawable.shop_banner_mens_2),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .padding(12.dp),
                                        text = "NEW GYMSHARK ONXY COLLECTION",
                                        style = AppTheme.typography.titleMedium.copy(
                                            AppTheme.systemColors.textPrimary
                                        ),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            },
                            Cards {
                                Box(modifier = Modifier.height(250.dp)) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = painterResource(R.drawable.shop_banner_mens),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        modifier = Modifier
                                            .align(Alignment.BottomStart)
                                            .padding(12.dp),
                                        text = "NEW IN: HOODIES",
                                        style = AppTheme.typography.titleMedium.copy(
                                            AppTheme.systemColors.textPrimary
                                        ),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        )

                        SwipeableCards(
                            height = 250.dp,
                            cards = mensCards,
                            hideBorders = true
                        )
                    }
                )

                SwipeableCards(
                    height = 2850.dp,
                    cards = genderCards,
                    hideBorders = true,
                    verticalArrangement = Arrangement.Top,
                    cardIndex = selectedGenderCardIndex.value
                ) { index ->
                    selectedGenderCardIndex.value = index
                    if (index == 0) {
                        selectedGender = "WOMENS"
                        selectedGenderIndex.value = 1
                    } else {
                        selectedGender = "MENS"
                        selectedGenderIndex.value = 3
                    }
                }
            }

        }
    }
}