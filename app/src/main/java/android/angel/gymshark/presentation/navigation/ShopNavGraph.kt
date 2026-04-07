package android.angel.gymshark.presentation.navigation

import android.angel.gymshark.data.local.datastore.DataStore
import android.angel.gymshark.presentation.components.BottomNav
import android.angel.gymshark.presentation.screens.shop.ShopCategoryScreen
import android.angel.gymshark.presentation.screens.shop.ShopScreen
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class ShopRoutes(val route: String, val title: String) {
    object ShopCategory : ShopRoutes(route = "shop/category?title={title}", title = "SHOP")
}

fun NavGraphBuilder.shopNavGraph(navController: NavHostController, dataStore: DataStore) {

    composable(BottomNav.Shop.route) {
        ShopScreen(
            navController
        )
    }

    composable(route = ShopRoutes.ShopCategory.route,
    arguments = listOf(
            navArgument("title") {
                type = NavType.StringType
                defaultValue = ShopRoutes.ShopCategory.title
            }
    )) { backStackEntry ->
        val title = backStackEntry.arguments?.getString("title")
        ShopCategoryScreen(title = title)
    }

}