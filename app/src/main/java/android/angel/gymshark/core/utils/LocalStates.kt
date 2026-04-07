package android.angel.gymshark.core.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState

val LocalHazeState = staticCompositionLocalOf<HazeState> {
    HazeState()
}

val LocalLoggedInHazeState = staticCompositionLocalOf<HazeState> {
    HazeState()
}

val ScaffoldPadding = staticCompositionLocalOf<PaddingValues> {
    PaddingValues(
        top = 56.dp,
        bottom = 56.dp,
        start = 0.dp,
        end = 0.dp
    )
}

val LocalEdgeToEdgePadding = staticCompositionLocalOf<PaddingValues> {
    PaddingValues(
        top = 56.dp,
        bottom = 56.dp,
        start = 0.dp,
        end = 0.dp
    )
}

val LocalScreenHeight = staticCompositionLocalOf<Dp> {
    650.dp
}

val LocalGlassBackground: Brush = Brush.linearGradient(
    colors = listOf(
        Color.White.copy(alpha = 0.25f),
        Color.White.copy(alpha = 0.10f),
        Color.White.copy(alpha = 0.05f)
    )
)

val LocalGlassBorder: Brush = Brush.linearGradient(
    colors = listOf(
        Color.White.copy(alpha = 0.1f),
        Color.White.copy(alpha = 0.4f)
    )
)