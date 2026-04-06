package android.angel.gymshark.presentation.screens.dashboard

import android.angel.gymshark.core.utils.LocalLoggedInHazeState
import android.angel.gymshark.presentation.components.InputField
import android.angel.gymshark.presentation.components.UiContent
import android.angel.gymshark.presentation.components.Widget
import android.angel.gymshark.presentation.components.buttons.CoreButton
import android.angel.gymshark.ui.theme.AppTheme
import android.graphics.ImageDecoder
import android.os.Build
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.prototype.gymshark.R
import dev.chrisbanes.haze.hazeChild
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder

@Composable
fun DashboardScreen() {
    val interactionSource = remember { MutableInteractionSource() }
    var isRefreshing by remember { mutableStateOf(false) }
    var refreshTrigger by remember { mutableStateOf(0) }
    val selectedItem by remember { mutableStateOf(0) }
    var searchInput by remember { mutableStateOf("") }
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context).components {
        if (Build.VERSION.SDK_INT >= 28) {
            add(ImageDecoderDecoder.Factory())
        } else {
            add(GifDecoder.Factory())
        }
    }.build()

    UiContent(isRefreshing = isRefreshing, onRefresh = { }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
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
                                    tint = AppTheme.systemColors.textSecondary
                                )
                            }
                        })
                }

            }
            item {
                Row(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .hazeChild(LocalLoggedInHazeState.current),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        modifier = Modifier
                            .size(120.dp)
                            .padding(end = 6.dp),
                        contentScale = ContentScale.FillWidth,
                        painter = painterResource(R.drawable.weight_image),
                        contentDescription = null
                    )

                    Column(
                        modifier = Modifier
                            .padding(end = 6.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.Start
                    ) {

                        Text(
                            text = "EARN 25XP WHEN YOU SIGN UP TO SMS",
                            style = AppTheme.typography.bodyLarge.copy(
                                AppTheme.systemColors.textPrimary, fontWeight = FontWeight.Bold
                            )
                        )

                        Text(
                            text = "No 'you up' texts; just the very best of Gymshark straight to your phone",
                            style = AppTheme.typography.bodyLarge.copy(AppTheme.systemColors.textSecondary)
                        )
                    }

                }
            }

            item {
                Box() {
                    AsyncImage(
                        modifier = Modifier
                            .height(360.dp)
                            .fillMaxWidth(),
                        model = ImageRequest.Builder(context)
                            .data("https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExejludDBibDdreW14cnZxZDI2M2s1N2drMzVjOWtjZDJvNmpmYXkxdiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/U0GJQ4mSrIpXUOU72R/giphy.gif")
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        imageLoader = imageLoader
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(vertical = 8.dp, horizontal = 6.dp)
                    ) {
                        Text(
                            text = "JUST DROPPED: NEW GYMSHARK CONDITIONING CLUB",
                            style = AppTheme.typography.headlineSmall.copy(AppTheme.systemColors.textPrimary),
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "The full kit for the hybrid guy: Built for it all, just like you",
                            style = AppTheme.typography.bodyMedium.copy(AppTheme.systemColors.textPrimary)
                        )
                    }

                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "NEW GYMSHARK CONDITIONING CLUB",
                        style = AppTheme.typography.bodyMedium.copy(AppTheme.systemColors.textPrimary),
                        softWrap = false,
                        fontWeight = FontWeight.ExtraBold
                    )

                    TextButton(onClick = {}) {
                        Text(
                            text = "View All",
                            style = AppTheme.typography.bodyMedium.copy(
                                AppTheme.systemColors.textSecondary, fontWeight = FontWeight.Bold
                            ),
                            softWrap = false,
                        )
                    }

                }
            }

            item {
                val items = listOf(
                    R.drawable.dash_image_1,
                    R.drawable.dash_image_2,
                    R.drawable.dash_image_3,
                    R.drawable.dash_image_4,
                    R.drawable.dash_image_5,
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .hazeChild(LocalLoggedInHazeState.current)
                ) {
                    items(items) { item ->
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly,
                            horizontalAlignment = Alignment.Start
                        ) {
                            Box(
                                modifier = Modifier
                                    .wrapContentSize()
                                    .padding(horizontal = 3.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .height(320.dp)
                                        .clickable(
                                            interactionSource = interactionSource,
                                            indication = ripple(
                                                bounded = true, radius = 160.dp
                                            )
                                        ) {

                                        }) {
                                    Image(
                                        modifier = Modifier.fillMaxSize(),
                                        painter = painterResource(item),
                                        contentDescription = null
                                    )
                                }

                                IconButton(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(2.dp),
                                    onClick = {

                                    }) {
                                    Icon(
                                        modifier = Modifier.size(20.dp),
                                        painter = painterResource(R.drawable.wishlist_icon),
                                        contentDescription = null,
                                        tint = AppTheme.systemColors.outline
                                    )
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .padding(start = 3.dp)
                                    .padding(bottom = 3.dp),
                                verticalArrangement = Arrangement.SpaceEvenly,
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "Conditioning Club Track Patch Jacket",
                                    style = AppTheme.typography.bodyMedium.copy(AppTheme.systemColors.textPrimary)
                                )

                                Text(
                                    text = "oversized fit",
                                    style = AppTheme.typography.bodySmall.copy(
                                        AppTheme.systemColors.textSecondary,
                                        fontStyle = FontStyle.Italic
                                    )
                                )

                                Text(
                                    text = "Black",
                                    style = AppTheme.typography.bodyMedium.copy(AppTheme.systemColors.textSecondary)
                                )

                                Text(
                                    text = "£70", style = AppTheme.typography.bodyMedium.copy(
                                        AppTheme.systemColors.textPrimary,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }

                    }
                }

            }

            item {
                Box() {
                    Image(
                        modifier = Modifier.height(370.dp),
                        painter = painterResource(R.drawable.dash_main_image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(vertical = 8.dp, horizontal = 6.dp)
                    ) {
                        Text(
                            text = "SHORTS AS VERSATILE AS YOUR TRAINING",
                            style = AppTheme.typography.headlineSmall.copy(AppTheme.systemColors.textPrimary),
                            fontWeight = FontWeight.ExtraBold
                        )
                        Text(
                            text = "We know you need your gym shorts to be lightweight, breathable and as versatile as you are, which is why we've got the best in the game right here",
                            style = AppTheme.typography.bodyMedium.copy(AppTheme.systemColors.textPrimary)
                        )
                    }
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "SHORTS AS VERSATILE AS YOUR TRAINING",
                        style = AppTheme.typography.bodyMedium.copy(AppTheme.systemColors.textPrimary),
                        softWrap = false,
                        fontWeight = FontWeight.ExtraBold
                    )

                }
            }

            item {
                val shortsItems = listOf(
                    R.drawable.dash_shorts_1,
                    R.drawable.dash_shorts_2,
                    R.drawable.dash_shorts_3,
                    R.drawable.dash_shorts_4,
                    R.drawable.dash_shorts_5,
                    R.drawable.dash_shorts_6,
                )

                Column(
                    verticalArrangement = Arrangement.spacedBy(3.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                ) {
                    // 1st Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp), // total height of row
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        // Large item
                        Image(
                            painter = painterResource(shortsItems[0]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight() // height = row height
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f / 2.05f) // small column relative to large image
                                .fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(3.dp)
                        ) {
                            Image(
                                painter = painterResource(shortsItems[1]),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            )

                            Image(
                                painter = painterResource(shortsItems[2]),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            )
                        }
                    }

                    // 2nd Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp), // same as 2 smaller images
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        repeat(3) { index ->
                            Image(
                                painter = painterResource(shortsItems[index + 3]),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                            )
                        }
                    }
                }

            }

            item {
                CoreButton(
                    modifier = Modifier.width(200.dp),
                    title = "view all",
                    onClick = {},
                )
            }

            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "MORE",
                    style = AppTheme.typography.headlineSmall.copy(AppTheme.systemColors.textPrimary),
                    softWrap = false,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}