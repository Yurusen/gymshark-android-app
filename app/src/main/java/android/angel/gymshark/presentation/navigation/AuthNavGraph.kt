package android.angel.gymshark.presentation.navigation

import android.angel.gymshark.data.local.datastore.DataStore
import android.angel.gymshark.presentation.screens.auth.LoginScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation

sealed class AuthRoute(val route: String, val title: String) {
    object Login: AuthRoute("login", title = "Login")
    object Register: AuthRoute("register", title = "Register")
    object ForgotPassword: AuthRoute("forgot-password", title = "Forgot Password?")
}

fun NavGraphBuilder.authNavGraph(navController: NavHostController, dataStore: DataStore, onLoginSuccess: () -> Unit) {

    navigation(
        startDestination = AuthRoute.Login.route,
        route = "auth"
    ) {

        composable(AuthRoute.Login.route) {
            LoginScreen()
        }

    }

}