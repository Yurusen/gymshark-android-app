package android.angel.gymshark.presentation.components.buttons

import android.angel.gymshark.ui.theme.AppTheme
import android.opengl.Visibility
import androidx.compose.foundation.background
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
fun CoreButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    iconVisibility: Boolean = false,
    iconSvg: Int? = null,
    backgroundColor: Brush = AppTheme.primaryGradients.primaryGradient,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
//    hazeState: HazeState
) {

    Button(
        modifier = modifier
            .height(50.dp)
            .padding(0.dp)
            .background(
                brush = backgroundColor,
                shape = RoundedCornerShape(99.dp)
            ),
//            .haze(
//                hazeState,
//                backgroundColor = Color.Transparent,
//                blurRadius = 15.dp
//            ),
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