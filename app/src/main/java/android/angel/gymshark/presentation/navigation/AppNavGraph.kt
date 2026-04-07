package android.angel.gymshark.presentation.navigation

import android.angel.gymshark.core.utils.LocalEdgeToEdgePadding
import android.angel.gymshark.core.utils.LocalHazeState
import android.angel.gymshark.core.utils.LocalLoggedInHazeState
import android.angel.gymshark.core.utils.LocalScreenHeight
import android.angel.gymshark.data.local.datastore.DataStore
import android.angel.gymshark.presentation.components.BottomNav
import android.angel.gymshark.presentation.components.ImageSlideshow
import android.angel.gymshark.ui.theme.AppTheme
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.prototype.gymshark.R
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavGraph(dataStore: DataStore) {
    val navController = rememberNavController()
    val hazeState = LocalHazeState.current
    val isLoggedIn = dataStore.isLoggedIn.collectAsState(initial = null)
    val startDestination =
        if (isLoggedIn.value != true) "auth" else "dashboard"
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val authRoutes = listOf(
        AuthRoute.Login.route,
        AuthRoute.Register.route,
        AuthRoute.ForgotPassword.route
    )

    LaunchedEffect(isLoggedIn.value) {
        if (isLoggedIn.value == false) {
            navController.navigate("auth") {
                popUpTo(BottomNav.Home.route) { inclusive = true }
            }
        }
    }

    val loggedInHazeState = remember { HazeState() }

    val topBarTitle = when {
        currentRoute?.startsWith("shop/category") == true -> {
            navBackStackEntry?.arguments?.getString("title")
        }

        currentRoute == BottomNav.Home.route -> BottomNav.Home.title
        currentRoute == BottomNav.Shop.route -> BottomNav.Shop.title
        currentRoute == BottomNav.Basket.route -> BottomNav.Basket.title
        currentRoute == BottomNav.WishList.route -> BottomNav.WishList.title
        currentRoute == BottomNav.Profile.route -> BottomNav.Profile.title
        else -> "Error"
    }
    CompositionLocalProvider(
        LocalLoggedInHazeState provides loggedInHazeState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .haze(
                        loggedInHazeState,
                        backgroundColor = Color.Transparent,
                        blurRadius = 3.5.dp
                    )
            ) {
                Image(
                    painter = painterResource(R.drawable.content_bg),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.4f))
                )
            }

            Scaffold(
                modifier = Modifier
                    .nestedScroll(scrollBehaviour.nestedScrollConnection)
                    .padding(
                        if (currentRoute !in authRoutes) {
                            LocalEdgeToEdgePadding.current
                        } else PaddingValues(
                            0.dp
                        )
                    ),
                containerColor = Color.Transparent,
                topBar = {
                    if (currentRoute !in authRoutes) {
                        TopAppBar(
                            title = {
                                Text(
                                    text = topBarTitle ?: "N/A",
                                    style = AppTheme.typography.headlineSmall.copy(
                                        AppTheme.systemColors.textPrimary,
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                )
                            },
                            scrollBehavior = scrollBehaviour,
                            windowInsets = WindowInsets(0),
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Transparent,
                                scrolledContainerColor = Color.Transparent
                            ),
                            actions = {
                                Row() {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            painter = painterResource(R.drawable.message_icon),
                                            contentDescription = null,
                                            modifier = Modifier.size(24.dp),
                                            tint = AppTheme.systemColors.textPrimary
                                        )
                                    }

                                    IconButton(onClick = {}) {
                                        Box(
                                            modifier = Modifier
                                                .background(
                                                    color = AppTheme.systemColors.textPrimary,
                                                    shape = CircleShape
                                                )
                                                .padding(6.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "AS",
                                                style = AppTheme.typography.bodyLarge.copy(
                                                    AppTheme.systemColors.textSecondary
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        )
                    }
                },
                bottomBar = {
                    if (currentRoute !in authRoutes) {
                        BottomNav(
                            navController = navController
                        )
                    }
                }
            ) { padding ->

                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Transparent)
                        .padding(
                            if (currentRoute !in authRoutes) padding else PaddingValues(
                                0.dp
                            )
                        )
                ) {
                    val availableHeightInt = maxHeight
                    val topPadding = padding.calculateTopPadding()
                    val bottomPadding = padding.calculateBottomPadding()
                    val availableHeight = availableHeightInt - topPadding - bottomPadding

                    CompositionLocalProvider(
                        LocalScreenHeight provides availableHeight
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            NavHost(
                                modifier = Modifier
                                    .background(color = Color.Transparent),
                                navController = navController,
                                startDestination = startDestination,
                                enterTransition = { fadeIn(animationSpec = tween(600)) },
                                exitTransition = { fadeOut(animationSpec = tween(600)) }
                            ) {


                                authNavGraph(
                                    navController = navController,
                                    dataStore = dataStore,
                                    onLoginSuccess = {
                                        navController.navigate("dashboard") {
                                            popUpTo(AuthRoute.Login.route) { inclusive = true }
                                        }
                                    }
                                )

                                dashboardNavGraph(
                                    navController = navController,
                                    dataStore = dataStore
                                )

                                shopNavGraph(
                                    navController = navController,
                                    dataStore = dataStore
                                )

                                basketNavGraph(
                                    navController = navController,
                                    dataStore = dataStore
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}