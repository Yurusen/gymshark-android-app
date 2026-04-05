package com.prototype.gymshark

import android.angel.gymshark.core.utils.LocalEdgeToEdgePadding
import android.angel.gymshark.core.utils.LocalHazeState
import android.angel.gymshark.data.local.datastore.DataStore
import android.angel.gymshark.presentation.components.ImageSlideshow
import android.angel.gymshark.presentation.navigation.AppNavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.prototype.gymshark.ui.theme.GymsharkTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var dataStore: DataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymsharkTheme {
                val hazeState = remember { HazeState() }
                val isLoggedIn by dataStore.isLoggedIn.collectAsState(initial = false)

                CompositionLocalProvider(
                    LocalHazeState provides hazeState
                ) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.Transparent
                    ) { innerPadding ->
                        CompositionLocalProvider(
                            LocalEdgeToEdgePadding provides innerPadding
                        ) {
                            if (isLoggedIn == true) {
                                Image(
                                    painter = painterResource(id = R.drawable.gym),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .alpha(0.7f)
                                        .haze(
                                            hazeState,
                                            backgroundColor = Color.Transparent,
                                            blurRadius = 2.dp
                                        )
                                )
                            }


                            Box(
                                modifier = Modifier
                                    .background(color = Color.Transparent)
                            ) {
                                if (isLoggedIn != true) {
                                    Image(
                                        painter = painterResource(R.drawable.gymshark_white_logo),
                                        contentDescription = null,
                                        contentScale = ContentScale.Fit,
                                        modifier = Modifier
                                            .align(Alignment.TopStart)
                                            .padding(16.dp)
                                            .height(30.dp)
                                    )
                                }

                                AppNavGraph(
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
