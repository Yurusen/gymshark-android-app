package android.angel.gymshark.presentation.components

import android.angel.gymshark.core.utils.LocalHazeState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
    val hazeState = LocalHazeState.current

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = {
            scope.launch { onRefresh() }
        },
        indicator = { state, refreshTrigger ->
            SwipeRefreshIndicator(
                state = state,
                refreshTriggerDistance = refreshTrigger,
                scale = true
            )
        }) {

        Scaffold(
            content = { padding ->

//                Image(
//                    painter = painterResource(id = R.drawable.gym),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .alpha(0.7f)
//                        .haze(
//                            hazeState,
//                            backgroundColor = Color.Transparent,
//                            blurRadius = 8.dp
//                        )
//                )

                Box(modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxSize()) {

                    content()

                }

            }

        )

    }

}