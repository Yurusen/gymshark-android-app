package android.angel.gymshark.presentation.components

import android.angel.gymshark.core.utils.LocalLoggedInHazeState
import android.angel.gymshark.ui.theme.AppTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.prototype.gymshark.R
import dev.chrisbanes.haze.hazeChild
import android.graphics.drawable.Icon
import androidx.compose.material.Icon

sealed class BottomNav(val route: String, val title: String, val icon: Int) {
    object Home : BottomNav(
        route = "home", title = "HOME", icon = R.drawable.home_icon
    )

    object Shop : BottomNav(
        route = "shop", title = "SHOP", icon = R.drawable.search_icon
    )

    object Basket : BottomNav(
        route = "basket", title = "YOUR BASKET", icon = R.drawable.basket_icon
    )

    object WishList : BottomNav(
        route = "wishlist", title = "WISH LIST", icon = R.drawable.wishlist_icon
    )

    object Profile : BottomNav(
        route = "profilt", title = "PROFILE", icon = R.drawable.profile_icon
    )
}

@Composable
fun BottomNav(
    navController: NavController
) {
    val items = listOf(
        BottomNav.Home,
        BottomNav.Shop,
        BottomNav.Basket,
        BottomNav.WishList,
        BottomNav.Profile,
    )

    val currentBackstackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = currentBackstackEntry.value?.destination?.route

    val selectedItem = remember(currentRoute) {
        items.indexOfFirst { item ->
            when {
                item.route == BottomNav.Home.route -> true
                item.route == BottomNav.Shop.route -> true
                item.route == BottomNav.Basket.route -> true
                item.route == BottomNav.WishList.route -> true
                item.route == BottomNav.Profile.route -> true
                else -> false
            }
        }.coerceAtLeast(0)
    }

    val backgroundBrush: Brush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.2f),
            Color.White.copy(alpha = 0.05f)
        )
    )

    val border: Brush = Brush.linearGradient(
        colors = listOf(
            Color.White.copy(alpha = 0.4f),
            Color.White.copy(alpha = 0.2f)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(top = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(1f))
                .background(brush = backgroundBrush, shape = RoundedCornerShape(99.dp))
                .border(1.dp, brush = border, shape = RoundedCornerShape(99.dp))
                .hazeChild(LocalLoggedInHazeState.current, RoundedCornerShape(99.dp))
                .fillMaxWidth(0.9f)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, item ->
                IconButton(
                    onClick = {}, modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(item.icon),
                        contentDescription = null,
                        tint = if (selectedItem == index) AppTheme.primaryColors.primary else AppTheme.systemColors.textSecondary
                    )
                }
            }
        }
    }
}