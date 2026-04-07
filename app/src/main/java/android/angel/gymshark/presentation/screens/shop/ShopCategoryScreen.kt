package android.angel.gymshark.presentation.screens.shop

import android.angel.gymshark.core.utils.LocalGlassBackground
import android.angel.gymshark.presentation.components.UiContent
import android.angel.gymshark.ui.theme.AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.prototype.gymshark.R

@Composable
fun ShopCategoryScreen(
    title: String?
) {
    var isRefreshing by remember { mutableStateOf(false) }
    var refreshTrigger by remember { mutableStateOf(0) }
    val context = LocalContext.current
    val selectedViewStyle = remember { mutableStateOf(true) }

    UiContent(isRefreshing = isRefreshing, onRefresh = { }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "136 Products Found",
                    style = AppTheme.typography.bodyLarge.copy(
                        AppTheme.systemColors.textSecondary
                    )
                )
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                            .clip(RoundedCornerShape(99.dp))
                            .background(
                                brush = LocalGlassBackground, shape = RoundedCornerShape(99.dp)
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {


                        Icon(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .fillMaxHeight()
                                .then(
                                    if (selectedViewStyle.value) {
                                        Modifier.background(color = AppTheme.primaryColors.primary)
                                    } else {
                                        Modifier.background(Color.Transparent)
                                    }
                                )
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() }) {
                                    selectedViewStyle.value = true
                                },
                            painter = painterResource(R.drawable.filter_icon),
                            contentDescription = null,
                            tint = if (selectedViewStyle.value) Color.White else AppTheme.systemColors.textSecondary
                        )


                        Icon(
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                                .fillMaxHeight()
                                .then(
                                    if (!selectedViewStyle.value) {
                                        Modifier.background(color = AppTheme.primaryColors.primary)
                                    } else {
                                        Modifier.background(Color.Transparent)
                                    }
                                )
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() }) {
                                    selectedViewStyle.value = false
                                },
                            painter = painterResource(R.drawable.filter_icon),
                            contentDescription = null,
                            tint = if (!selectedViewStyle.value) Color.White else AppTheme.systemColors.textSecondary
                        )
                    }

                    Spacer(modifier = Modifier.weight(0.1f))

                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp)
                            .clip(RoundedCornerShape(99.dp))
                            .background(
                                brush = LocalGlassBackground, shape = RoundedCornerShape(99.dp)
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() }) {

                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {

                        Icon(
                            modifier = Modifier.height(60.dp),
                            painter = painterResource(R.drawable.filter_icon),
                            contentDescription = null,
                            tint = Color.White
                        )

                        Text(
                            modifier = Modifier.padding(
                                horizontal = 4.dp
                            ),
                            text = "FILTER & SORT",
                            textAlign = TextAlign.Center,
                            style = AppTheme.typography.bodyMedium.copy(
                                AppTheme.systemColors.textPrimary, fontWeight = FontWeight.Bold
                            )
                        )

                    }
                }
            }

        }
    }
}