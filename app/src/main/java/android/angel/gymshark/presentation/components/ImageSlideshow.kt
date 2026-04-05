package android.angel.gymshark.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.prototype.gymshark.R
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import kotlinx.coroutines.delay

@Composable
fun ImageSlideshow(
    images: List<Int>,
    visibleDuration: Long = 4000L,
    fadeDuration: Int = 2000
) {
    var currentIndex by remember { mutableStateOf(0) }
    var nextIndex by remember { mutableStateOf(1) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            alpha.snapTo(0f)
            alpha.animateTo(1f, tween(fadeDuration))

            delay(visibleDuration)

            currentIndex = nextIndex
            nextIndex = (nextIndex + 1) % images.size

            alpha.snapTo(0f)
        }
    }

    Box {
        Image(
            painter = painterResource(images[currentIndex]),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Image(
            painter = painterResource(images[nextIndex]),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .alpha(alpha.value)
        )
    }
}