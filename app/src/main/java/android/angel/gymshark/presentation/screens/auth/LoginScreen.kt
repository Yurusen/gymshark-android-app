package android.angel.gymshark.presentation.screens.auth

import android.angel.gymshark.core.utils.LocalEdgeToEdgePadding
import android.angel.gymshark.core.utils.LocalHazeState
import android.angel.gymshark.core.utils.LocalScreenHeight
import android.angel.gymshark.presentation.components.GlassSheet
import android.angel.gymshark.presentation.components.ImageSlideshow
import android.angel.gymshark.presentation.components.InputField
import android.angel.gymshark.presentation.components.UiContent
import android.angel.gymshark.presentation.components.Widget
import android.angel.gymshark.presentation.components.buttons.CoreButton
import android.angel.gymshark.presentation.components.buttons.HollowButton
import android.angel.gymshark.ui.theme.AppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxColors
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import dev.chrisbanes.haze.HazeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    var showCreateAccountScreen by remember { mutableStateOf<Boolean>(false) }
    var termsAndConds by remember { mutableStateOf<Boolean>(false) }
    val screenHeight = LocalScreenHeight.current
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
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
                        showCreateAccountScreen = !showCreateAccountScreen
                    })
            }

            item {
                HollowButton(
                    modifier = Modifier.padding(horizontal = 16.dp), title = "log in", onClick = {})
            }

        }
    }

    GlassSheet(
        isVisible = showCreateAccountScreen, sheetHeight = screenHeight, onDismissRequest = {
            showCreateAccountScreen = false
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
                    text = "GYMSHARK SIGNUP", style = AppTheme.typography.headlineSmall.copy(
                        AppTheme.systemColors.textPrimary, fontWeight = FontWeight.Bold
                    )
                )
            }

            Text(
                text = "One account, across all apps, just to make things a little easier",
                textAlign = TextAlign.Center,
                style = AppTheme.typography.bodyLarge.copy(AppTheme.systemColors.textSecondary)
            )

            InputField(
                value = firstName,
                onValueChange = { nameInput ->
                    firstName = nameInput
                },
                label = "first name",
                placeholder = "John",
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .padding(end = 19.dp)
                                .clickable { firstName = "" },
                            painter = painterResource(id = R.drawable.cross),
                            contentDescription = "Clear Name",
                            tint = AppTheme.systemColors.textPrimary
                        )
                    }
                },
            )

            InputField(
                value = surname,
                onValueChange = { nameInput ->
                    surname = nameInput
                },
                label = "last name",
                placeholder = "Smith",
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .padding(end = 19.dp)
                                .clickable { surname = "" },
                            painter = painterResource(id = R.drawable.cross),
                            contentDescription = "Clear Name",
                            tint = AppTheme.systemColors.textPrimary
                        )
                    }
                },
            )

            InputField(
                value = dob,
                onValueChange = { dobInput ->
                    dob = dobInput
                },
                label = "date of birth",
                placeholder = "01/01/1997",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                trailingIcon = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .padding(end = 19.dp)
                                .clickable { dob = "" },
                            painter = painterResource(id = R.drawable.cross),
                            contentDescription = "Clear Name",
                            tint = AppTheme.systemColors.textSecondary
                        )
                    }
                },
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
                            tint = AppTheme.systemColors.textSecondary
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
                            tint = AppTheme.systemColors.textSecondary
                        )
                        Icon(
                            modifier = Modifier
                                .padding(end = 19.dp)
                                .clickable { password = "" },
                            painter = painterResource(id = R.drawable.cross),
                            contentDescription = "Clear Password",
                            tint = AppTheme.systemColors.textSecondary
                        )
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            )

            CoreButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = "create account",
                onClick = {
                    showCreateAccountScreen = !showCreateAccountScreen
                })

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        colors = CheckboxDefaults.colors(
                            checkedColor = AppTheme.primaryColors.primary,
                            uncheckedColor = Color.White,
                            checkmarkColor = Color.White
                        ),
                        checked = termsAndConds,
                        onCheckedChange = { termsAndConds = !termsAndConds })

                    val annotatedText = buildAnnotatedString {
                        append("Tick here to receive emails about our products, apps, sales, exclusive content and more. See our ")

                        pushStringAnnotation(tag = "PRIVACY", annotation = "privacy_policy")
                        withStyle(
                            style = SpanStyle(
                                color = AppTheme.systemColors.textPrimary,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append("Privacy Policy")
                        }
                        pop()
                    }

                    ClickableText(
                        text = annotatedText, style = AppTheme.typography.bodyLarge.copy(
                            color = AppTheme.systemColors.textPrimary
                        ), onClick = { offset ->
                            val annotations = annotatedText.getStringAnnotations(
                                tag = "PRIVACY", start = offset, end = offset
                            )

                            if (annotations.isNotEmpty()) {
                            }
                        })
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account?",
                    style = AppTheme.typography.bodyLarge.copy(AppTheme.systemColors.textPrimary),
                    textAlign = TextAlign.Center
                )


                TextButton(onClick = {}) {
                    Text(
                        text = " Log in", style = AppTheme.typography.bodyLarge.copy(
                            AppTheme.primaryColors.primary, fontWeight = FontWeight.Bold
                        ), textAlign = TextAlign.Center, textDecoration = TextDecoration.Underline

                    )
                }
            }
        }
    }
}