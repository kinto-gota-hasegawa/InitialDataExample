package com.example.initialdataexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.initialdataexample.ui.theme.InitialDataExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InitialDataExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        startDestination = MainScreenRoute,
                    ) {
                        composable(
                            route = MainScreenRoute
                        ) {
                            MainScreen {
                                navController.navigate(it)
                            }
                        }
                        composable(
                            route = ViewModelInitScreenRoute
                        ) {
                            ViewModelInitScreen()
                        }
                        composable(
                            route = LaunchedEffectScreenRoute
                        ) {
                            LaunchedEffectScreen()
                        }
                        composable(
                            route = ReactiveScreenRoute,
                        ) {
                            ReactiveScreen()
                        }
                    }
                }
            }
        }
    }
}

const val MainScreenRoute = "MainScreenRoute"


private val RouteList = listOf(
    ViewModelInitScreenRoute,
    LaunchedEffectScreenRoute,
    ReactiveScreenRoute,
)

@Composable
fun MainScreen(
    navigateTo: (route: String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 16.dp),
    ) {
        RouteList.forEach { route ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navigateTo(route)
                    }
                    .padding(24.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    "Navigate to $route",
                    fontSize = 24.sp,
                )
            }
        }
    }
}
