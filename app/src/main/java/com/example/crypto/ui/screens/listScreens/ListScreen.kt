package com.example.crypto.ui.screens.listScreens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.crypto.Screens
import com.example.crypto.constants.Constants
import com.example.crypto.model.Coins.CryptoDetail

@Composable
fun ListScreen(
    navController: NavHostController,
    savedStateHandle: SavedStateHandle
) {
    val viewModel: ListScreenViewModel =
        viewModel(factory = ListScreenViewModel.Factory)
    val listState = viewModel.listState
    when (listState) {
        is ListState.Success -> {
            MainScreen(coins = listState.details, Modifier, navController, savedStateHandle)
        }
        is ListState.Error -> {
            ErrorScreen()
        }
        is ListState.Loading -> {
            LoadingScreen()
        }
    }
}

@Composable
fun MainScreen(coins: List<CryptoDetail>, modifier: Modifier, navController: NavHostController, savedStateHandle: SavedStateHandle) {

    Box(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        LazyColumn (){
            items(coins.size) { idx ->
                val coin = coins[idx]
                val status: String = if (coin.is_active) "active" else "inactive"
                Row (
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                                    savedStateHandle[Constants.PARAM_COIN_ID] = coin.id
                                   navController.navigate(route = Screens.COIN_DETAIL_SCREEN.name)
                        }, horizontalArrangement = Arrangement.SpaceEvenly){
                    Text(text = "${idx+1}.")
                    Text(text = coin.name+" " + coin.id)
                    Text(text = status, color = Color.Green)
                }
            }
        }
    }
}

@Composable
fun ErrorScreen() {
    Text(text = "Error")
}

@Composable
fun LoadingScreen() {
    Text(text = "Loading")
}