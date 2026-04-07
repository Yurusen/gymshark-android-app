package android.angel.gymshark.presentation.screens.basket

import android.angel.gymshark.core.utils.LocalScreenHeight
import android.angel.gymshark.presentation.components.UiContent
import android.angel.gymshark.ui.theme.AppTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlin.collections.emptyList

data class Item(
    val id: Long,
    val name: String,
    val price: Double
)

@Composable
fun BasketScreen(
    navController: NavController
) {
    var isRefreshing by remember { mutableStateOf(false) }
    var refreshTrigger by remember { mutableStateOf(0) }
    val basketItems by remember { mutableStateOf<List<Item>>(emptyList()) }
    val context = LocalContext.current

    UiContent(isRefreshing = isRefreshing, onRefresh = { }) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(
                    modifier = Modifier
                        .height(LocalScreenHeight.current)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    if (basketItems.size == 0) {
                        Text(
                            text = "NOTHING HERE YET",
                            style = AppTheme.typography.headlineSmall.copy(
                                AppTheme.systemColors.textPrimary, fontWeight = FontWeight.Bold
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }

        }
    }
}