package android.angel.gymshark.presentation.screens.auth

import android.angel.gymshark.core.utils.LocalScreenHeight
import android.angel.gymshark.presentation.components.GlassSheet
import android.angel.gymshark.presentation.components.InputField
import android.angel.gymshark.presentation.components.buttons.CoreButton
import android.angel.gymshark.ui.theme.AppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.prototype.gymshark.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: AuthViewModel,
    showScreen: Boolean,
    screenHeight: Dp,
) {
    val scope = rememberCoroutineScope()
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }

    LaunchedEffect(showScreen) {
    }

    GlassSheet(
        isVisible = viewModel.showLoginScreen,
        sheetHeight = screenHeight,
        onDismissRequest = {
            viewModel.updateShowLoginScreen(false)
        }) {

        Column(
            modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(
                12.dp, alignment = Alignment.CenterVertically
            ), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(180.dp),
                    painter = painterResource(R.drawable.shark_logo_white),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White)
                )

                Text(
                    text = "GYMSHARK LOGIN", style = AppTheme.typography.headlineSmall.copy(
                        AppTheme.systemColors.textPrimary, fontWeight = FontWeight.Bold
                    )
                )
            }

            Text(
                text = "Shop your styles, save top pics to your wishlist, track those orders & train with us.",
                textAlign = TextAlign.Center,
                style = AppTheme.typography.bodyLarge.copy(AppTheme.systemColors.textSecondary)
            )

            InputField(
                value = email,
                onValueChange = { emailInput ->
                    email = emailInput
                },
                label = "email*",
                placeholder = "someone@mail.com",
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .padding(end = 19.dp)
                                .clickable { email = "" },
                            painter = painterResource(id = R.drawable.cross),
                            contentDescription = "Clear Password",
                            tint = AppTheme.systemColors.textPrimary
                        )
                    }
                },
            )


            InputField(
                value = password,
                onValueChange = { currPass ->
                    password = currPass
                },
                label = "password*",
                placeholder = "*********",
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .padding(end = 25.dp)
                                .clickable {
                                    passwordVisible = !passwordVisible
                                },
                            painter = painterResource(id = if (passwordVisible) R.drawable.password_visible else R.drawable.password),
                            contentDescription = "Show Password",
                            tint = AppTheme.systemColors.textPrimary
                        )
                        Icon(
                            modifier = Modifier
                                .padding(end = 25.dp)
                                .clickable { password = "" },
                            painter = painterResource(id = R.drawable.cross),
                            contentDescription = "Clear Password",
                            tint = AppTheme.systemColors.textPrimary
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            )

            TextButton(onClick = {}) {
                Text(
                    text = "Forgot Password?", style = AppTheme.typography.bodyLarge.copy(
                        AppTheme.primaryColors.primary, fontWeight = FontWeight.Bold
                    ), textAlign = TextAlign.Center, textDecoration = TextDecoration.Underline

                )
            }

            CoreButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = "log in",
                onClick = {
                    scope.launch {
                        viewModel.updateShowLoginScreen(!viewModel.showLoginScreen)
//                        delay(600)
                        navController.navigate("dashboard")
                    }
                })

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Don't have an account?",
                    style = AppTheme.typography.bodyLarge.copy(AppTheme.systemColors.textPrimary),
                    textAlign = TextAlign.Center
                )


                TextButton(onClick = {
                    scope.launch {
                        viewModel.updateShowLoginScreen(!viewModel.showLoginScreen)
                        delay(600)
                        viewModel.updateShowCreateAccountScreen(!viewModel.showCreateAccountScreen)
                    }
                }) {
                    Text(
                        text = "Sign up", style = AppTheme.typography.bodyLarge.copy(
                            AppTheme.primaryColors.primary, fontWeight = FontWeight.Bold
                        ), textAlign = TextAlign.Center, textDecoration = TextDecoration.Underline

                    )
                }
            }

        }
    }
}