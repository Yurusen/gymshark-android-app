package android.angel.gymshark.presentation.screens.auth

import android.angel.gymshark.core.utils.LocalEdgeToEdgePadding
import android.angel.gymshark.core.utils.LocalHazeState
import android.angel.gymshark.core.utils.LocalScreenHeight
import android.angel.gymshark.presentation.components.ImageSlideshow
import android.angel.gymshark.presentation.components.buttons.CoreButton
import android.angel.gymshark.presentation.components.buttons.HollowButton
import android.angel.gymshark.ui.theme.AppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.prototype.gymshark.R
import dev.chrisbanes.haze.haze
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    navController: NavController
) {
    val screenHeight = LocalScreenHeight.current
    val hazeState = LocalHazeState.current
    val edgeToEdgePadding = LocalEdgeToEdgePadding.current
    val images = listOf(
        R.drawable.login_image,
        R.drawable.login_image_2,
        R.drawable.login_image_3,
        R.drawable.login_image_4,
        R.drawable.login_image_5,
    )

    fun isValidEmail(email: String) =
        Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$").matches(email)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .haze(hazeState, backgroundColor = Color.Black, blurRadius = 3.5.dp)
    ) {

        ImageSlideshow(images = images)

        Image(
            painter = painterResource(R.drawable.gymshark_white_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .padding(edgeToEdgePadding)
                .padding(16.dp)
                .height(35.dp)

        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(edgeToEdgePadding)
                .background(color = Color.Transparent), verticalArrangement = Arrangement.spacedBy(
                6.dp, alignment = Alignment.Bottom
            ), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                Text(
                    modifier = Modifier,
                    text = "The best of Gymshark, anytime, anywhere.",
                    style = AppTheme.typography.bodyLarge.copy(
                        color = AppTheme.systemColors.textPrimary, fontWeight = FontWeight.Bold
                    ),
                    textAlign = TextAlign.Center
                )
            }

            item {
                CoreButton(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    title = "create account",
                    onClick = {
                        viewModel.updateShowCreateAccountScreen(!viewModel.showCreateAccountScreen)
                    })
            }

            item {
                HollowButton(
                    modifier = Modifier.padding(horizontal = 16.dp), title = "log in", onClick = {
                        viewModel.updateShowLoginScreen(!viewModel.showLoginScreen)
                    })
            }

        }
    }
    CreateAccountSheet(viewModel, viewModel.showCreateAccountScreen, screenHeight)

    LoginScreen(navController, viewModel, viewModel.showLoginScreen, screenHeight)
}