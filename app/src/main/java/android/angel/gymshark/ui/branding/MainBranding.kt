package android.angel.gymshark.ui.branding

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.prototype.gymshark.R
import com.prototype.gymshark.ui.theme.BrandColorScheme
import com.prototype.gymshark.ui.theme.DarkContainerColors
import com.prototype.gymshark.ui.theme.DarkContainerGradients
import com.prototype.gymshark.ui.theme.LightContainerColors
import com.prototype.gymshark.ui.theme.LightContainerGradients
import com.prototype.gymshark.ui.theme.PrimaryColors
import com.prototype.gymshark.ui.theme.PrimaryGradients


fun MAINBRANDING() = BrandColorScheme(
    primaryColors = PrimaryColors(
        primary = Color(color = 0xFF0C87E8),
        secondary = Color(color = 0xFF003D4B),
        approve = Color(color = 0xFF22F251),
        amberWarning = Color(color = 0xFFFF7F48),
        reject = Color(color = 0xFF400C0D),
    ),
    primaryGradients = PrimaryGradients(
        primaryGradient = Brush.horizontalGradient(
            listOf(
                Color(color = 0XFF4FA8F0),
                Color(color = 0xFF0C87E8)
            )
        ),
        secondaryGradient = Brush.horizontalGradient(
            listOf(
                Color(color = 0xFF003D4B),
                Color(color = 0xFF1C2C35)
            )
        ),
        approveGradient = Brush.horizontalGradient(
            listOf(
                Color(color = 0xFF22F251),
                Color(color = 0xFF0FABBB)
            )
        ),
        amberGradient = Brush.horizontalGradient(
            listOf(
                Color(color = 0xFF8D350C),
                Color(color = 0xFFFF7F48)
            )
        ),
        criticalGradient = Brush.horizontalGradient(
            listOf(
                Color(color = 0xFF400C0D),
                Color(color = 0xFF9B2F51)
            )
        ),
        rejectGradient = Brush.horizontalGradient(
            listOf(
                Color(color = 0xFF400C0D),
                Color(color = 0xFF9B2F51)
            )
        )
    ),
    darkContainerColors = DarkContainerColors(
        primaryText = Color(color = 0XFFFFFFFF),
        secondaryText = Color(0xFF6d6b73),
        primaryContainer = Color(color = 0xFF1a1a1d),
        inputTextContainer = Color(color = 0xFF5A5A60),
        unfocusedInputContainer = Color(color = 0xFF39393c),
        outline = Color(0xFF36343B)
    ),
    darkContainerGradients = DarkContainerGradients(
        primaryContainer = Brush.horizontalGradient(
            colors = listOf(
                Color(0xFF26272C),
                Color(0xFF1C1D20)

            )
        ),
        primaryContainerReversed = Brush.horizontalGradient(
            listOf(
                Color(0xFF1C1D20),
                Color(0xFF26272C)
            )
        )
    ),
    lightContainerColors = LightContainerColors(
        primaryText = Color(color = 0xFF003D4B),
        secondaryText = Color(color = 0xFF82878f),
        primaryContainer = Color(color = 0xFFE9E9EE),
        secondaryContainer = Color(color = 0xFF3F3F44),
        tertiaryContainer = Color(color = 0xFF3F3F44),
        inputTextContainer = Color(color = 0xFFFFFFFF),
        unfocusedInputContainer = Color(color = 0xFFdadae4),
        outline = Color(color = 0xFFC7C8C8)
    ),
    lightContainerGradients = LightContainerGradients(
        primaryContainer = Brush.horizontalGradient(
            colors = listOf(Color(color = 0xFFFFFFFF), Color(0xFFFAFAFD))
        ),
        primaryContainerReversed = Brush.horizontalGradient(
            colors = listOf(
                Color(0xFFF6F6FB),
                Color(0xFFFFFFFF)
            )
        )
    )
)

val montserratFontFamily = FontFamily(
    Font(R.font.montserrat_regular, FontWeight.Normal),
    Font(R.font.montserrat_bold, FontWeight.Bold),
    Font(R.font.montserrat_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.montserrat_black, FontWeight.Black)
)

val montserratTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp
    ),
    displayMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 40.sp
    ),
    displaySmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 32.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp
    ),
    titleMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    titleSmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    bodySmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    labelLarge = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    labelSmall = TextStyle(
        fontFamily = montserratFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    )
)