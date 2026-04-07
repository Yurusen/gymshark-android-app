package android.angel.gymshark.presentation.components

import android.angel.gymshark.core.utils.LocalLoggedInHazeState
import android.angel.gymshark.ui.theme.AppTheme
import android.graphics.drawable.Icon
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeChild

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    showStringPlaceholder: Boolean = false,
    trailingIcon: @Composable () -> Unit = {},
    fillColor: Color = Color.White.copy(alpha = 0.45f),
    focusedTextColor: Color = AppTheme.systemColors.textPrimary,
    unfocusedFillColor: Color = Color.White.copy(alpha = 0.3f),
    unfocusedTextColor: Color = Color.White.copy(alpha = 0.4f),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    borderRadius: RoundedCornerShape = RoundedCornerShape(
        topStart = 6.dp,
        topEnd = 6.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    ),
    showUnderline: Boolean = true,
    inputHeight: Dp = 56.dp,
    fontSize: TextUnit = 14.sp,
    numberInput: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
) {
    val color = AppTheme.systemColors
    val primaryColors = AppTheme.primaryColors

    val borderColor = if (isSystemInDarkTheme()) {
        Brush.linearGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.4f),
                Color.White.copy(alpha = 0.1f)
            )
        )
    } else {
        Brush.linearGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.1f),
                Color.White.copy(alpha = 0.75f)
            )
        )
    }

    OutlinedTextField(
        value = value,
        keyboardOptions = keyboardOptions,
        onValueChange = { input ->
            if (numberInput) {
                if (input.all { it.isDigit() }) {
                    onValueChange(input)
                }
            } else {
                onValueChange(input)
            }
        },
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontSize = fontSize
        ),
        enabled = enabled,
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge.copy(color = color.textPrimary.copy(0.5f))
            )
        },
        placeholder = {
            if (showStringPlaceholder) {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = fontSize, color = color.textPrimary.copy(0.5f)),
                )
            } else {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge.copy(fontSize = fontSize, color = color.textPrimary.copy(0.5f)),
                )
            }

        },
        singleLine = true,
        shape =  RoundedCornerShape(16.dp),
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        modifier = modifier
            .fillMaxWidth()
            .height(inputHeight)
            .background(Color.Transparent),
        colors = TextFieldDefaults.colors(
            focusedTextColor = focusedTextColor,
            unfocusedTextColor = unfocusedTextColor,
            focusedIndicatorColor = fillColor,
            unfocusedIndicatorColor = unfocusedFillColor,
            errorIndicatorColor = primaryColors.reject,
            cursorColor = color.textPrimary,
            errorCursorColor = primaryColors.reject,
            focusedContainerColor = fillColor,
            unfocusedContainerColor = unfocusedFillColor
        )
    )
}