package android.angel.gymshark.presentation.navigation

import android.angel.gymshark.data.local.datastore.DataStore
import android.angel.gymshark.presentation.components.BottomNav
import android.angel.gymshark.presentation.screens.dashboard.DashboardScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

sealed class DashboardRoutes(val route: String, val title: String) {
//    object Dashboard : DashboardRoutes("home", title = "HOME")
}

fun NavGraphBuilder.dashboardNavGraph(navController: NavHostController, dataStore: DataStore) {

    navigation(
        startDestination = BottomNav.Home.route,
        route = "dashboard"
    ) {
        composable(BottomNav.Home.route) {
            DashboardScreen()
        }
    }

}