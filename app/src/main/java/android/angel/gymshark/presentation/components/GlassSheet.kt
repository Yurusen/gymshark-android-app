package android.angel.gymshark.presentation.components

import android.angel.gymshark.core.utils.LocalHazeState
import android.angel.gymshark.ui.theme.AppTheme
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun GlassSheet(
    isVisible: Boolean,
    onDismissRequest: () -> Unit,
    sheetHeight: Dp = 300.dp,
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val hazeState = LocalHazeState.current
    var isMounted by remember { mutableStateOf(isVisible) }
    val sheetHeightPx = with(LocalDensity.current) { sheetHeight.toPx() }
    var offsetY = remember { Animatable(sheetHeightPx) }
    val overlayAlpha = remember { Animatable(0f) }
    val backgroundBrush: Brush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.25f),
            Color.White.copy(alpha = 0.10f),
            Color.White.copy(alpha = 0.05f)
        )
    )

    val border: Brush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.1f),
            Color.White.copy(alpha = 0.4f)
        )
    )

    LaunchedEffect(isVisible) {
        if (isVisible) {
            isMounted = true

            launch {
                offsetY.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = LinearOutSlowInEasing
                    )
                )
            }

            launch {
                overlayAlpha.animateTo(
                    targetValue = 0.5f,
                    animationSpec = tween(durationMillis = 500)
                )
            }
        } else {
            launch {
                offsetY.animateTo(
                    targetValue = sheetHeightPx,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutLinearInEasing
                    )
                )
                isMounted = false
                onDismissRequest()

            }

            launch {
                overlayAlpha.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(durationMillis = 500)
                )
            }
        }
    }

    if (isMounted) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = overlayAlpha.value))
                    .clickable {
                        if (isVisible) {
                            onDismissRequest()
                        }
                    }
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(sheetHeight)
                    .align(Alignment.BottomCenter)
                    .offset { IntOffset(0, offsetY.value.roundToInt()) }
                    .background(
                        brush = backgroundBrush,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
                    .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onVerticalDrag = { change, dragAmount ->
                                change.consume()

                                val newOffset = (offsetY.value + dragAmount)
                                    .coerceIn(0f, sheetHeightPx)

                                val progress = newOffset / sheetHeightPx

                                scope.launch {
                                    offsetY.snapTo(newOffset)
                                    overlayAlpha.snapTo(0.5f - progress)
                                }
                            },
                            onDragEnd = {
                                val shouldDismiss = offsetY.value > sheetHeightPx * 0.3f

                                scope.launch {
                                    if (shouldDismiss) {
                                        offsetY.animateTo(
                                            targetValue = sheetHeightPx,
                                            animationSpec = tween(
                                                durationMillis = 500,
                                                easing = FastOutLinearInEasing
                                            )
                                        )


                                        isMounted = false
                                        onDismissRequest()
                                    } else {
                                        offsetY.animateTo(
                                            targetValue = 0f,
                                            animationSpec = tween(300)
                                        )
                                    }
                                }

                                scope.launch {
                                 overlayAlpha.animateTo(
                                 targetValue = if (shouldDismiss) 0f else 0.5f,
                                 animationSpec = tween(300)
                                 )
                                }

                            }
                        )
                    }
                    .hazeChild(
                        hazeState,
                        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp)
                    )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    content()
                }
            }
        }
    }
}