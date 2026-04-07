package android.angel.gymshark.presentation.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Cards(
    val content: @Composable () -> Unit
)

@Composable
fun SwipeableCards(
    modifier: Modifier = Modifier,
    height: Dp = 300.dp,
    hideBorders: Boolean = false,
    cards: List<Cards>,
    cardIndex: Int = 0,
    verticalArrangement: Arrangement.Vertical = Arrangement.SpaceBetween,
    onCardSelected: (Int) -> Unit = {}
) {
    BoxWithConstraints {
        val containerWidth = maxWidth
        val cardSpacing = 16.dp
        val listState = rememberLazyListState(
            initialFirstVisibleItemIndex = cardIndex,
        )
        val flingBehaviour =rememberSnapFlingBehavior(listState)

        LaunchedEffect(cardIndex) {
            listState.animateScrollToItem(index = cardIndex)
        }

        val currentIndex by remember {
            derivedStateOf { listState.firstVisibleItemIndex }
        }

        LaunchedEffect(currentIndex) {
            onCardSelected(currentIndex)
        }

        Column(
            modifier = Modifier
                .width(containerWidth)
                .height(height)
                .align(Alignment.Center)
                .then(modifier)
        ) {
            LazyRow(
                modifier = Modifier
                    .width(containerWidth),
                state = listState,
                horizontalArrangement = Arrangement.spacedBy(cardSpacing),
                flingBehavior = flingBehaviour

            ) {

                items(cards) { card ->
                    Widget(
                        modifier = Modifier.width(containerWidth),
                        height = height,
                        hideBorders = hideBorders,
                        verticalArrangement = verticalArrangement
                    ) {
                        card.content()
                    }
                }

            }
        }


    }
}