package com.prototype.gymshark.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color


enum class ThemeMode {
    LIGHT,
    DARK,
    SYSTEM_DEFAULT
}

enum class BRANDING {
    DEV,
    QA,
    POV
}

data class BrandColorScheme(
    val primaryColors: PrimaryColors,
    val primaryGradients: PrimaryGradients,
    val darkContainerColors: DarkContainerColors,
    val darkContainerGradients: DarkContainerGradients,
    val lightContainerColors: LightContainerColors,
    val lightContainerGradients: LightContainerGradients
)

data class PrimaryColors(
    val primary: Color,
    val secondary: Color,
    val approve: Color,
    val amberWarning: Color,
    val reject: Color,
)

data class PrimaryGradients(
    val primaryGradient: Brush,
    val secondaryGradient: Brush,
    val approveGradient: Brush,
    val amberGradient: Brush,
    val criticalGradient: Brush,
    val rejectGradient: Brush

)

data class DarkContainerColors(
    val primaryText: Color,
    val secondaryText: Color,
    val primaryContainer: Color,
    val inputTextContainer: Color,
    val unfocusedInputContainer: Color,
    val outline: Color
)


data class DarkContainerGradients(
    val primaryContainer: Brush,
    val primaryContainerReversed: Brush,
)

data class LightContainerColors(
    val primaryText: Color,
    val secondaryText: Color,
    val primaryContainer: Color,
    val secondaryContainer: Color,
    val tertiaryContainer: Color,
    val inputTextContainer: Color,
    val unfocusedInputContainer: Color,
    val outline: Color
)


data class LightContainerGradients(
    val primaryContainer: Brush,
    val primaryContainerReversed: Brush,
)

data class ResolvedColors(
    val textPrimary: Color,
    val textSecondary: Color,
    val container: Color,
    val inputContainer: Color,
    val unfocusedInputContainer: Color,
    val outline: Color
)

data class ResolvedGradients(
    val primaryContainer: Brush,
    val primaryContainerReversed: Brush,
)

fun BrandColorScheme.resolveColors(isDark: Boolean): ResolvedColors {
    return if (isDark) {
        ResolvedColors(
            textPrimary = darkContainerColors.primaryText,
            textSecondary = darkContainerColors.secondaryText,
            container = darkContainerColors.primaryContainer,
            inputContainer = darkContainerColors.inputTextContainer,
            unfocusedInputContainer = darkContainerColors.unfocusedInputContainer,
            outline = darkContainerColors.outline
        )
    } else {
        ResolvedColors(
            textPrimary = lightContainerColors.primaryText,
            textSecondary = lightContainerColors.secondaryText,
            container = lightContainerColors.primaryContainer,
            inputContainer = lightContainerColors.inputTextContainer,
            unfocusedInputContainer = lightContainerColors.unfocusedInputContainer,
            outline = lightContainerColors.outline
        )
    }
}

fun BrandColorScheme.resolveGradients(isDark: Boolean): ResolvedGradients {
    return if (isDark) {
        ResolvedGradients(
            primaryContainer = darkContainerGradients.primaryContainer,
            primaryContainerReversed = darkContainerGradients.primaryContainerReversed
        )
    } else {
        ResolvedGradients(
            primaryContainer = lightContainerGradients.primaryContainer,
            primaryContainerReversed = lightContainerGradients.primaryContainerReversed
        )
    }
}


fun BrandColorScheme.resolvePrimaryColors(): PrimaryColors {
    return primaryColors
}

fun BrandColorScheme.resolvePrimaryGradients(): PrimaryGradients {
    return primaryGradients
}
