package android.angel.gymshark.presentation.components

import android.angel.gymshark.core.utils.LocalGlassBackground
import android.angel.gymshark.core.utils.LocalGlassBorder
import android.angel.gymshark.core.utils.LocalHazeState
import android.angel.gymshark.core.utils.LocalLoggedInHazeState
import android.angel.gymshark.ui.theme.AppTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.hazeChild

@Composable
fun Widget(
    modifier: Modifier = Modifier,
    height: Dp = 300.dp,
    title: String? = null,
    caption: String? = null,
    hideBorders: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.SpaceBetween,
    content: @Composable () -> Unit
) {

    val isDarkMode = isSystemInDarkTheme()
    val hazeState = LocalLoggedInHazeState.current

    Box(
        modifier = Modifier
            .height(height)
            .then(
                if (!hideBorders) {
                    Modifier
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(8.dp),
                            clip = false
                        )
                        .hazeChild(
                            state = hazeState,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .background(
                            brush = LocalGlassBackground,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .drawBehind {
                            if (isDarkMode) {
                                val cornerRadius = 8.dp.toPx()
                                val offsetX = 4.dp.toPx()
                                val offsetY = 4.dp.toPx()
                                val blurRadius = 24.dp.toPx()

                                val paint = Paint().asFrameworkPaint().apply {
                                    color = android.graphics.Color.BLACK
                                    alpha = (0.35f * 255).toInt()
                                    maskFilter = android.graphics.BlurMaskFilter(
                                        blurRadius,
                                        android.graphics.BlurMaskFilter.Blur.NORMAL
                                    )
                                }

                                drawContext.canvas.nativeCanvas.drawRoundRect(
                                    offsetX,
                                    offsetY,
                                    size.width + offsetX,
                                    size.height + offsetY,
                                    cornerRadius,
                                    cornerRadius,
                                    paint
                                )
                            }
                        }
                        .border(
                            width = 1.dp,
                            brush = LocalGlassBorder,
                            shape = RoundedCornerShape(8.dp)
                        )
                } else {
                    Modifier
                        .shadow(
                            elevation = 8.dp,
                            clip = false
                        )
                        .hazeChild(
                            state = hazeState,
                        )
                        .background(
                            brush = LocalGlassBackground,
                        )
                        .drawBehind {
                            if (isDarkMode) {
                                val cornerRadius = 0.dp.toPx()
                                val offsetX = 4.dp.toPx()
                                val offsetY = 4.dp.toPx()
                                val blurRadius = 24.dp.toPx()

                                val paint = Paint().asFrameworkPaint().apply {
                                    color = android.graphics.Color.BLACK
                                    alpha = (0.35f * 255).toInt()
                                    maskFilter = android.graphics.BlurMaskFilter(
                                        blurRadius,
                                        android.graphics.BlurMaskFilter.Blur.NORMAL
                                    )
                                }

                                drawContext.canvas.nativeCanvas.drawRoundRect(
                                    offsetX,
                                    offsetY,
                                    size.width + offsetX,
                                    size.height + offsetY,
                                    cornerRadius,
                                    cornerRadius,
                                    paint
                                )
                            }
                        }
                }
            )
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (!title.isNullOrBlank() || (!title.isNullOrBlank() && !caption.isNullOrBlank())) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.15f),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (!title.isNullOrBlank()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            text = title,
                            textAlign = TextAlign.Center,
                            style = AppTheme.typography.titleMedium.copy(
                                color = AppTheme.systemColors.textPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    if (!caption.isNullOrBlank()) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            text = caption,
                            textAlign = TextAlign.Center,
                            style = AppTheme.typography.titleSmall.copy(
                                color = AppTheme.systemColors.textSecondary,
                                fontStyle = FontStyle.Italic
                            )
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(if (!title.isNullOrBlank() && !caption.isNullOrBlank()) 0.85f else 1f),
                    verticalArrangement = verticalArrangement,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    content()
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(if (!title.isNullOrBlank() && !caption.isNullOrBlank()) 0.85f else 1f),
                    verticalArrangement = verticalArrangement,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    content()
                }
            }

        }
    }


}