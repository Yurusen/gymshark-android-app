package android.angel.gymshark.presentation.components.buttons

import android.angel.gymshark.core.utils.LocalHazeState
import android.angel.gymshark.ui.theme.AppTheme
import android.opengl.Visibility
import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

@Composable
fun HollowButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    iconVisibility: Boolean = false,
    iconSvg: Int? = null,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
) {

    val backgroundBrush: Brush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.2f),
            Color.White.copy(alpha = 0.05f)
        )
    )

    val border: Brush = Brush.linearGradient(
        colors = listOf(
            AppTheme.primaryColors.primary.copy(alpha = 0.4f),
            AppTheme.primaryColors.primary.copy(alpha = 0.9f)
        )
    )

    Button(
        modifier = modifier
            .height(50.dp)
            .padding(0.dp)
            .background(
                brush = backgroundBrush,
                shape = RoundedCornerShape(99.dp)
            )
            .border(
                width = 1.dp,
                shape = RoundedCornerShape(99.dp),
                brush = border
            ),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        enabled = enabled,
        shape = RoundedCornerShape(99.dp),
        contentPadding = contentPadding
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Text(
                modifier = Modifier.weight(1f),
                text = title.uppercase(),
                softWrap = false,
                textAlign = TextAlign.Center,
                style = AppTheme.typography.bodySmall.copy(
                    color = AppTheme.systemColors.textPrimary,
                    fontWeight = FontWeight.Bold
                )
            )

            if (iconVisibility && iconSvg != null) {
                Icon(
                    painter = painterResource(id = iconSvg),
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
            }
        }
    }

}