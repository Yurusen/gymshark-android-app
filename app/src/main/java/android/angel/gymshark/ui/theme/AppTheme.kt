package android.angel.gymshark.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import com.prototype.gymshark.ui.theme.LocalAppColors
import com.prototype.gymshark.ui.theme.LocalAppGradients
import com.prototype.gymshark.ui.theme.LocalAppTypography
import com.prototype.gymshark.ui.theme.LocalPrimaryColors
import com.prototype.gymshark.ui.theme.LocalPrimaryGradients
import com.prototype.gymshark.ui.theme.PrimaryColors
import com.prototype.gymshark.ui.theme.PrimaryGradients
import com.prototype.gymshark.ui.theme.ResolvedColors
import com.prototype.gymshark.ui.theme.ResolvedGradients

object AppTheme {
    val systemColors: ResolvedColors
        @Composable get() = LocalAppColors.current

    val systemGradients: ResolvedGradients
        @Composable get() = LocalAppGradients.current

    val primaryColors: PrimaryColors
        @Composable get() = LocalPrimaryColors.current

    val primaryGradients: PrimaryGradients
        @Composable get() = LocalPrimaryGradients.current

    val typography: androidx.compose.material3.Typography
        @Composable get() = LocalAppTypography.current
}