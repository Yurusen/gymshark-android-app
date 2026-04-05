package com.prototype.gymshark.ui.theme

import android.angel.gymshark.ui.branding.MAINBRANDING
import android.angel.gymshark.ui.branding.montserratTypography
import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext


val LocalBrandColors = staticCompositionLocalOf<BrandColorScheme> {
    error("No BrandColorScheme provided")
}

val LocalAppGradients = staticCompositionLocalOf<ResolvedGradients> {
    error("No gradients provided")
}

val LocalAppColors = staticCompositionLocalOf<ResolvedColors> {
    error("No gradients provided")
}

val LocalPrimaryColors = staticCompositionLocalOf<PrimaryColors> {
    error("No primary colors provided")
}

val LocalPrimaryGradients = staticCompositionLocalOf<PrimaryGradients> {
    error("No primary gradients provided")
}

val LocalAppTypography = staticCompositionLocalOf<androidx.compose.material3.Typography> {
    error("No Typography provided")
}

@Composable
fun GymsharkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
//    val environment = BuildConfig.FLAVOUR
    val environment = "dev"

//    val branding: BRANDING = when (BuildConfig.FLAVOUR) {
    val branding: BRANDING = when (environment) {
        "dev" -> BRANDING.DEV
        "qa" -> BRANDING.QA
        "pov" -> BRANDING.POV
        else -> BRANDING.DEV
    }

//    val typography: androidx.compose.material3.Typography = when (BuildConfig.FLAVOUR) {
    val typography: androidx.compose.material3.Typography = when (environment) {
        "dev" -> montserratTypography
        "qa" -> montserratTypography
        "pov" -> montserratTypography
        else -> montserratTypography
    }

    fun provideBrandColorScheme(variant: BRANDING): BrandColorScheme {
        return when (variant) {
            BRANDING.DEV -> MAINBRANDING()
            BRANDING.QA -> MAINBRANDING()
            BRANDING.POV -> MAINBRANDING()
        }
    }

    val brand = provideBrandColorScheme(branding)
    val resolvedColors = brand.resolveColors(darkTheme)
    val resolvedGradients = brand.resolveGradients(darkTheme)
    val primaryColors = brand.resolvePrimaryColors()
    val primaryGradients = brand.resolvePrimaryGradients()



    CompositionLocalProvider(
        LocalBrandColors provides brand,
        LocalAppColors provides resolvedColors,
        LocalAppGradients provides resolvedGradients,
        LocalPrimaryColors provides primaryColors,
        LocalPrimaryGradients provides primaryGradients,
        LocalAppTypography provides typography
    ) {
        MaterialTheme(
            colorScheme = if (darkTheme) {
                darkColorScheme(
                    background = resolvedColors.container
                )
            } else {
                lightColorScheme(
                    background = resolvedColors.container
                )
            },
            typography = typography,
            content = content
        )
    }
}