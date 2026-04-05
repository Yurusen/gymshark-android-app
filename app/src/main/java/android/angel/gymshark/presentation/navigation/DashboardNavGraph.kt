package android.angel.gymshark.presentation.navigation

import android.angel.gymshark.data.local.datastore.DataStore
import android.angel.gymshark.presentation.screens.dashboard.DashboardScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation

sealed class DashboardRoutes(val route: String, title: String) {
    object Dashboard : DashboardRoutes("home", title = "Dashboard")

    companion object {
        val allPages = listOf(
            Dashboard
        )
    }
}

fun NavGraphBuilder.dashboardNavGraph(navController: NavHostController, dataStore: DataStore) {

    navigation(
        startDestination = DashboardRoutes.Dashboard.route,
        route = "dashboard"
    ) {
        composable(DashboardRoutes.Dashboard.route) {
            DashboardScreen()
        }
    }

}