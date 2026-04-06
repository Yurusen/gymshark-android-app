package android.angel.gymshark.core.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.staticCompositionLocalOf
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