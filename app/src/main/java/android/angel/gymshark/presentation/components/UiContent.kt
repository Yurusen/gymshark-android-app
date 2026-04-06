package android.angel.gymshark.presentation.components

import android.angel.gymshark.core.utils.LocalEdgeToEdgePadding
import android.angel.gymshark.core.utils.LocalHazeState
import android.angel.gymshark.core.utils.LocalScreenHeight
import android.angel.gymshark.ui.theme.AppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.prototype.gymshark.R
import dev.chrisbanes.haze.haze
import kotlinx.coroutines.launch

@Composable
fun UiContent(
    isRefreshing: Boolean,
    onRefresh: suspend () -> Unit,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = {
            scope.launch { onRefresh() }
        },
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                contentColor = AppTheme.primaryColors.primary,
                state = state,
                refreshTriggerDistance = refreshTrigger,
                scale = true
            )
        }) {

        Scaffold(
            containerColor = Color.Transparent,
            content = { padding ->
                Box(
                    modifier = Modifier
                        .background(color = Color.Transparent)
                ) {
                        content()
                }
            }
        )
    }
}