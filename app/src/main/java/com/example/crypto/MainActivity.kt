package com.example.crypto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crypto.constants.Constants
import com.example.crypto.ui.screens.detailScreen.CoinDetailScreen
import com.example.crypto.ui.screens.listScreens.ListScreen
import com.example.crypto.ui.theme.CryptoTheme

class MainActivity : ComponentActivity() {
    val savedStateHandle = SavedStateHandle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController: NavHostController = rememberNavController()
                    val startDestination = Screens.LIST_SCREEN.name
                    NavHost(navController = navController, startDestination = startDestination) {
                        composable(route = Screens.LIST_SCREEN.name) {
                            ListScreen(navController = navController, savedStateHandle)
                        }
                        composable(route = Screens.COIN_DETAIL_SCREEN.name + "/{${Constants.PARAM_COIN_ID}}") {
                            it.arguments?.getString(Constants.PARAM_COIN_ID)?.let { it1 ->
                                CoinDetailScreen(
                                    coinId = it1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
enum class Screens {
    LIST_SCREEN, COIN_DETAIL_SCREEN
}