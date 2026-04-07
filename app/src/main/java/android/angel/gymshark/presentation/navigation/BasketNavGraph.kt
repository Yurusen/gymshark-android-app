package android.angel.gymshark.presentation.navigation

import android.angel.gymshark.data.local.datastore.DataStore
import android.angel.gymshark.presentation.components.BottomNav
import android.angel.gymshark.presentation.screens.basket.BasketScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.basketNavGraph(navController: NavController, dataStore: DataStore) {

    composable(
        route = BottomNav.Basket.route
    ) {
        BasketScreen(navController = navController)
    }

}